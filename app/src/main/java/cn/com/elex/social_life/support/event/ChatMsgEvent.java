package cn.com.elex.social_life.support.event;

import com.avos.avoscloud.im.v2.AVIMConversation;

import cn.com.elex.social_life.model.bean.ChatMessage;

/**聊天室事件
 * Created by zhangweibo on 2015/8/3.
 */
public class ChatMsgEvent {

   private ChatMessage msg;

    private AVIMConversation conversation;

    public ChatMsgEvent(ChatMessage msg,AVIMConversation conversation) {
        this.msg = msg;
        this.conversation=conversation;
    }

    public AVIMConversation getConversation() {
        return conversation;
    }

    public ChatMessage getMsg() {
        return msg;
    }
}
