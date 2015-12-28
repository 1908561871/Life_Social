package cn.com.elex.social_life.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.support.util.ScreenUtil;
import cn.com.elex.social_life.ui.base.BaseFragment;

/**
 * Created by zhangweibo on 2015/12/24.
 */
public class InforMationFragment extends BaseFragment {
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;

    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_information;
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
    }

    @Override
    protected void preloadDataInit() {

    }


    public void initListen() {

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams params=scrollView.getLayoutParams();
                int height = params.height;
                if (height< ScreenUtil.getHeight(getActivity()))
                {
                    params.height=height;
                }
                //    recycleView.setLayoutParams(params);
                //  if (Build.VERSION.SDK_INT>)
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

    }

}
