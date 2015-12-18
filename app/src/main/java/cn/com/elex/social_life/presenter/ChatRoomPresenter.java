package cn.com.elex.social_life.presenter;

import cn.com.elex.social_life.model.ChatRoomModel;
import cn.com.elex.social_life.model.imodel.IChatRoomModel;
import cn.com.elex.social_life.ui.iview.IChatRoomView;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public class ChatRoomPresenter {

  private IChatRoomView chatRoomView;

  private IChatRoomModel chatRoomModel;

    public ChatRoomPresenter(IChatRoomView chatRoomView) {
        this.chatRoomView = chatRoomView;
        chatRoomModel=new ChatRoomModel();
    }



}


