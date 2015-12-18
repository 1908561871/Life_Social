package cn.com.elex.social_life.support.location;

import android.util.Log;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.avos.avoscloud.AVGeoPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.elex.social_life.model.bean.LocationMsg;
import cn.com.elex.social_life.support.callback.LocationCallBack;
import cn.com.elex.social_life.sys.exception.GlobalApplication;

/**
 * Created by zhangweibo on 2015/12/2.
 */
public class LocationManager {

    private static class SingleLocationManager {
        private static final LocationManager instance = new LocationManager();
    }

    public static LocationManager getInstance() {
        return SingleLocationManager.instance;
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    public boolean hasLocation;

     //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取经度
                    amapLocation.getLongitude();//获取纬度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getRoad();//街道信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    mLocationClient.stopLocation();
                    hasLocation=true;
                    locationMsg.setAddr(amapLocation.getAddress());
                    locationMsg.setLat(amapLocation.getLatitude());
                    locationMsg.setLon( amapLocation.getLongitude());
                    locationMsg.setProvince(amapLocation.getProvince());
                    locationMsg.setCity(amapLocation.getCity());
                    if (callBack!=null){
                        callBack.locSuccess(locationMsg);
                    }
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    if (callBack!=null){
                        callBack.locFailure();
                    }
                }
            }
        }
    };
    private LocationMsg locationMsg;
    public LocationCallBack callBack;
    private LocationManager() {
        initLocation();
    }

    private void initLocation(){
        //初始化定位
        mLocationClient = new AMapLocationClient(GlobalApplication.getInstance());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        locationMsg=new LocationMsg();
    }



    /**
     * 获取当前位置
     * @param callBack
     */
    public void obtainCurrentLocation(LocationCallBack callBack){

        this.callBack=callBack;

        if (!mLocationClient.isStarted())
        {
            mLocationClient.startLocation();
        }
    }




    public  boolean hasLocation(){

        return  hasLocation;
    }



    public LocationMsg getCurrentLocation(){

        return  locationMsg;

    }


    public AVGeoPoint getGeoPoint(){
        return new AVGeoPoint(locationMsg.getLat(),locationMsg.getLon());
    }

}
