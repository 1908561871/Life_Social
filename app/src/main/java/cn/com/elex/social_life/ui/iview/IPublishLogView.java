package cn.com.elex.social_life.ui.iview;

import android.content.Intent;

import java.util.ArrayList;

import cn.com.elex.social_life.model.bean.LocationMsg;

/**
 * Created by zhangweibo on 2015/12/1.
 */
public interface IPublishLogView {


     ArrayList<String> getSelectPath();

    void setSelectPath(ArrayList<String> selectPath);

    int getRequstCode();
    int getResultCode();
    String getLogContent();
    LocationMsg getLocation();
    String getLogTitle();
    Intent getData();
    void setLocation(LocationMsg location);
    void showDialog();
    void hideDialog();

}
