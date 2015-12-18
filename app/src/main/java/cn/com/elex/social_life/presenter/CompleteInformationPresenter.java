package cn.com.elex.social_life.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.CompleteInformationModel;
import cn.com.elex.social_life.model.imodel.ICompleteInformationModel;
import cn.com.elex.social_life.support.callback.CustomSaveCallBack;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.iview.ICompleteInformationView;

/**
 * Created by zhangweibo on 2015/11/24.
 */
public class CompleteInformationPresenter {

    private ICompleteInformationModel model;

    private ICompleteInformationView view;

    public CompleteInformationPresenter(ICompleteInformationView view) {
        this.view = view;
        model=new CompleteInformationModel();
    }

    public void takePhoto(){
        model.takePhoto(view.getRequestCode(), (Activity) view);
    }

    public void pickPhoto(){
        model.pickPhoto(view.getRequestCode(), (Activity) view);
    }

    public void cropPhoto(){
        view.setRequestCode(CompleteInformationModel.CROP_PHOTO);
        model.cropPhoto(view.getImagePath(), (Activity) view,view.getRequestCode());
    }

    public void completeRegister(){
        if (view.getBitmap()==null )
        {
            ToastUtils.show(R.string.please_select_headicon);
            return;
        }

        if (TextUtils.isEmpty(view.getNicker()))
        {
            ToastUtils.show(R.string.please_input_your_nicker);
            return;
        }

        if (view.getSexType()==0){
            ToastUtils.show(R.string.please_select_sex);
            return ;
        }
        model.uploadData(view.getNicker(), view.getSexType(), view.getBitmap(), new CustomSaveCallBack() {
            @Override
            public void success() {
                model.goToMainTabActivity((Context) view);
            }
            @Override
            public void failure(String error) {
ToastUtils.show(error);
            }
        });

    }


}
