package cn.com.elex.social_life.presenter;

import java.util.List;

import cn.com.elex.social_life.model.NearPeopleDetailModel;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.INearPeopleDetailModel;
import cn.com.elex.social_life.support.callback.DataQueryCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.iview.INearPeopleDetailView;

/**
 * Created by zhangweibo on 2015/12/25.
 */
public class NearPeopleDetailPresenter {

    private INearPeopleDetailView view;

    private INearPeopleDetailModel model;

    public NearPeopleDetailPresenter(INearPeopleDetailView view) {
        this.view = view;
        model=new NearPeopleDetailModel();
    }



    public void obtainUserInfoData(){

        model.obtainUserInfo(view.getUserName(), new DataQueryCallBack() {
            @Override
            public void success(List list) {
                if (list.size()!=0)
                {
                    view.obtainUserInfoData((UserInfo)list.get(0));
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.show(msg);
            }
        });


    }

}
