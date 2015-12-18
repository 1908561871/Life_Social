package cn.com.elex.social_life.model.imodel;

import com.avos.avoscloud.im.v2.AVIMConversation;

import java.util.ArrayList;
import java.util.List;

import cn.com.elex.social_life.model.bean.ChatMessage;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public interface IChatRoomModel {



    void sendMessage(ChatMessage msg);

    void receiveMessage(ChatMessage msg);

}
