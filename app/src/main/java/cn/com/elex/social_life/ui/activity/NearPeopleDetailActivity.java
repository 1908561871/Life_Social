package cn.com.elex.social_life.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.ui.base.BaseActivity;


/**
 * Created by zhangweibo on 2015/12/18.
 */
public class NearPeopleDetailActivity extends BaseActivity {

    @Bind(R.id.iv_people_bg)
    ImageView ivPeopleBg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collasing)
    CollapsingToolbarLayout collasing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_people_detail);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.btn_public_back);
        collasing.setTitle("这是测试账号");
        collasing.setCollapsedTitleTextColor(Color.WHITE);
        collasing.setContentScrimResource(R.color.orangle);
        collasing.setExpandedTitleColor(Color.WHITE);
    }


}
