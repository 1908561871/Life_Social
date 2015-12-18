package cn.com.elex.social_life.ui.activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.cloud.im.message.MessageSendCotrol;
import cn.com.elex.social_life.model.bean.ChatMessage;
import cn.com.elex.social_life.model.bean.ChatMsgSendType;
import cn.com.elex.social_life.support.callback.CustomAVIMConversationCreatedCallback;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.event.ChatMsgEvent;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.adapter.ChatRoomMsgAdapter;
import cn.com.elex.social_life.ui.adapter.EmoticonsGridAdapter;
import cn.com.elex.social_life.ui.adapter.EmoticonsPagerAdapter;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IChatRoomView;
import de.greenrobot.event.Subscribe;

/**
 * Created by zhangweibo on 2015/11/9.
 */
public class ChatRoomActivity extends BaseActivity implements EmoticonsGridAdapter.KeyClickListener, IChatRoomView {

    private static final int NO_OF_EMOTICONS = 54;
    @Bind(R.id.et_content)
    EditText content;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.iv_keyboard)
    ImageView ivKeyboard;
    @Bind(R.id.footer_input)
    LinearLayout footerInput;
    @Bind(R.id.bottom_layout)
    LinearLayout emoticonsCover;
    @Bind(R.id.parent_layout)
    RelativeLayout parentLayout;
    private ChatRoomMsgAdapter chatRoomMsgAdapter;
    private List<ChatMessage> messages;
    private PopupWindow popupWindow;
    //表情
    private View popUpView;

    private boolean isKeyBoardVisible;

    private int keyboardHeight;

    /**
     * 对话
     */
    private AVIMConversation conversation;

    private List<String> members;


    private ChatMessage chatMessage;

    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable d;
            d = new BitmapDrawable(getResources(), BitmapUtil.getBitmapEmojiFromAssert(source));
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);
        init();
    }


    public void init() {
        initData();
        createConverstation();
        enablePopUpView();
        checkKeyboardHeight(parentLayout);
    }

    public void initData() {
        members = Arrays.asList(getIntent().getStringArrayExtra("member"));
        messages = new ArrayList<>();
        chatMessage = new ChatMessage();
        chatMessage.setNickName(ClientUserManager.getInstance().obtainCurrentUser().getUsername());
        chatMessage.setSendType(ChatMsgSendType.OWN);
        popUpView = getLayoutInflater().inflate(R.layout.emoticons_popup, null);
        chatRoomMsgAdapter = new ChatRoomMsgAdapter(this, messages);
        recycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(chatRoomMsgAdapter);

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

            }

        });
    }


    /**
     * Defining all components of emoticons keyboard
     */
    private void enablePopUpView() {
        ViewPager pager = (ViewPager) popUpView.findViewById(R.id.emoticons_pager);
        pager.setOffscreenPageLimit(3);

        ArrayList<String> paths = new ArrayList<String>();

        for (short i = 1; i <= NO_OF_EMOTICONS; i++) {
            paths.add(i + ".png");
        }

        EmoticonsPagerAdapter adapter = new EmoticonsPagerAdapter(this, paths, this);
        pager.setAdapter(adapter);

        // Creating a pop window for emoticons keyboard
        popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.MATCH_PARENT,
                (int) keyboardHeight, false);
        TextView backSpace = (TextView) popUpView.findViewById(R.id.back);
        backSpace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                content.dispatchKeyEvent(event);
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                emoticonsCover.setVisibility(LinearLayout.GONE);
            }
        });
    }



    @Override
    public void keyClickedIndex(final String index) {

        Spanned cs = Html.fromHtml("<img src ='" + index + "'/>", imageGetter, null);
        int cursorPosition = content.getSelectionStart();
        content.getText().insert(cursorPosition, cs);
    }


    /**
     * Overriding onKeyDown for dismissing keyboard on key down
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * Checking keyboard height and keyboard visibility
     */
    int previousHeightDiffrence = 0;

    private void checkKeyboardHeight(final View parentLayout) {

        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {

                        Rect r = new Rect();
                        parentLayout.getWindowVisibleDisplayFrame(r);

                        int screenHeight = parentLayout.getRootView()
                                .getHeight();
                        int heightDifference = screenHeight - (r.bottom);

                        if (previousHeightDiffrence - heightDifference > 50) {
                            popupWindow.dismiss();
                        }

                        previousHeightDiffrence = heightDifference;
                        if (heightDifference > 100) {

                            isKeyBoardVisible = true;
                            changeKeyboardHeight(heightDifference);

                        } else {

                            isKeyBoardVisible = false;

                        }

                    }
                });

    }

    /**
     * change height of emoticons keyboard according to height of actual
     * keyboard
     *
     * @param height minimum height by which we can make sure actual keyboard is
     *               open or not
     */
    private void changeKeyboardHeight(int height) {

        if (height > 100) {
            keyboardHeight = height;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, keyboardHeight);
            emoticonsCover.setLayoutParams(params);
        }

    }


    @OnClick(R.id.et_content)
    public void clickContent() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    /**
     * 回复信息
     */
    @OnClick(R.id.bt_send_msg)
    public void replyContent() {
        String sp = Html.toHtml(content.getText());
        chatMessage.setContent(sp);
        refreshChatMsg(chatMessage);
        if (conversation == null) {
            createConverstation();
        } else {
            MessageSendCotrol.sendTextMsg(conversation, new MsgCallBack() {
                @Override
                public void success() {
                    content.setText("");
                    ToastUtils.show(ChatRoomActivity.this, "信息发送成功");
                }

                @Override
                public void failure(String msg) {

                }
            }, chatMessage);
        }
    }


    @OnClick(R.id.iv_face)
    public void clickFaceAction() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.setHeight(keyboardHeight);
            if (isKeyBoardVisible) {
                emoticonsCover.setVisibility(LinearLayout.GONE);
            } else {
                emoticonsCover.setVisibility(LinearLayout.VISIBLE);
            }
            popupWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    public String getReplayContent() {
        return null;
    }

    @Override
    public void refreshChatMsg(ChatMessage msg) {
        messages.add(msg);
        chatRoomMsgAdapter.notifyDataSetChanged();
        recycleView.smoothScrollToPosition(chatRoomMsgAdapter.getItemCount() - 1);
    }


    /**
     * 接收消息
     *
     * @param event
     */
    @Subscribe
    public void onReceiverMessage(ChatMsgEvent event) {

        ChatMessage msg = event.getMsg();
        msg.setSendType(ChatMsgSendType.OPPOSITE);
        refreshChatMsg(msg);


    }


    public void createConverstation() {
        ClientUserManager.getInstance().obtainCurrentClentUser().createConversation(members, "conversation", null,true,new CustomAVIMConversationCreatedCallback() {
            @Override
            protected void success(AVIMConversation avimConversation) {
                ToastUtils.show(ChatRoomActivity.this, "对话创建成功");
                conversation = avimConversation;
            }
            @Override
            protected void failure(String error) {
                ToastUtils.show(ChatRoomActivity.this, "对话创建失败");
            }
        });

    }

}
