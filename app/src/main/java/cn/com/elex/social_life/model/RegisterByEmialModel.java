package cn.com.elex.social_life.model;

import android.content.Context;
import android.content.Intent;

import cn.com.elex.social_life.cloud.data.DataStorage;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.IRegisterByEmailModel;
import cn.com.elex.social_life.support.callback.MsgCallBack;
import cn.com.elex.social_life.support.util.PreferencesUtils;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.activity.CompleteInformationActivity;
import cn.com.elex.social_life.ui.activity.RegisterByEmailActivity;

/**
 * Created by zhangweibo on 2015/11/20.
 */
public class RegisterByEmialModel  implements IRegisterByEmailModel{

    @Override
    public void signUpByEmial(String emial, String pwd,MsgCallBack callBack) {
        DataStorage.signUpByEmail(emial,pwd,callBack);
    }

    @Override
    public void goToCompleteInformation(UserInfo info, String password,Context context) {
        PreferencesUtils.putString(context, PreferencesUtils.USER_NAME, info.getUsername());
        PreferencesUtils.putString(context, PreferencesUtils.PASS_WORD, password);
        Intent  intent =new Intent(context, CompleteInformationActivity.class);
        intent.putExtra("pass_word",password);
        context.startActivity(intent);
    }
}
