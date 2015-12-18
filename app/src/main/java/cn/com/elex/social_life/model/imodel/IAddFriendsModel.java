package cn.com.elex.social_life.model.imodel;

import cn.com.elex.social_life.support.callback.DataQueryCallBack;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public interface IAddFriendsModel {

    void search(String content, DataQueryCallBack callBack);

}
