package cn.com.elex.social_life.support.callback;


import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public  class  CustomConversitonCallBack extends AVIMConversationCallback {


    private MsgCallBack msgCallBack ;


    public CustomConversitonCallBack(MsgCallBack msgCallBack) {
        this.msgCallBack = msgCallBack;
    }
    @Override
    public void done(AVIMException e) {
        if (e==null){
            msgCallBack.success();
        }else{
            msgCallBack.failure(e.getMessage());
        }

    }

}
