package cn.com.elex.social_life.sys.receiver.im;


import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;

import cn.com.elex.social_life.model.bean.ChatMessage;
import cn.com.elex.social_life.support.event.ChatMsgEvent;
import de.greenrobot.event.EventBus;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public  class IMMessageReceiver extends AVIMMessageHandler {
    /**
     * 处理接收消息
     * @param message
     * @param conversation
     * @param client
     */


    @Override
    public void onMessage(AVIMMessage message,AVIMConversation conversation,AVIMClient client){

        //转化为message
        ChatMessage msg= JSON.parseObject(message.getContent(),ChatMessage.class);
        ChatMsgEvent event=new ChatMsgEvent(msg,conversation);
        EventBus.getDefault().post(event);
    }

    /**
     * 处理消息回执
     * @param message
     * @param conversation
     * @param client
     */
    public void onMessageReceipt(AVIMMessage message,AVIMConversation conversation,AVIMClient client){


    }





}