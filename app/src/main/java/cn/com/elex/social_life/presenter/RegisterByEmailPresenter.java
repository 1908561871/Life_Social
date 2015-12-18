package cn.com.elex.social_life.presenter;

import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.RegisterByEmialModel;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.IRegisterByEmailModel;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.util.StringUtils;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.activity.CompleteInformationActivity;
import cn.com.elex.social_life.ui.activity.RegisterByEmailActivity;
import cn.com.elex.social_life.ui.iview.IRegisterByEmailView;

/**
 * Created by zhangweibo on 2015/11/20.
 */
public class RegisterByEmailPresenter {

  private IRegisterByEmailModel model;

  private IRegisterByEmailView view;


    public RegisterByEmailPresenter(IRegisterByEmailView view) {
        this.view = view;
        model=new RegisterByEmialModel();
    }

    public void register(final String email, final String pwd){
      if (!StringUtils.checkEmail(email))
      {
          ToastUtils.show(GlobalApplication.getInstance().getString(R.string.email_format_error));
          return;
      }

      if (TextUtils.isEmpty(pwd)|| pwd.length()<6){
          ToastUtils.show(GlobalApplication.getInstance().getString(R.string.password_format_error));
          return;
      }
        view.showLoadingView();
       model.signUpByEmial(email, pwd, new MsgCallBack() {
           @Override
           public void success() {
               view.hideLoadingView();
               ToastUtils.show(GlobalApplication.getInstance().getString(R.string.register_success));
               model. goToCompleteInformation((UserInfo) AVUser.getCurrentUser(), pwd, (RegisterByEmailActivity) view);
           }
           @Override
           public void failure(String msg) {
               ToastUtils.show(msg);
               view.hideLoadingView();
           }
       });
    }




}
