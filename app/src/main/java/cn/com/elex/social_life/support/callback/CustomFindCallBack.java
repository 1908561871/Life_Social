package cn.com.elex.social_life.support.callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public  class CustomFindCallBack extends FindCallback{

    private DataQueryCallBack callBack;


    public CustomFindCallBack(DataQueryCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void done(List list, AVException e) {

        if (e==null){
            callBack.success(list);
        }else{
            callBack.failure(e.getMessage());
        }
    }


}
