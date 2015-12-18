package cn.com.elex.social_life.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.model.LoginModel;
import cn.com.elex.social_life.model.imodel.ILoginModel;
import cn.com.elex.social_life.ui.activity.LoginActivity;
import cn.com.elex.social_life.ui.iview.ILoginView;
import cn.com.elex.social_life.support.util.ToastUtils;

/**
 * Created by zhangweibo on 2015/11/3.
 */
public class LoginPresenter {

    ILoginModel loginModel;
    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel=new LoginModel();
    }


    public void login(String username,String userpwd){

        if (loginModel.vertifyUserInfo(username,userpwd)){
            loginView.showLoadView();
            loginModel.login(username, userpwd, new LogInCallback() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    loginView.hideLoadView();
                    if (avUser!=null){
                        loginModel.loginSuccess((LoginActivity)loginView);
                        ((LoginActivity)loginView).finish();
                    }else{
                        ToastUtils.show(GlobalApplication.getInstance(),e.getMessage());
                    }
                }
            });

        }
    }

    public void goToSignUp(){
        loginModel.goToSignUp((LoginActivity)loginView);
    }


}
