package cn.com.elex.social_life.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.presenter.ZoneDynamicPresenter;
import cn.com.elex.social_life.support.view.DividerItemDecoration;
import cn.com.elex.social_life.support.view.cjj.MaterialRefreshLayout;
import cn.com.elex.social_life.support.view.cjj.MaterialRefreshListener;
import cn.com.elex.social_life.ui.adapter.ZoneDynamicAdapter;
import cn.com.elex.social_life.ui.base.BaseFragment;
import cn.com.elex.social_life.ui.iview.IZoneDynamicView;

/**
 * Created by zhangweibo on 2015/12/24.
 */
public class DynamicFragment extends BaseFragment implements IZoneDynamicView {


    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.log_refresh)
    MaterialRefreshLayout logRefresh;
    /*@Bind(R.id.scrollView)
    NestedScrollView scrollView;*/
    private ZoneDynamicAdapter adapter;
    private List<PublishLogBean> publishLogs;
    //当前页
    private int pageSize;
    //每页数量
    private int pageNum = 10;
    private ZoneDynamicPresenter presenter;

    private UserInfo userInfo;
    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void onViewFirstLoading() {

    }

    @Override
    protected void onViewReloading() {

    }

    @Override
    protected void setViewData() {
        initListen();
        initRecycleView();
        initRefreshView();
    }

    @Override
    protected void preloadDataInit() {
        presenter = new ZoneDynamicPresenter(this);
        publishLogs=new ArrayList<PublishLogBean>();
        adapter = new ZoneDynamicAdapter(getActivity(), publishLogs);
    }

    public void initListen() {

       /* scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams params = scrollView.getLayoutParams();
                int height = params.height;
                if (height < ScreenUtil.getHeight(getActivity())) {
                    params.height = height;
                }
                //    recycleView.setLayoutParams(params);
                //  if (Build.VERSION.SDK_INT>)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });*/

    }

    public void initRecycleView() {

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    public void initRefreshView(){
        logRefresh.setLoadMore(true);
        logRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                logRefresh.setLoadMore(true);
                pageSize=0;
                presenter.getData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                presenter.getData();
            }
        });
    }
    @Override
    public UserInfo getUserInfo() {
        return userInfo;
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


    public void obtainUserInfo(UserInfo userInfo){
        this.userInfo=userInfo;
        logRefresh.autoRefresh();
    }



    public void setRefresh(boolean isRefresh){

        logRefresh.setCanRefresh(isRefresh);

    }


}
