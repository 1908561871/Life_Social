package cn.com.elex.social_life.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.ui.activity.PublishLogActivity;
import cn.com.elex.social_life.ui.activity.ZoneDynamicActivity;
import cn.com.elex.social_life.ui.base.BaseFragment;


/**
 * Created by Administrator on 2015/10/30.
 */
public class MineTabFragment extends BaseFragment {


    @Bind(R.id.nickername)
    TextView nickerName;
    private UserInfo info;

    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_tab_mine;
    }

    @Override
    protected void onViewFirstLoading() {

    }

    @Override
    protected void onViewReloading() {

    }

    @Override
    protected void setViewData() {
        userIcon.setImageURI(Uri.parse(info.getHeadIconUrl().getUrl()));
        nickerName.setText(info.getNickName());
    }

    @Override
    protected void preloadDataInit() {

        info = (UserInfo) AVUser.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Bind(R.id.user_info)
    RelativeLayout userInfo;
    @Bind(R.id.share)
    RelativeLayout share;
    @Bind(R.id.posting)
    RelativeLayout posting;
    @Bind(R.id.setting)
    RelativeLayout setting;
    @Bind(R.id.more)
    RelativeLayout more;
    @Bind(R.id.user_icon)
    SimpleDraweeView userIcon;

    @OnClick(R.id.posting)
   public void post(){
        goToPagerByIntent(PublishLogActivity.class);
    }

    @OnClick(R.id.zone)
    public void zone(){
        goToPagerByIntent(ZoneDynamicActivity.class);
    }

}
