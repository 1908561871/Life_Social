package cn.com.elex.social_life.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import cn.com.elex.social_life.model.ZoneDynamicModel;
import cn.com.elex.social_life.model.imodel.IZoneDynamicModel;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.iview.IZoneDynamicView;

/**
 * Created by zhangweibo on 2015/12/28.
 */
public class ZoneDynamicPresenter {

    private IZoneDynamicModel model;

    private IZoneDynamicView view;

    public ZoneDynamicPresenter(IZoneDynamicView view) {
        this.view = view;
        model=new ZoneDynamicModel();
    }





    public void getData(){

        model.getZoneDynamic(view.getUserInfo(), new FindCallback() {
            @Override
            public void done(List list, AVException e) {
                view.closeLoadView();
                if (list.size()<view.getPageNum()) {
                    view.setLoadMoreStatue(false);
                }
                if (view.getPageSize()==0)
                {
                    view.getPublishLog().clear();
                }
                view.getPublishLog().addAll(list);
                view.updateLogs();
                view.setPageSize(view.getPageSize()+1);
            }
        },view.getPageNum(),view.getPageSize());

    }

}
