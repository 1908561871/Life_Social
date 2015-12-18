package cn.com.elex.social_life.support.callback;

import java.util.List;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public interface DataQueryCallBack {

    void success(List list);

    void failure(String msg);


}
