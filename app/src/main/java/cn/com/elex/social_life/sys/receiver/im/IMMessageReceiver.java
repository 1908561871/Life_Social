package cn.com.elex.social_life.sys.receiver.im;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.support.event.ChatMsgEvent;
import cn.com.elex.social_life.ui.activity.ChatRoomActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public  class IMMessageReceiver extends AVIMMessageHandler {
    private final NotificationManager manager;
    /**
     * 处理接收消息
     * @param message
     * @param conversation
     * @param client
     */

    private Context context;


    public IMMessageReceiver(Context context) {
        this.context = context;
         manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onMessage(AVIMMessage message,AVIMConversation conversation,AVIMClient client){

        Log.w("IMMessageReceiver","onMessage:"+message.toString());
        notification(  message.getFrom());
        //转化为message
     //   ChatMessage msg= JSON.parseObject(message.getContent(),ChatMessage.class);
        ChatMsgEvent event=new ChatMsgEvent(message,conversation);
        EventBus.getDefault().post(event);


    }

    /**
     * 处理消息回执
     * @param message
     * @param conversation
     * @param client
     */
    public void onMessageReceipt(AVIMMessage message,AVIMConversation conversation,AVIMClient client){
        Log.w("IMMessageReceiver","onMessageReceipt"+message.toString());
    }




    public void notification(String member){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context).setSmallIcon(R.drawable.search_icon);
        Intent intent=new Intent(context,ChatRoomActivity.class);
        intent.putExtra("member",new String[]{member});
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, 0);
        builder.setContentIntent(pendingIntent);
        manager.notify(10,builder.build());

    }

}