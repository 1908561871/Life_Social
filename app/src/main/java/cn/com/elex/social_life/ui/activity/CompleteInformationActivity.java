package cn.com.elex.social_life.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.CompleteInformationModel;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.presenter.CompleteInformationPresenter;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.util.MemoryControl;
import cn.com.elex.social_life.support.view.dialog.ActionSheetDialog;
import cn.com.elex.social_life.support.view.dialog.base.OnOperItemClickL;
import cn.com.elex.social_life.support.view.imageview.CircleImageView;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.ICompleteInformationView;

/**
 * Created by zhangweibo on 2015/11/19.
 * 完善资料
 */
public class CompleteInformationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener ,ICompleteInformationView{


    @Bind(R.id.nickername)
    EditText nickername;
    @Bind(R.id.user_icon)
    CircleImageView userIcon;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.mail)
    RadioButton mail;
    @Bind(R.id.femail)
    RadioButton femail;
    CompleteInformationPresenter presenter;
    private int requestCode;
    private String path;
    private Bitmap userBitmap;
    private String password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_info);
        ButterKnife.bind(this);
        setHeader(R.string.complete_information);
        radioGroup.setOnCheckedChangeListener(this);
        presenter=new CompleteInformationPresenter(this);
        password=getIntent().getStringExtra("pass_word");
    }


    @OnClick(R.id.user_icon)
    public void choosePhoto() {
        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, userIcon);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    requestCode= CompleteInformationModel.PICKER_PHOTO;
                    presenter.pickPhoto();
                } else if (position == 1) {
                    requestCode= CompleteInformationModel.TAKE_PHOTO;
                    presenter.takePhoto();
                }
                dialog.dismiss();
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.mail) {
            mail.setBackground(getResources().getDrawable(R.drawable.circle_green));
            femail.setBackground(getResources().getDrawable(R.drawable.circle_gray));
        }else{
            mail.setBackground(getResources().getDrawable(R.drawable.circle_gray));
            femail.setBackground(getResources().getDrawable(R.drawable.circle_red));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            if (requestCode==CompleteInformationModel.PICKER_PHOTO && data.getData()!=null)
            {
                path=getRealPathFromURI(data.getData());
                   /* path=getRealPathFromURI(data.getData());
                    presenter.cropPhoto();*/
            }else if (requestCode==CompleteInformationModel.TAKE_PHOTO){
                path=MemoryControl.getTempImageFile().getPath();
                  /*  path=MemoryControl.getTempImageFile().getPath();
                    presenter.cropPhoto();*/

            }/*else if (requestCode==CompleteInformationModel.CROP_PHOTO){
                    userBitmap=GlobalApplication.getInstance().tempBitmap;
                    userIcon.setImageBitmap(userBitmap);
            }*/
        //   GlobalApplication.getInstance().tempBitmap=BitmapUtil.compressImageFromFile(path);
            userBitmap=BitmapUtil.compressImageFromFile(path);
            userIcon.setImageBitmap(userBitmap);
        }
    }

    @OnClick(R.id.btn_complete)
    public void completeRegister(){
        presenter.completeRegister();
    }


    @Override
    protected void onDestroy() {
        GlobalApplication.getInstance().recycleBitmap();
        super.onDestroy();
    }


    @Override
    public String getNicker() {
        return nickername.getText().toString();
    }

    @Override
    public String getPassWord() {
        return password;
    }

    @Override
    public int getSexType() {
        return mail.isChecked()?1:femail.isChecked()?2:0;
    }

    @Override
    public Bitmap getBitmap() {
        return userBitmap;
    }

    @Override
    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public void setRequestCode(int code) {
        requestCode=code;
    }

    @Override
    public String getImagePath() {
        return path;
    }


    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


}
