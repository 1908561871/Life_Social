package cn.com.elex.social_life.model;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

import cn.com.elex.social_life.cloud.data.DataQuery;
import cn.com.elex.social_life.cloud.im.message.MessageSendCotrol;
import cn.com.elex.social_life.model.imodel.IChatRoomModel;
import cn.com.elex.social_life.support.callback.MessageQueryCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public class ChatRoomModel  implements  IChatRoomModel{

    @Override
    public void sendMessage(AVIMConversation conversation,AVIMMessage msg, MsgCallBack callBack) {
        MessageSendCotrol.sendTextMsg(conversation, callBack, msg);
    }

    @Override
    public void receiveMessage(AVIMMessage msg) {


    }

    @Override
    public void obtainHistory(AVIMConversation conversation, int pageNum, AVIMMessage message, MessageQueryCallBack callBack) {

        if (message==null)
        {
            DataQuery.queryHistoryMsg(conversation,pageNum,callBack);
        }else{
            DataQuery.queryHistoryMsg(conversation,pageNum,message,callBack);
        }


    }


}
