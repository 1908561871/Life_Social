package cn.com.elex.social_life.model.imodel;

import android.content.Context;

import com.avos.avoscloud.AVGeoPoint;

import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.LocationMsg;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.callback.LocationCallBack;
import cn.com.elex.social_life.support.location.LocationManager;

/**
 * Created by zhangweibo on 2015/11/3.
 */
public  class MainTabModel implements IMainTabModel {



    @Override
    public void loginIM(IMLoginCallBack callBack,Context context) {
        //登录IM
        ClientUserManager.getInstance().imLogin(callBack,context);
    }

    @Override
    public void exit(IMLoginCallBack callBack) {

        ClientUserManager.getInstance().imQuit(callBack);

    }

    @Override
    public void updateLocation() {
        LocationManager.getInstance().obtainCurrentLocation(new LocationCallBack() {
            @Override
            public void locSuccess(LocationMsg msg) {
                UserInfo info=ClientUserManager.getInstance().obtainCurrentUser();
                info.setGeoPoint(new AVGeoPoint(msg.getLat(),msg.getLon()));
                info.saveInBackground(null);
            }

            @Override
            public void locFailure() {

            }
        });
    }


}
