package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.support.view.indicator.InfiniteViewPager;
import cn.com.elex.social_life.support.view.indicator.LinePageIndicator;
import cn.com.elex.social_life.ui.adapter.GuideIntroduceAdpter;
import cn.com.elex.social_life.ui.base.BaseActivity;

/**
 * Created by zhangweibo on 2015/11/17.
 */
public class GuideActivity extends BaseActivity {


    @Bind(R.id.indicator)
    LinePageIndicator indicator;
    @Bind(R.id.viewpager)
    InfiniteViewPager viewpager;
    private GuideIntroduceAdpter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guide);
        ButterKnife.bind(this);
        adapter = new GuideIntroduceAdpter(this);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
        viewpager.setAutoScrollTime(5000);
    }


    @Override
    protected void onResume() {
        if (viewpager != null) {
            viewpager.startAutoScroll();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (viewpager != null) {
            viewpager.stopAutoScroll();
        }
        super.onPause();
    }


    @OnClick({R.id.tv_login})
    public void login() {

        // goToPagerByIntent(LoginActivity.class);
        goToPagerByIntent(LoginActivity.class);

    }



  /*  @OnClick(R.id.tv_register)
    public void register(){
        goToPagerByIntent(RegisterByEmailActivity.class);
    }*/

    @OnClick(R.id.tv_register)
    public void register() {
        goToPagerByIntent(RegisterByEmailActivity.class);
    }


}
