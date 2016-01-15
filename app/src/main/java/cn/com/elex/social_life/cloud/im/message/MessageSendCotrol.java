package cn.com.elex.social_life.cloud.im.message;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

import cn.com.elex.social_life.support.callback.CustomConversitonCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public class MessageSendCotrol {

    /**
     * 发送文本消息
     */

    public static void sendTextMsg(AVIMConversation avimConversation,MsgCallBack callBack,AVIMMessage message){
        avimConversation.sendMessage(message, new CustomConversitonCallBack(callBack));
    }






}
