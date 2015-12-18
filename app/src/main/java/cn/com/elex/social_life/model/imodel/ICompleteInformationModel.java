package cn.com.elex.social_life.model.imodel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import cn.com.elex.social_life.model.bean.UserInfo;

/**
 * Created by zhangweibo on 2015/11/24.
 */
public interface ICompleteInformationModel {


    void takePhoto(int requestCode, Activity context);

    void pickPhoto(int requestCode, Activity context);

    void cropPhoto(String path, Activity context, int requestCode);

     void uploadData(final String nicker, final int sexType, Bitmap bimap, final SaveCallback callback) ;

     void goToMainTabActivity(Context context) ;

}
