package cn.com.elex.social_life.support.callback;

import cn.com.elex.social_life.model.bean.LocationMsg;

/**定位回调
 * Created by zhangweibo on 2015/12/2.
 */
public interface LocationCallBack {


    void locSuccess(LocationMsg msg);

    void locFailure();



}
