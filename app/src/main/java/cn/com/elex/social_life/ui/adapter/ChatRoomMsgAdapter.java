package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMMessage;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.support.util.BitmapUtil;

/**
 * Created by zhangweibo on 2015/11/10.
 */
public class ChatRoomMsgAdapter extends RecyclerView.Adapter<ChatRoomMsgAdapter.ViewHolder> {

    private Context context;

    private List<AVIMMessage> messages;

    private String userName;
    Html.ImageGetter imageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable d;
            d = new BitmapDrawable(context.getResources(), BitmapUtil.getBitmapEmojiFromAssert(source));
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }
    };

    public ChatRoomMsgAdapter(Context context, List<AVIMMessage> messages,String userName) {
        this.context = context;
        this.messages = messages;
        this.userName= userName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_chat_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AVIMMessage msg=messages.get(position);
        if (userName.equals(msg.getFrom()))
        {
            showMsgOwnSide(holder,msg);
        }else{
            showMsgOppositeSide(holder,msg);
        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_chat_room.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.icon_left_user)
        SimpleDraweeView iconLeftUser;
        @Bind(R.id.tv_left_nickname)
        TextView tvLeftNickname;
        @Bind(R.id.tv_left_content)
        TextView tvLeftContent;
        @Bind(R.id.rl_speak_left)
        RelativeLayout rlSpeakLeft;
        @Bind(R.id.icon_other_user)
        SimpleDraweeView iconOtherUser;
        @Bind(R.id.tv_other_nickname)
        TextView tvOtherNickname;
        @Bind(R.id.tv_right_content)
        TextView tvRightContent;
        @Bind(R.id.rl_speak_right)
        RelativeLayout rlSpeakRight;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 显示对方发送的信息
     * @param holder
     */
    public void showMsgOppositeSide(ViewHolder holder,AVIMMessage msg){

        holder.rlSpeakLeft.setVisibility(View.VISIBLE);
        holder.rlSpeakRight.setVisibility(View.GONE);
        //设置用户icon
     //   holder.iconLeftUser.setImageURI(Uri.parse(msg.getUserIcon()));
        //设置用户昵称
        holder.tvLeftNickname.setText(msg.getFrom());
        Spanned content=Html.fromHtml(msg.getContent(), imageGetter, null);
        if (content.length()>2)
        {
            content= (Spanned) content.subSequence(0,content.length()-2);
        }
        holder.tvLeftContent.setText(content);
    }

    /**
     * 显示己方发送的信息
     * @param holder
     * @param msg
     */

    public void showMsgOwnSide(ViewHolder holder,AVIMMessage msg){

        holder.rlSpeakLeft.setVisibility(View.GONE);
        holder.rlSpeakRight.setVisibility(View.VISIBLE);
    //    holder.iconLeftUser.setImageURI(Uri.parse(msg.getUserIcon()));
        holder.tvOtherNickname.setText(msg.getFrom());
        Spanned content=Html.fromHtml(msg.getContent(), imageGetter, null);
        if (content.length()>2)
        {
            content= (Spanned) content.subSequence(0,content.length()-2);
        }
        holder.tvRightContent.setText(content);
    }





}
