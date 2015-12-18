package cn.com.elex.social_life.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.IOException;
import java.net.URI;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.support.util.BitmapUtil;
import cn.com.elex.social_life.support.view.CropImageView;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.base.BaseActivity;

/**
 * Created by zhangweibo on 2015/11/23.
 */
public class CropActivity extends BaseActivity {


    @Bind(R.id.cropImageView)
    CropImageView cropImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        ButterKnife.bind(this);
        cropImageView.setImageBitmap(GlobalApplication.getInstance().tempBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.confirm)
    public void confirm(){
        GlobalApplication.getInstance().tempBitmap=cropImageView.getCroppedBitmap();
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.cancel)
    public void cancel(){
        setResult(RESULT_CANCELED);
        finish();
    }

}
