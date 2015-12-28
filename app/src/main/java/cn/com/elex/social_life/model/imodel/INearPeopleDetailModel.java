package cn.com.elex.social_life.model.imodel;

import cn.com.elex.social_life.support.callback.DataQueryCallBack;

/**
 * Created by zhangweibo on 2015/12/25.
 */
public interface INearPeopleDetailModel {


     void obtainUserInfo(String userName, DataQueryCallBack callBack);



}
