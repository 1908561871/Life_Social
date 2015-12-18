package cn.com.elex.social_life.model;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.avos.avoscloud.LogInCallback;

import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.imodel.ILoginModel;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.activity.GuideActivity;
import cn.com.elex.social_life.ui.activity.MainTabActivity;
import cn.com.elex.social_life.ui.activity.RegisterActivity;

/**
 * Created by zhangweibo on 2015/11/3.
 */
public class LoginModel implements ILoginModel {
    @Override
    public void login(String userName, String pwd,LogInCallback callback) {
        ClientUserManager.getInstance().loginByUserName(userName, pwd, callback);
    }

    @Override
    public boolean vertifyUserInfo(String userName, String pwd) {
        if (!TextUtils.isEmpty(userName)&& !TextUtils.isEmpty(pwd))
        return true;
        return false;
    }

    @Override
    public void loginSuccess(final Context context) {

        Intent intent  =new Intent(context, MainTabActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void goToSignUp(Context context) {
        Intent intent  =new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }





}
