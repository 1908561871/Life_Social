package cn.com.elex.social_life.model.imodel;

import android.app.Activity;
import android.content.Intent;

import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;

import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.support.callback.CustomSaveCallBack;
import cn.com.elex.social_life.support.callback.LocationCallBack;

/**
 * Created by zhangweibo on 2015/12/1.
 */
public interface IPublishLogModel {

     void selectPhoto(Activity context, ArrayList<String> mSelectPath, int count);


     void commitData(PublishLogBean data, CustomSaveCallBack callback);



     void obtainAddress(LocationCallBack callBack);

}
