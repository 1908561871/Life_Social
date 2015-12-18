package cn.com.elex.social_life.ui.iview;

import cn.com.elex.social_life.model.bean.ChatMessage;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public interface IChatRoomView {


    String getReplayContent();

    void refreshChatMsg(ChatMessage msg);


}
