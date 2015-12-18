package cn.com.elex.social_life.model;

import com.avos.avoscloud.SignUpCallback;

import cn.com.elex.social_life.cloud.data.DataStorage;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.IRegisterModel;
import cn.com.elex.social_life.support.util.StringUtils;

/**
 * Created by zhangweibo on 2015/11/2.
 */
public class RegisterModel  implements IRegisterModel {


    @Override
    public void signUp(String name, String pwd,SignUpCallback callback) {
        UserInfo info=new UserInfo();
        info.setUsername(name);
        info.setPassword(pwd);
        DataStorage.signUpByUserName(info, callback);
    }

    @Override
    public boolean vertifyUserName(String name) {

        if ((name.length()>=6) && StringUtils.isStartWithCharacter(name)){

            return  true;
        }

        return false;
    }

    @Override
    public boolean vertifyPassWord(String pwd, String confirmPwd) {
        if (pwd.length()>=6 && pwd.equals(confirmPwd)){
            return true;
        }
        return false;
    }


}
