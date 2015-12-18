package cn.com.elex.social_life.model.imodel;

import android.content.Context;

import com.avos.avoscloud.LogInCallback;

/**
 * Created by zhangweibo on 2015/11/2.
 */
public  interface ILoginModel {

    void login(String userName, String pwd, LogInCallback callback);

    boolean vertifyUserInfo(String userName, String pwd);

    void loginSuccess(Context context);

    void goToSignUp(Context context);


}
