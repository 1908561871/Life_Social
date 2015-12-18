package cn.com.elex.social_life.support.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by zhangweibo on 2015/11/9.
 */
public abstract class CustomSaveCallBack extends SaveCallback {



    @Override
    public void done(AVException e) {

        if (e==null){
            success();
        }else{
            failure(e.getMessage());
        }
    }
    public abstract  void success();
    public abstract  void failure(String error);
}
