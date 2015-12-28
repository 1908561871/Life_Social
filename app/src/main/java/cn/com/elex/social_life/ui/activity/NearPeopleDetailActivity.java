package cn.com.elex.social_life.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.presenter.NearPeopleDetailPresenter;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.fragment.DynamicFragment;
import cn.com.elex.social_life.ui.fragment.InforMationFragment;
import cn.com.elex.social_life.ui.iview.INearPeopleDetailView;


/**
 * Created by zhangweibo on 2015/12/18.
 */
public class NearPeopleDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener, INearPeopleDetailView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.image)
    SimpleDraweeView image;
    @Bind(R.id.tv_userName)
    TextView tvUserName;
    @Bind(R.id.coordcontent)
    CoordinatorLayout coordcontent;

    private List<Fragment> fragments;

    private FragmentPagerAdapter adapter;

    private String userName;

    private NearPeopleDetailPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_people_detail);
        ButterKnife.bind(this);
        presenter = new NearPeopleDetailPresenter(this);
        initData();
        initView();
        presenter.obtainUserInfoData();

    }

    public void initData() {
        userName = getIntent().getStringExtra("userName");
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<Fragment>();
        fragments.add(new DynamicFragment());
        fragments.add(new InforMationFragment());
    }

    public void initView() {
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

//        if (i==0)
//        {
//            ((DynamicFragment)fragments.get(0)).setRefresh(true);
//
//        }else {
//            ((DynamicFragment)fragments.get(0)).setRefresh(false);
//
//        }
        ((DynamicFragment)fragments.get(0)).setRefresh(false);

    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void obtainUserInfoData(UserInfo userInfo) {

        if (image != null) {
            image.setImageURI(Uri.parse(userInfo.getHeadIconUrl().getThumbnailUrl(true, getResources().getDimensionPixelSize(R.dimen.big_icon_size2), getResources().getDimensionPixelSize(R.dimen.big_icon_size2))));
        }

        if (tvUserName != null) {
            tvUserName.setText(userInfo.getNickName());
        }
        obtainDynamicData(userInfo);

    }

    class MyPagerAdapter extends FragmentPagerAdapter {


        private String titles[] = new String[]{"动态", "资料"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        appbar.addOnOffsetChangedListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        appbar.removeOnOffsetChangedListener(this);
    }


    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }


    public void obtainDynamicData(UserInfo userInfo){

        if (userInfo!=null){
            DynamicFragment fragment= (DynamicFragment) fragments.get(0);
            fragment.obtainUserInfo(userInfo);
        }

    }


}
