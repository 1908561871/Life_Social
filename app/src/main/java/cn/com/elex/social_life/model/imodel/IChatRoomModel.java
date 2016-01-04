package cn.com.elex.social_life.model.imodel;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

import cn.com.elex.social_life.support.callback.MessageQueryCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public interface IChatRoomModel {



    void sendMessage(AVIMConversation conversation,AVIMMessage msg, MsgCallBack callBack);

    void receiveMessage(AVIMMessage msg);


    void obtainHistory(AVIMConversation conversation, int pageNum,AVIMMessage message, MessageQueryCallBack callBack);


}
