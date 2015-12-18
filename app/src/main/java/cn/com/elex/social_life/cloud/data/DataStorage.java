package cn.com.elex.social_life.cloud.data;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;

import java.sql.Time;

import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.callback.CustomSaveCallBack;
import cn.com.elex.social_life.support.callback.CustomSignUpCallBack;
import cn.com.elex.social_life.support.callback.DataSaveCallBack;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.util.TimeUtils;


/**
 * Created by zhangweibo on 2015/11/2.
 * 数据存储
 */
public class DataStorage {

    /**
     * 用户名注册
     * @param info
     * @param callback
     */

  public static void signUpByUserName(UserInfo info, SignUpCallback callback){
      info.signUpInBackground(callback);

  }

    /**
     * 邮箱注册
     * @param email
     * @param pwd
     * @param callback
     */
    public static UserInfo signUpByEmail(String email,String pwd, MsgCallBack callback){
        UserInfo info =new UserInfo();
        //邮箱设置为用户名
        info.setUsername(email);
        info.setEmail(email);
        info.setPassword(pwd);
        info.signUpInBackground(new CustomSignUpCallBack(callback));
        return info;
    }



    /**
     * 通过手机号码登录
      * @param phone
     * @param pwd
     * @param callback
     */

   public static void loginByMobilePhone(String phone,String pwd,LogInCallback callback){

    AVUser.loginByMobilePhoneNumberInBackground(phone, pwd, callback);

   }



    /**
     * 添加好友
     * @param saveCallBack
     */

    public static void addFriends(UserInfo addUser, CustomSaveCallBack saveCallBack){

        AVUser user=ClientUserManager.getInstance().obtainCurrentUser();
        AVRelation<UserInfo> infos=user.getRelation("friends");
        infos.add(addUser);
        user.saveInBackground(saveCallBack);
    }









}
