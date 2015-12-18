package cn.com.elex.social_life.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.model.RegisterModel;
import cn.com.elex.social_life.model.imodel.IRegisterModel;
import cn.com.elex.social_life.ui.activity.RegisterActivity;
import cn.com.elex.social_life.ui.iview.IRegisterView;
import cn.com.elex.social_life.support.util.ToastUtils;

/**
 * Created by zhangweibo on 2015/11/2.
 */
public class RegisterPresenter {

    public RegisterActivity iRegisterView;

    public IRegisterModel iRegisterModel;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = (RegisterActivity) iRegisterView;
        iRegisterModel = new RegisterModel();
    }


    public void signUp(String userName, String pwd, String confirmPwd) {

        if (!iRegisterModel.vertifyUserName(userName))
        {
            ToastUtils.show(GlobalApplication.getInstance().getString(R.string.username_format_error));
            return;
        }
        if (!iRegisterModel.vertifyPassWord(pwd,confirmPwd)){
            ToastUtils.show(GlobalApplication.getInstance().getString(R.string.password_format_error));
            return;

        }
        iRegisterView.showLoadingView();
        iRegisterModel.signUp(userName, pwd, new SignUpCallback() {
            @Override
            public void done(AVException e) {
                iRegisterView.hideLoadingView();
                if (null == e) {
                    ToastUtils.show(GlobalApplication.getInstance().getString(R.string.register_success));

                } else {
                    ToastUtils.show(GlobalApplication.getInstance().getString(R.string.register_failure));
                }
            }
        });
    }


}
