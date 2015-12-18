package cn.com.elex.social_life.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;

import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.model.imodel.ICompleteInformationModel;
import cn.com.elex.social_life.support.callback.CustomSaveCallBack;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.util.MemoryControl;
import cn.com.elex.social_life.support.util.PreferencesUtils;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.activity.CropActivity;
import cn.com.elex.social_life.ui.activity.MainTabActivity;

/**
 * Created by zhangweibo on 2015/11/24.
 */
public class CompleteInformationModel  implements ICompleteInformationModel{

    public static final int PICKER_PHOTO=1;
    public static final int TAKE_PHOTO=2;
    public static final int CROP_PHOTO=3;


    @Override
    public void takePhoto(int requestCode, Activity context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(MemoryControl.getTempImageFile()));
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    public void pickPhoto(int requestCode, Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    public void cropPhoto(String path, Activity context, int requestCode) {

        GlobalApplication.getInstance().tempBitmap=BitmapUtil.compressImageFromFile(path);
        context.startActivityForResult(new Intent(context, CropActivity.class), requestCode);
    }


    @Override
    public void uploadData( final String nicker, final int sexType,Bitmap bimap, final SaveCallback callback) {
        final UserInfo info= (UserInfo) AVUser.getCurrentUser();
        final AVFile file=new AVFile(info.getObjectId(),BitmapUtil.bitmapConverTobyteArray(bimap));
        file.addMetaData("width", bimap.getWidth());
        file.addMetaData("height", bimap.getHeight());
        //图片上传
        file.saveInBackground(new CustomSaveCallBack() {
            @Override
            public void success() {
                //数据上传
                info.setHeadIconUrl(file);
                info.setNickName(nicker);
                info.setSexType(sexType);
                info.saveInBackground(callback);
            }
            @Override
            public void failure(String error) {
                ToastUtils.show(R.string.upload_image_failure);
            }
        });
    }

    @Override
    public void goToMainTabActivity(Context context) {
        Intent intent=new Intent(context, MainTabActivity.class);
        context.startActivity(intent);
    }


}
