package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.MainTab;
import cn.com.elex.social_life.presenter.MainTabPresenter;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IMainTabView;


public class MainTabActivity extends BaseActivity implements IMainTabView,TabHost.OnTabChangeListener {
    //内容显示区
    @Bind(R.id.fl_content)
    FrameLayout fl_content;
    @Bind(R.id.fth_tabhost)
    FragmentTabHost tabHost;
    private long exitTime;
    private MainTabPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        initTab();
        presenter=new MainTabPresenter(this);
        finishOtherActivty();
        //初始化数据加载
        presenter.initLoad();
    }



    public void initTab(){
        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            tabHost.getTabWidget().setShowDividers(0);
        }
        MainTab[]tabs= MainTab.values();
        for(int i = 0; i <tabs.length; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tabs[i].getTitle()));
            tabSpec.setIndicator(getTabItemView(tabs[i]));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, tabs[i].getClz(), null);
        }
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
    }


    public View getTabItemView(MainTab tab){
         View  view= getLayoutInflater().inflate(R.layout.activity_main_tab_item,null);
         TextView tv_title= (TextView) view.findViewById(R.id.tv_title);
         ImageView  iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
         tv_title.setText(getString(tab.getTitle()));
         iv_icon.setImageDrawable(getResources().getDrawable(tab.getDrawable()));
         return view;
    }


    @Override
    public void onTabChanged(String tabId) {

    }


    //选项切换
    @OnClick(R.id.fth_tabhost)
    public void tabChanged()
    {



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.show(R.string.press_again_exit);
            exitTime = System.currentTimeMillis();
        } else {
            closeAllActivities();
            presenter.exit();
            System.exit(0);
        }
    }

}
