package cn.com.elex.social_life.ui.iview;

import android.graphics.Bitmap;


import cn.com.elex.social_life.model.bean.UserInfo;

/**
 * Created by zhangweibo on 2015/11/24.
 */
public interface ICompleteInformationView {

     String getNicker();

     String getPassWord();
     int getSexType();

     Bitmap getBitmap();

     int getRequestCode();

     void setRequestCode(int code);

     String getImagePath();


}
