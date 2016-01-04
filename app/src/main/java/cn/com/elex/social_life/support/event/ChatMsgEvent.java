package cn.com.elex.social_life.support.event;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;

/**聊天室事件
 * Created by zhangweibo on 2015/8/3.
 */
public class ChatMsgEvent {

   private AVIMMessage msg;

    private AVIMConversation conversation;

    public ChatMsgEvent(AVIMMessage msg,AVIMConversation conversation) {
        this.msg = msg;
        this.conversation=conversation;
    }

    public AVIMConversation getConversation() {
        return conversation;
    }

    public AVIMMessage getMsg() {
        return msg;
    }
}
