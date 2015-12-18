package cn.com.elex.social_life.model.imodel;

import com.avos.avoscloud.AVGeoPoint;

import cn.com.elex.social_life.support.callback.CustomFindCallBack;

/**
 * Created by zhangweibo on 2015/12/9.
 */
public interface IFindNearPeopleModel {


     void obtainNearPeopleData(AVGeoPoint point, CustomFindCallBack callBack, int skip) ;

}
