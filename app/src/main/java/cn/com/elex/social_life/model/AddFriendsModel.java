package cn.com.elex.social_life.model;

import cn.com.elex.social_life.cloud.data.DataQuery;
import cn.com.elex.social_life.model.imodel.IAddFriendsModel;
import cn.com.elex.social_life.support.callback.DataQueryCallBack;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public class AddFriendsModel implements IAddFriendsModel {


    @Override
    public void search(String content, DataQueryCallBack callBack) {
        DataQuery.queryUserByUserName(content,callBack);
    }
}
