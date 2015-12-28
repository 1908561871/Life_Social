package cn.com.elex.social_life.model;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.IZoneDynamicModel;

/**
 * Created by zhangweibo on 2015/12/28.
 */
public class ZoneDynamicModel implements IZoneDynamicModel{
    @Override
    public void getZoneDynamic(UserInfo info, FindCallback callback,int pageNum,int pageSize) {
        AVQuery query = AVQuery.getQuery("PublishLogBean");
        query.whereEqualTo("UserInfo", info);
        query.include("UserInfo");
        query.include("imageFiles");
        query.setLimit(pageNum);
        query.skip(pageSize);
        query.findInBackground(callback);
    }
}
