package cn.com.elex.social_life.model.imodel;

import android.content.Context;

import cn.com.elex.social_life.support.callback.IMLoginCallBack;

/**
 * Created by zhangweibo on 2015/11/3.
 */
public interface IMainTabModel {


     void loginIM(IMLoginCallBack callBack,Context context);
     void exit(IMLoginCallBack callBack);
     void updateLocation();
}
