package cn.com.elex.social_life.ui.iview;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

import java.util.List;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public interface IChatRoomView {

    //(AVIMConversation conversation, int pageNum, int pageSize,AVIMMessage message, MessageQueryCallBack callBack)
    String getReplayContent();

    void refreshChatMsg(AVIMMessage msg);

    void updateHistory(List<AVIMMessage> msgs);

    AVIMConversation getAVIMConversation();

    int getPageNum();

    int getPageSize();

    void closeLoadMore();

    void finishLoadMore();

    void clearContent();

}
