package cn.com.elex.social_life.presenter;

import com.avos.avoscloud.im.v2.AVIMMessage;

import java.util.List;

import cn.com.elex.social_life.model.ChatRoomModel;
import cn.com.elex.social_life.model.imodel.IChatRoomModel;
import cn.com.elex.social_life.support.callback.MessageQueryCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
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



   public void sendMessage(final AVIMMessage msg){

       chatRoomModel.sendMessage(chatRoomView.getAVIMConversation(), msg, new MsgCallBack() {
           @Override
           public void success() {
               chatRoomView.refreshChatMsg(msg);
               chatRoomView.clearContent();
           }

           @Override
           public void failure(String msg) {
               ToastUtils.show(msg);
           }
       });

   }




   public void obtainHistory(AVIMMessage message){

       chatRoomModel.obtainHistory(chatRoomView.getAVIMConversation(), chatRoomView.getPageNum(), message, new MessageQueryCallBack() {

           @Override
           public void success(List list) {
               chatRoomView.finishLoadMore();
               if (chatRoomView.getPageNum()>list.size())
               {
                   chatRoomView.closeLoadMore();
               }
               chatRoomView.updateHistory(list);
           }
           @Override
           public void failure(String error) {
               ToastUtils.show(error);
           }
       });
   }




}


