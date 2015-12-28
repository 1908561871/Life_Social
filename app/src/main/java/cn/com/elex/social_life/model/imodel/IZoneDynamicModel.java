package cn.com.elex.social_life.model.imodel;

import com.avos.avoscloud.FindCallback;

import cn.com.elex.social_life.model.bean.UserInfo;

/**
 * Created by zhangweibo on 2015/12/28.
 */
public interface IZoneDynamicModel {


    void getZoneDynamic(UserInfo info, FindCallback callback,int pageNum,int pageSize);

}
