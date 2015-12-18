package cn.com.elex.social_life.support.event;

/**
 * Created by zhangwb on 2015/7/31.
 */
public class NetChangeEvent {

    private boolean isNetAvailable;

    public NetChangeEvent(boolean isNetAvailable) {
        this.isNetAvailable = isNetAvailable;
    }

    public boolean isNetAvailable() {
        return isNetAvailable;
    }
}
