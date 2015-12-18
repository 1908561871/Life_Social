package cn.com.elex.social_life.support.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by zhangweibo on 2015/11/19.
 */
public  class CustomSignUpCallBack extends SignUpCallback{





    private MsgCallBack callBack;

    public CustomSignUpCallBack(MsgCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void done(AVException e) {

        if (e==null){
            callBack.success();
        }else{
            callBack.failure(e.getMessage());
        }
    }

}
