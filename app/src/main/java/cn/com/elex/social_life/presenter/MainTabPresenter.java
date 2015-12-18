package cn.com.elex.social_life.presenter;


import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.imodel.IMainTabModel;
import cn.com.elex.social_life.model.imodel.MainTabModel;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.iview.IMainTabView;

/**
 * Created by zhangweibo on 2015/11/3.
 */
public class MainTabPresenter {

    IMainTabModel mainTabModel;
    IMainTabView mainTabView;

    public MainTabPresenter(IMainTabView mainTabView) {
        this.mainTabView = mainTabView;
        mainTabModel=new MainTabModel();
    }


    public void initLoad(){

        mainTabModel.updateLocation();
        mainTabModel.loginIM(new IMLoginCallBack() {
            @Override
            public void onsuccess() {
                ToastUtils.show(R.string.im_login_success);

            }
            @Override
            public void failure(String error) {
                ToastUtils.show(R.string.im_login_failure);

            }
        });

    }


    public void exit(){

        mainTabModel.exit(new IMLoginCallBack() {
            @Override
            public void onsuccess() {
                ToastUtils.show(R.string.im_quit_success);
            }

            @Override
            public void failure(String error) {
                ToastUtils.show(R.string.im_quit_failure);
            }
        });

    }



}
