package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import org.buraktamturk.loadingview.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.presenter.RegisterByEmailPresenter;
import cn.com.elex.social_life.support.view.EditTextWithDeleteButton;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IRegisterByEmailView;

/**
 * Created by zhangweibo on 2015/11/19.
 */
public class RegisterByEmailActivity extends BaseActivity implements IRegisterByEmailView {


    @Bind(R.id.et_email)
    EditTextWithDeleteButton email;
    @Bind(R.id.et_pwd)
    EditTextWithDeleteButton password;
    @Bind(R.id.btn_register)
    TextView register;
    @Bind(R.id.loadingview)
    LoadingView loadingView;
    private RegisterByEmailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_email);
        ButterKnife.bind(this);
        setHeader(true,R.string.register);
        presenter = new RegisterByEmailPresenter(this);
    }


    @Override
    public String getEmailAddress() {
        return email.getText();
    }


    @Override
    public String getPassWord() {
        return password.getText();
    }

    @Override
    public void showLoadingView() {
        loadingView.setLoading(true);
        register.setClickable(false);
    }

    @Override
    public void hideLoadingView() {
        loadingView.setLoading(false);
        register.setClickable(true);
    }


    @OnClick(R.id.btn_register)
    public void register() {

        presenter.register(getEmailAddress(), getPassWord());

    }


}
