package cn.com.elex.social_life.support.callback;


import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMMessagesQueryCallback;

import java.util.List;

/**
 * Created by zhangweibo on 2016/1/4.
 */
public abstract class MessageQueryCallBack extends AVIMMessagesQueryCallback {


    @Override
    public void done(List list, AVIMException e) {

        if (e==null) {
            success(list);
        }else {
            failure(e.getMessage());
        }

    }

    public abstract  void success(List<AVIMMessage> list);
    public abstract  void failure(String error);

}
