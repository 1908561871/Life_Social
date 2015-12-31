package cn.com.elex.social_life.sys.receiver.im;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMClientEventHandler;

import java.util.List;

import cn.com.elex.social_life.support.event.IMClientOffLineEvent;
import cn.com.elex.social_life.sys.exception.GlobalApplication;
import de.greenrobot.event.EventBus;

/**
 * Created by zhangweibo on 2015/12/31.
 */
public class AVImClientReceiver extends AVIMClientEventHandler {

    /**
     *断开连接
     * @param avimClient
     */

    private Context context;


    public AVImClientReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onConnectionPaused(AVIMClient avimClient) {

    }

    /**
     * 回复连接
     * @param avimClient
     */
    @Override
    public void onConnectionResume(AVIMClient avimClient) {



    }

    /**&
     * 提掉线
     * @param avimClient
     * @param i
     */
    @Override
    public void onClientOffline(AVIMClient avimClient, int i) {


        EventBus.getDefault().post(new IMClientOffLineEvent(avimClient,i));
/*        ClientUserManager.getInstance().setClient(avimClient);
        ClientUserManager.getInstance().imQuit(new IMLoginCallBack() {
            @Override
            public void onsuccess() {
                ClientUserManager.getInstance().clearUserMsg();
            }

            @Override
            public void failure(String error) {

            }
        });*/

    }






}
