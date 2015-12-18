package cn.com.elex.social_life.model.bean;

import android.text.Spanned;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangweibo on 2015/11/10.
 */
public class ChatMessage  implements Serializable{

    /**
     * 信息类型
     */
    ChatMsgType chatMsgType;

    /**
     * 发送类型（对方、系统和自己）
     */
    ChatMsgSendType sendType;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户图片
     */
    private String userIcon;

    private List<String> members;


    /**
     * 回话ID
     */
    private String conversationID;

    public ChatMsgType getChatMsgType() {
        return chatMsgType;
    }

    public void setChatMsgType(ChatMsgType chatMsgType) {
        this.chatMsgType = chatMsgType;
    }

    public ChatMsgSendType getSendType() {
        return sendType;
    }

    public void setSendType(ChatMsgSendType sendType) {
        this.sendType = sendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }
}
