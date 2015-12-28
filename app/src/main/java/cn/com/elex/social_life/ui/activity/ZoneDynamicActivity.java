package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.presenter.ZoneDynamicPresenter;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.support.view.DividerItemDecoration;
import cn.com.elex.social_life.support.view.load.LoadViewHelper;
import cn.com.elex.social_life.ui.adapter.ZoneDynamicAdapter;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IZoneDynamicView;

/**
 * Created by zhangweibo on 2015/12/4.
 */
public class ZoneDynamicActivity extends BaseActivity implements IZoneDynamicView{

    @Bind(R.id.log_recycle)
    RecyclerView logRecycle;
    @Bind(R.id.log_refresh)
    MaterialRefreshLayout logRefresh;
    private ZoneDynamicAdapter adapter;
    private List<PublishLogBean> publishLogs;
    LoadViewHelper helper;
    //当前页
    private int pageSize;
    //每页数量
    private int pageNum=10;
    private ZoneDynamicPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_dynamic);
        ButterKnife.bind(this);
        presenter=new ZoneDynamicPresenter(this);
        setHeader(true,R.string.zone);
        helper=new LoadViewHelper(logRefresh);
        helper.showLoading();
        initRecycleView();
        initRefreshView();
    }



    public void initRecycleView() {
        publishLogs=new ArrayList<PublishLogBean>();
        adapter = new ZoneDynamicAdapter(this, publishLogs);
        logRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        logRecycle.setAdapter(adapter);
        logRecycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    public void initRefreshView(){
        logRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
               pageSize=0;
               presenter.getData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                presenter.getData();
            }
        });
        logRefresh.autoRefresh();
    }



    @Override
    public UserInfo getUserInfo() {
        return ClientUserManager.getInstance().obtainCurrentUser();
    }

    @Override
    public void updateLogs() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<PublishLogBean> getPublishLog() {
        return publishLogs;
    }

    @Override
    public void closeLoadView() {
        logRefresh.finishRefreshLoadMore();
        logRefresh.finishRefresh();
        helper.restore();
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize=pageSize;
    }

    @Override
    public void setLoadMoreStatue(boolean isLoadMore) {
        logRefresh.setLoadMore(isLoadMore);
    }


}
