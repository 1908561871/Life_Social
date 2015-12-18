package cn.com.elex.social_life.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.com.elex.social_life.sys.exception.GlobalApplication;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午1:28
 * 描述:
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected GlobalApplication mApp;
    protected ProgressDialog mLoadingDialog;
    protected  boolean isFirstLoading ;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        mApp = GlobalApplication.getInstance();
        mLoadingDialog = new ProgressDialog(activity);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setMessage("数据加载中...");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        isFirstLoading=true;
        preloadDataInit();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (getResourceLyoutID() != 0) {
            View view = LayoutInflater.from(getActivity()).inflate(getResourceLyoutID(), null);
            ButterKnife.bind(this, view);
            setViewData();
            if (isFirstLoading){
                onViewFirstLoading();
                isFirstLoading=false;
            }{
                onViewReloading();
            }
            return view;
        } else {
            return super.onCreateView(inflater,container,savedInstanceState);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){

        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    protected  abstract  int getResourceLyoutID();


    /**
     * 视图第一次加载
     */
    protected  abstract  void onViewFirstLoading();

    /**
     * 视图重新加载
     */
    protected  abstract  void onViewReloading();


    /**
     * 设置数据
     */
    protected  abstract  void setViewData();
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }


    /**
     * 预加载数据初始化
      */
    protected abstract void preloadDataInit();


    /**
     *跳转页面
     * @param cls
     */
    public void goToPagerByIntent(Class cls){
        Intent intent =new Intent(getActivity(),cls);
        startActivity(intent);

    }


}