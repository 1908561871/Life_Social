package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import org.buraktamturk.loadingview.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.presenter.RegisterPresenter;
import cn.com.elex.social_life.support.view.EditTextWithDeleteButton;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IRegisterView;


/**
 * Created by zhangweibo on 2015/11/2.
 * 注册
 */
public class RegisterActivity extends BaseActivity implements IRegisterView {


    @Bind(R.id.et_username)
    EditTextWithDeleteButton userName;
    @Bind(R.id.et_pwd)
    EditTextWithDeleteButton userPwd;
    @Bind(R.id.et_confirm_pwd)
    EditTextWithDeleteButton confirmPwd;
    @Bind(R.id.btn_complete)
    TextView complete;
    private RegisterPresenter presenter;
    @Bind(R.id.loadingview)
    LoadingView loadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
        setHeader(true, getResources().getString(R.string.register));
    }


    @Override
    public String getUserName() {
        return userName.getText();
    }

    @Override
    public String getPwd() {
        return userPwd.getText();
    }

    @Override
    public String getConfirmPwd() {
        return confirmPwd.getText();
    }

    @Override
    public void showLoadingView() {
        loadingView.setLoading(true);
        complete.setClickable(false);
    }

    @Override
    public void hideLoadingView() {
        loadingView.setLoading(false);
        complete.setClickable(true);
    }

    @OnClick(R.id.btn_complete)
    public void signUp() {
        presenter.signUp(getUserName(), getPwd(), getConfirmPwd());
    }


}
