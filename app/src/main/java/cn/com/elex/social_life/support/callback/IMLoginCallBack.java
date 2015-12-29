package cn.com.elex.social_life.support.callback;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import cn.com.elex.social_life.cloud.ClientUserManager;

/**
 * Created by zhangweibo on 2015/11/11.
 */
public abstract class IMLoginCallBack  extends AVIMClientCallback {


    @Override
    public void done(AVIMClient avimClient, AVIMException e) {

        if (e==null){
            ClientUserManager.getInstance().setClient(avimClient);
            onsuccess();
        }else{
            failure(e.getMessage());
        }
    }


    public abstract  void onsuccess();

    public abstract  void failure(String error);
}
