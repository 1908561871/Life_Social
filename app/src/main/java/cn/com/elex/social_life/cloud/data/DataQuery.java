package cn.com.elex.social_life.cloud.data;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

import java.util.List;

import cn.com.elex.social_life.support.callback.CustomFindCallBack;
import cn.com.elex.social_life.support.callback.DataQueryCallBack;

/**
 * Created by zhangweibo on 2015/11/2.
 */
public class DataQuery {

    /**
     * 根据用户名查找
     * @param userName
     * @param callBack
     */

    public  static void queryUserByUserName(String userName,DataQueryCallBack callBack){
        AVQuery<AVObject> query = new AVQuery<AVObject>("_User");
        query.whereEqualTo("username", userName);
        query.findInBackground(new CustomFindCallBack(callBack));
    }





}
