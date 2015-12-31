package cn.com.elex.social_life.support.event;

import com.avos.avoscloud.im.v2.AVIMClient;

/**
 * Created by zhangweibo on 2015/12/31.
 */
public class IMClientOffLineEvent {


    private AVIMClient avimClient;

    private int code;

    public IMClientOffLineEvent( AVIMClient avimClient, int code) {
        this.avimClient = avimClient;
        this.code = code;
    }


    public AVIMClient getAvimClient() {
        return avimClient;
    }

    public int getCode() {
        return code;
    }
}
