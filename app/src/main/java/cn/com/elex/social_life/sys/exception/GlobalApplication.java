package cn.com.elex.social_life.sys.exception;


import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;


import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.buraktamturk.loadingview.LoadingConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.config.Constant;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.sys.receiver.im.IMMessageReceiver;


/**
 * @author yyx
 * @2015-5-15
 * @desperation:全局Application
 * 
 */
public class GlobalApplication extends Application {
	private static  String TAG="GlobalApplication";
	public List<Activity> activityManager;
	public HashMap<String, Object> session;
	private static GlobalApplication instance;
	public String deviceid;  // 设备ID
	public String osVersion; // 操作系统版本
	public String mobileType;// 手机型号
	public String version;   // app的versionName
	public int versionCode;  // app的versionCode
	public Bitmap tempBitmap;
	private UserInfo userInfo;
	@Override
	public void onCreate() {
		super.onCreate();
	    init();
        //测试
        test();
	}

    /**
     * 单例，返回一个实例
     * @return
     */
    public static GlobalApplication getInstance() {
        if (instance == null) {
			instance=new GlobalApplication();
        }
        return instance;
    }
	public void init(){
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
        //fresco初始化
		Fresco.initialize(getApplicationContext());
		resignEntity();
		AVUser.alwaysUseSubUserClass(UserInfo.class);
		//初始化数据存储
		AVOSCloud.initialize(this, Constant.APP_ID, Constant.APP_KEY);
		resignReceiver();
		instance = this;
		session = new HashMap<String, Object>();
		activityManager = new ArrayList<Activity>();
		initConfig();
		PackageManager manager = this.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			osVersion = Build.VERSION.RELEASE;
			mobileType = Build.MODEL;
			if (null != info) {
				version = info.versionName;
				versionCode = info.versionCode;
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 初始化实体
	 */
	public  void  resignEntity(){
		AVObject.registerSubclass(PublishLogBean.class);

	}



    public void test(){
    }


	public void initConfig(){

		new LoadingConfiguration()
				.setLoadingText(R.string.loading)
						// or
				.setLoading(false).setTextColor(getResources().getColor(R.color.orangle)) // you'll manually call setLoading(true) to LoadingView in order to show loading indicator.
				.setDefault(); // make this configuration default
	}

	/**
	 *添加接收器
	 */
    public void resignReceiver(){

		AVIMMessageManager.registerDefaultMessageHandler(new IMMessageReceiver());

	}


	/**
	 * 释放图片
	 */
	public void recycleBitmap(){

		if (tempBitmap!=null && !tempBitmap.isRecycled()){
			tempBitmap.recycle();
			System.gc();
		}

	}

}
