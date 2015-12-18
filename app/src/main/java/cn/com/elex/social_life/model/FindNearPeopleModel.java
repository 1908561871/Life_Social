package cn.com.elex.social_life.model;

import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import java.util.ArrayList;

import cn.com.elex.social_life.model.imodel.IFindNearPeopleModel;
import cn.com.elex.social_life.support.callback.CustomFindCallBack;

/**
 * Created by zhangweibo on 2015/12/9.
 */
public class FindNearPeopleModel implements IFindNearPeopleModel{
    @Override
    public void obtainNearPeopleData(AVGeoPoint point, CustomFindCallBack callBack,int skip) {

        AVQuery<AVObject> query = new AVQuery<AVObject>("_User");
        query.whereNear("GeoPoint", point);
        query.setLimit(20);            //获取最接近用户地点的10条微博
        query.skip(skip);
        query.findInBackground(callBack);

    }
}
