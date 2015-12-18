package cn.com.elex.social_life.cloud.im.message;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.List;

import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.ChatMessage;
import cn.com.elex.social_life.support.callback.CustomAVIMConversationCreatedCallback;
import cn.com.elex.social_life.support.callback.CustomConversitonCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.util.StringUtils;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public class MessageSendCotrol {

    /**
     * 发送文本消息
     */

    public static void sendTextMsg(AVIMConversation avimConversation,MsgCallBack callBack,ChatMessage chatMessage){
        AVIMMessage msg=new AVIMMessage();
        msg.setContent(JSON.toJSONString(chatMessage));
        avimConversation.sendMessage(msg, new CustomConversitonCallBack(callBack));
    }






}
