/**
 * 
 */
package cn.com.elex.social_life.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.ClientUserManager;
import cn.com.elex.social_life.support.callback.IMLoginCallBack;
import cn.com.elex.social_life.support.event.IMClientOffLineEvent;
import cn.com.elex.social_life.support.view.dialog.base.BounceTopEnter;
import cn.com.elex.social_life.support.view.dialog.base.NormalDialog;
import cn.com.elex.social_life.support.view.dialog.base.OnBtnClickL;
import cn.com.elex.social_life.support.view.dialog.base.SlideBottomExit;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import cn.com.elex.social_life.ui.activity.GuideActivity;
import cn.com.elex.social_life.ui.activity.MainTabActivity;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import dmax.dialog.SpotsDialog;


/**
 * @author Tony Shen
 *
 */
public class BaseActivity extends FragmentActivity{

	public GlobalApplication app;
	public int networkType;
	public String networkName;
	private boolean isShowNet;
	private SpotsDialog dialog;

    protected Handler mHandler = new SafeHandler(this);
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		app = (GlobalApplication) this.getApplication();
		addActivityToManager(this);
		EventBus.getDefault().register(this);
	}
	
	protected  void addActivityToManager(Activity act) {
        if (!app.activityManager.contains(act)) {
        	 app.activityManager.add(act);
	    }
	}
	
	protected void closeAllActivities() {
		for (final Activity act : app.activityManager) {
			if (act != null) {
				act.finish();
			}
		}
	}
	
	protected  void delActivityFromManager(Activity act) {
        if (app.activityManager.contains(act)) {
        	app.activityManager.remove(act);
        }
	}
	

	protected String getCurrentActivityName() {
		int size = app.activityManager.size();
		if (size > 0) {
			return app.activityManager.get(size-1).getClass().getName();
		}
		return null;
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}


	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		delActivityFromManager(this);
		mHandler.removeCallbacksAndMessages(null);
		EventBus.getDefault().unregister(this);
		ButterKnife.unbind(this);
    }
	


    public static class SafeHandler extends Handler{
	    private final WeakReference<Activity> mActivity;
	    public SafeHandler(Activity activity) {
	        mActivity = new WeakReference<Activity>(activity);
	    }
	    @Override
	    public void handleMessage(Message msg) {
	        if(mActivity.get() == null) {
	            return;
	        }
	    }
	}


  /*  *//**
     * 设置标题
     *//*
    public void setHeadTitle(String titleName,boolean isBack,boolean isShowNet){
        TextView title= (TextView) findViewById(R.id.tv_title);
        title.setText(titleName);
		if (isBack){
			ImageView iv_back= (ImageView) findViewById(R.id.iv_back);
			iv_back.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
				}
			});
		}
        this.isShowNet=isShowNet;
		if (isShowNet && !isNetAvailabe){
			findViewById(R.id.rl_set_net).setVisibility(View.VISIBLE);
			findViewById(R.id.rl_set_net).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//todo
				}
			});
		}
    }
*/
	@Subscribe
  public void netChange(cn.com.elex.social_life.support.event.NetChangeEvent event){
		/*isNetAvailabe=event.isNetAvailable();
		RelativeLayout rl_set_net= (RelativeLayout) findViewById(R.id.rl_set_net);
		if (rl_set_net==null)
		{
			return;
		}
      if (isShowNet && !isNetAvailabe){
		  rl_set_net.setVisibility(View.VISIBLE);
		  rl_set_net.setOnClickListener(new View.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  //todo
			  }
		  });
      }else{
		  rl_set_net.setVisibility(View.GONE);
	  }*/
  }


 public void setHeader(boolean isBack,String titleName){
	 ImageView tv_left= (ImageView) findViewById(R.id.tv_left);
	 tv_left.setVisibility(isBack ? View.VISIBLE : View.INVISIBLE);
	 tv_left.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick(View v) {
			 finish();
		 }
	 });
	 ((TextView) findViewById(R.id.tv_title)).setText(titleName);
 }

	public void setHeader(boolean isBack,int  titleName) {

		setHeader(isBack,getResources().getString(titleName));
	}

		public void setHeader(String titleName){

	setHeader(false, titleName);

}
public void setHeader(int titleName){
		setHeader(false, getResources().getString(titleName));
}


public void setHeader(boolean isBack ,int title,int ActionName,View.OnClickListener clickListener) {

	setHeader(isBack,getResources().getString(title),getResources().getString(ActionName),clickListener);
}
		/**
         *
         * @param isBack   是否显示回退
         * @param titleName  标题名字
         * @param ActionName  右边的动作名字
         * @param clickListener 监听
         */

public void setHeader(boolean isBack ,String titleName,String ActionName,View.OnClickListener clickListener){
	ImageView tv_left= (ImageView) findViewById(R.id.tv_left);
	tv_left.setVisibility(isBack ? View.VISIBLE : View.INVISIBLE);
	tv_left.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	((TextView)findViewById(R.id.tv_title)).setText(titleName);
	TextView tv_right=(TextView) findViewById(R.id.tv_right);
	tv_right.setText(ActionName);
	tv_right.setOnClickListener(clickListener);
	tv_right.setVisibility(View.VISIBLE);
}



	public void showLoadingDialog(){
		showLoadingDialog(getResources().getString(R.string.data_loading));
	}


	public void showLoadingDialog(String content){
		if(dialog==null){
			dialog=new SpotsDialog(this,content);
		}
		dialog.show();
	}

	public void hideLoadingDialog(){
		if(dialog!=null){
			dialog.hide();
		}
	}

	/**
	 *跳转页面
	 * @param cls
	 */
	public void goToPagerByIntent(Class cls){
		Intent intent =new Intent(this,cls);
		startActivity(intent);
	}


	public void goToMainTabPager(){

		for (int i = app.activityManager.size() - 1; i >= 0; i--) {
			if (!(app.activityManager.get(i) instanceof MainTabActivity))
			{
				app.activityManager.get(i).finish();
				app.activityManager.remove(i);
			}

		}
	}


	public void showSoftInput(View view){


		InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(view, 0);

	}

	public void hideSoftInput(View view){

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

	}

	@Subscribe
	public void clientOffLine(IMClientOffLineEvent event)
	{

		if (this.getClass().isInstance(app.activityManager.get(app.activityManager.size()-1)) ){
			showClientOffDialog(event.getAvimClient());
		}

	}




	private void showClientOffDialog(AVIMClient avimClient) {
		BounceTopEnter bas_in = new BounceTopEnter();
		SlideBottomExit bas_out = new SlideBottomExit();
		final NormalDialog dialog = new NormalDialog(this);
		dialog.content("您的账号在其他设备上登录，是否重新登录？")//
				.style(NormalDialog.STYLE_TWO)//
				.titleTextSize(23)//
				.showAnim(bas_in)//
				.dismissAnim(bas_out)//
				.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnBtnClickL(
				new OnBtnClickL() {
					@Override
					public void onBtnClick() {
						AVUser.logOut();
						goToGuidePager();
						dialog.dismiss();
					}
				},
				new OnBtnClickL() {
					@Override
					public void onBtnClick() {
						reLogin();
						dialog.dismiss();
					}
				});

		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
				return true;
			}
		});
	}


	public void goToGuidePager(){

		Intent intent=new Intent(this, GuideActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);

	}


	public void reLogin(){

		ClientUserManager.getInstance().imLogin(new IMLoginCallBack() {
			@Override
			public void onsuccess() {

			}

			@Override
			public void failure(String error) {

			}
		},this);

	}


}
