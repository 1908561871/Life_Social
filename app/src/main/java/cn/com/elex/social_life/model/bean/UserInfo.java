package cn.com.elex.social_life.model.bean;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;

import java.util.List;

/**
 * Created by zhangweibo on 2015/11/2.
 */

public class UserInfo extends AVUser{

    /**
     * 账号类型（QQ,wexin,sina,phone）
     */
    private String accountType;

    /**
     * 好友
     */
    private AVRelation<UserInfo> friends;

    public void setNickName(String name) {
        this.put("nickName", name);
    }

    public String getNickName() {
        return this.getString("nickName");
    }



    public String getAccountType() {
        return this.getString("accountType");
    }

    public void setAccountType(String accountType) {
        this.put("accountType", accountType);
    }

    //头像URL
    public void setHeadIconUrl(AVFile icon) {
        this.put("headIconUrl", icon);
    }

    public AVFile getHeadIconUrl() {
        return this.getAVFile("headIconUrl");
    }


    //性别
    public void setSexType(int  sexType) {
        this.put("sexType", sexType);
    }

    public int getSexType() {
        return this.getInt("sexType");
    }

    public void setGeoPoint(AVGeoPoint point) {
        this.put("GeoPoint", point);
    }


    public AVGeoPoint getGeoPoint() {
        return this.getAVGeoPoint("GeoPoint");
    }



}
