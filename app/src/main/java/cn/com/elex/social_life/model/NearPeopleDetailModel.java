package cn.com.elex.social_life.model;

import cn.com.elex.social_life.cloud.data.DataQuery;
import cn.com.elex.social_life.model.imodel.INearPeopleDetailModel;
import cn.com.elex.social_life.support.callback.DataQueryCallBack;

/**
 * Created by zhangweibo on 2015/12/25.
 */
public class NearPeopleDetailModel  implements INearPeopleDetailModel{
    @Override
    public void obtainUserInfo(String userName, DataQueryCallBack callBack) {
        DataQuery.queryUserByUserName(userName, callBack);
    }

}
