package cn.com.elex.social_life.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.support.location.LocationManager;
import cn.com.elex.social_life.ui.base.BaseActivity;

/**
 * Created by Administrator on 2015/10/30.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.sdv_loadingview)
    SimpleDraweeView sdv_loadingview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ClientUserManager.getInstance().obtainCurrentUser()!=null)
                {
                    goToMainActivity();
                }else{
                    goToGuideActivity();
                }
            }
        }, 3000);


    }


    public void goToGuideActivity(){
        Intent intent=new Intent(this,GuideActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void initLoad(){
        LocationManager.getInstance().obtainCurrentLocation(null);
    }



    public void goToMainActivity(){
        Intent intent=new Intent(this,MainTabActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    public void finish() {
        overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        super.finish();
    }
}
