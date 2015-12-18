package cn.com.elex.social_life.ui.iview;

/**
 * Created by Administrator on 2015/10/30.
 */
public interface IRegisterView {
    String getUserName();

    String getPwd();

    String getConfirmPwd();

    void showLoadingView();

    void hideLoadingView();

}
