package cn.com.elex.social_life.ui.activity;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.presenter.ChatRoomPresenter;
import cn.com.elex.social_life.support.callback.CustomAVIMConversationCreatedCallback;
import cn.com.elex.social_life.support.event.ChatMsgEvent;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.view.cjj.MaterialRefreshLayout;
import cn.com.elex.social_life.support.view.cjj.MaterialRefreshListener;
import cn.com.elex.social_life.ui.adapter.ChatRoomMsgAdapter;
import cn.com.elex.social_life.ui.adapter.EmoticonsGridAdapter;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.fragment.EmojiFragment;
import cn.com.elex.social_life.ui.fragment.PictureSelectFragment;
import cn.com.elex.social_life.ui.iview.IChatRoomView;
import de.greenrobot.event.Subscribe;

/**
 * Created by zhangweibo on 2015/11/9.
 */
public class ChatRoomActivity extends BaseActivity implements EmoticonsGridAdapter.KeyClickListener, IChatRoomView, EmojiFragment.KeyClickListen {

    @Bind(R.id.et_content)
    EditText content;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.iv_picture)
    ImageView ivKeyboard;
    @Bind(R.id.footer_input)
    LinearLayout footerInput;
    @Bind(R.id.parent_layout)
    RelativeLayout parentLayout;
    @Bind(R.id.tv_left)
    ImageView tvLeft;
    @Bind(R.id.fl_tool)
    FrameLayout toolLayout;
    private ChatRoomMsgAdapter chatRoomMsgAdapter;
    private List<AVIMMessage> messages;

    private boolean isKeyBoardVisible;

    private int keyboardHeight;

    /**
     * 对话
     */
    private AVIMConversation conversation;

    private List<String> members;

    private int pageNum = 20;

    private int pageSize;

    private AVIMMessage chatMessage;

    private ChatRoomPresenter presenter;

    private String userName;

    private float itemSize;

    private boolean isToolNeedShow;

    private boolean isAdd;
    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable d;
            d = new BitmapDrawable(getResources(), BitmapUtil.getBitmapEmojiFromAssert(source));
            d.setBounds(0, 0, (int) itemSize, (int) itemSize);
            return d;
        }
    };
    private EmojiFragment emojiFragment;

    private PictureSelectFragment pictureSelectFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);
        if (savedInstanceState!=null)
        {
            isAdd=savedInstanceState.getBoolean("isAdd",false);

        }
        presenter = new ChatRoomPresenter(this);
        userName = AVUser.getCurrentUser().getUsername();
        init();
    }


    public void init() {
        initData();
        createConverstation();
        initEmojiFragment();
        checkKeyboardHeight(parentLayout);
    }

    public void initData() {

        members = Arrays.asList(getIntent().getStringArrayExtra("member"));
        messages = new ArrayList<>();
        itemSize = getResources().getDimension(R.dimen.emoij_grid_item);
        chatRoomMsgAdapter = new ChatRoomMsgAdapter(this, messages, userName);
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
    private void initEmojiFragment() {
        Log.w("TAG","initEmojiFragment");
        Log.w("TAG","isadd"+isAdd);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        emojiFragment = new EmojiFragment();
        pictureSelectFragment=new PictureSelectFragment();
        transaction.add(R.id.fl_tool, pictureSelectFragment);
        transaction.add(R.id.fl_tool, emojiFragment);
        transaction.hide(pictureSelectFragment);
        transaction.hide(emojiFragment);
        transaction.commit();
         emojiFragment.setKeyClickListen(this);

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
        Log.w("TAG","onKeyDown");
        if (toolLayout.getVisibility()==View.VISIBLE) {
            toolLayout.setVisibility(View.GONE);
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
                        Log.w("checkKeyboardHeight","当前高度:"+heightDifference);

                        previousHeightDiffrence = heightDifference;
                        //如果软键盘展开
                        if (heightDifference > 100 && !isKeyBoardVisible) {
                            isKeyBoardVisible = true;
                            isToolNeedShow=false;
                            changeKeyboardHeight(heightDifference);
                        } else {
                            //软键盘收缩
                            if (heightDifference==0 )
                                isKeyBoardVisible = false;
                              if (isToolNeedShow) {
                                  {
                                      isToolNeedShow=false;
                                      toolLayout.setVisibility(View.VISIBLE);
                                  }
                              }
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
            toolLayout.setLayoutParams(params);
        }

    }


    public void hideToolLayout(){
        toolLayout.setVisibility(View.GONE);

    }


    public void showToolLayout(){
        if (isKeyBoardVisible)
        {
            isToolNeedShow=true;
            toggleSoftInput();

        }else{
            if (toolLayout.getVisibility()!=View.VISIBLE)
            {
                toolLayout.setVisibility(View.VISIBLE);
            }else{
                toggleSoftInput();
                hideToolLayout();
            }
        }
    }

    @OnClick(R.id.et_content)
    public void clickContent() {

        hideToolLayout();
    }

    /**
     * 回复信息
     */
    @OnClick(R.id.bt_send_msg)
    public void replyContent() {
        String sp = Html.toHtml(content.getText());
        AVIMMessage message = new AVIMMessage();
        message.setContent(sp);
        presenter.sendMessage(message);

    }

    /**
     * 选择照片
     */
    @OnClick(R.id.iv_picture)
    public void selectPicture(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (toolLayout.getVisibility()==View.GONE ){
            transaction.hide(emojiFragment);
            transaction.show(pictureSelectFragment);
            transaction.commit();
            isToolNeedShow=true;
            showToolLayout();
        }else if (pictureSelectFragment.isHidden())
        {
            transaction.hide(emojiFragment);
            transaction.show(pictureSelectFragment);
            transaction.commit();
        }else if (pictureSelectFragment.isVisible())
        {
            showToolLayout();

        }

    }



    @OnClick(R.id.iv_face)
    public void clickFaceAction() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       if (toolLayout.getVisibility()==View.GONE){
           transaction.hide(pictureSelectFragment);
           transaction.show(emojiFragment);
           transaction.commit();
           isToolNeedShow=true;
            showToolLayout();
        }else if (emojiFragment.isHidden())
        {
            transaction.hide(pictureSelectFragment);
            transaction.show(emojiFragment);
            transaction.commit();

        }else if (emojiFragment.isVisible())
       {
           showToolLayout();

       }

    }

    @Override
    public String getReplayContent() {
        return null;
    }


    @Override
    public void refreshChatMsg(AVIMMessage msg) {
        messages.add(msg);
        chatRoomMsgAdapter.notifyDataSetChanged();
        recycleView.smoothScrollToPosition(chatRoomMsgAdapter.getItemCount() - 1);
    }

    @Override
    public void updateHistory(List<AVIMMessage> msgs) {
        messages.addAll(0, msgs);
        chatRoomMsgAdapter.notifyDataSetChanged();
    }

    @Override
    public AVIMConversation getAVIMConversation() {
        return conversation;
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void closeLoadMore() {
        refreshLayout.setCanRefresh(false);
    }

    @Override
    public void finishLoadMore() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void clearContent() {
        content.setText("");
    }

    /**
     * 接收消息
     *
     * @param event
     */
    @Subscribe
    public void onReceiverMessage(ChatMsgEvent event) {
        if (event.getConversation().getConversationId().equals(conversation.getConversationId())) {
            refreshChatMsg(event.getMsg());
        }
    }

    /**
     * 创建对话
     */
    public void createConverstation() {
        ClientUserManager.getInstance().obtainCurrentClentUser().createConversation(members, "conversation", null, false, true, new CustomAVIMConversationCreatedCallback() {
            @Override
            protected void success(AVIMConversation avimConversation) {
                conversation = avimConversation;
                presenter.obtainHistory(null);
            }

            @Override
            protected void failure(String error) {
            }
        });
    }

    @Override
    public void onKeyClickListen(String index) {
        Spanned cs = Html.fromHtml("<img src ='" + index + "'/>", imageGetter, null);
        int cursorPosition = content.getSelectionStart();
        content.getText().insert(cursorPosition, cs);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.w("TAG","onSaveInstanceState");
        outState.putBoolean("isAdd",isAdd);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Log.w("TAG","onDestroy");
        super.onDestroy();
    }
}
