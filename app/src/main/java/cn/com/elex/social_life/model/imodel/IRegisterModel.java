package cn.com.elex.social_life.model.imodel;

import com.avos.avoscloud.SignUpCallback;

/**
 * Created by zhangweibo on 2015/11/2.
 */
public  interface IRegisterModel {


    void signUp(String name, String pwd, SignUpCallback callback);

    boolean  vertifyUserName(String name);

    boolean  vertifyPassWord(String pwd, String confirmPwd);
}

