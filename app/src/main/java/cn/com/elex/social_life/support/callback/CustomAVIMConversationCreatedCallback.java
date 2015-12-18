package cn.com.elex.social_life.support.callback;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public abstract class CustomAVIMConversationCreatedCallback extends AVIMConversationCreatedCallback {





    public void done(AVIMConversation avimConversation, AVIMException e) {

        if (e==null){

            success(avimConversation);

        }else{
            failure(e.getMessage());
        }

    }

    protected   abstract  void  success(AVIMConversation avimConversation);

    protected   abstract  void  failure(String error);


}
