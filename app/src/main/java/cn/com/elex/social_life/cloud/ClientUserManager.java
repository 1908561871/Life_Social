package cn.com.elex.social_life.cloud;

import android.content.Context;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.sys.receiver.im.AVImClientReceiver;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public class ClientUserManager {

    AVIMClient client;

    AVUser user;

    AVImClientReceiver avImClientReceiver;

    private static class SingleClientUserManager {
        private static final ClientUserManager instance = new ClientUserManager();
    }

    public static ClientUserManager getInstance() {
        return SingleClientUserManager.instance;
    }

    private ClientUserManager() {

    }


    public  AVIMClient obtainCurrentClentUser(){

        if (client==null){
            if (AVUser.getCurrentUser()!=null)
            client = AVIMClient.getInstance(AVUser.getCurrentUser().getUsername(),"ANDROID");
        }
        return client;
    }



    public  UserInfo  obtainCurrentUser(){

        if (user==null){
            user=  AVUser.getCurrentUser();
        }
        return (UserInfo) user;
    }


    public  void clearUserMsg(){

        client=null;
        user=null;
    }

    /**
     * IM登录
     * @param callBack
     */

    public  void imLogin(IMLoginCallBack callBack, Context context){
        AVIMClient client=obtainCurrentClentUser();
        if (client!=null)
        {
            client.open(callBack);
            if (avImClientReceiver==null){
                avImClientReceiver=new AVImClientReceiver(context);
            }
            client.setClientEventHandler(avImClientReceiver);
        }
    }

    /**
     * IM退出
     * @param callBack
     */
    public void imQuit(IMLoginCallBack callBack){
        AVIMClient client=obtainCurrentClentUser();
        if (client!=null)
        {
            client.close(callBack);
        }
    }


    /**
     * 使用用户名登录
     * @param useName
     * @param pwd
     * @param callback
     */

    public  void loginByUserName(String useName ,String pwd, LogInCallback callback)
    {
        AVUser.logInInBackground(useName, pwd, callback,UserInfo.class);

    }


    /**
     * 用户退出
     * @param callBack
     */
    public void userQuit(IMLoginCallBack callBack){

        imQuit(callBack);
    }


    public void setClient(AVIMClient client) {
        this.client = client;
    }
}
