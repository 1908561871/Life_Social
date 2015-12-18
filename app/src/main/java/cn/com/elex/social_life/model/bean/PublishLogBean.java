package cn.com.elex.social_life.model.bean;

import android.os.Parcel;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.util.ArrayList;
import java.util.List;

import cn.com.elex.social_life.cloud.ClientUserManager;

/**
 * Created by zhangweibo on 2015/12/3.
 */
@AVClassName("PublishLogBean")
public class PublishLogBean extends AVObject{

    //此处为我们的默认实现，当然你也可以自行实现
    public static final Creator CREATOR = AVObjectCreator.instance;
    private String title;

    private String content;

    private LocationMsg  location;

    private String userName;

    private ArrayList<AVFile> imageFiles;

    private AVObject publishUser;
    public PublishLogBean(){
    }

    public PublishLogBean(Parcel in){
        super(in);
    }

    public String getUserName() {
        return  getString("userName");
    }

    public void setUserName(String userName) {
        put("userName", userName);

    }

    public String getTitle() {
        return  getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getContent() {
        return  getString("content");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public LocationMsg getLocation() {
        return   JSONObject.parseObject(  getString("location"),LocationMsg.class);
    }


    public void setLocation(LocationMsg location) {
        put("location", JSONObject.toJSONString(location));
    }




    public void setPublishUser() {

        put("UserInfo",  ClientUserManager.getInstance().obtainCurrentUser());
    }

    public AVUser getPublishUser() {
        return getAVUser("UserInfo",UserInfo.class);
    }


    public List<AVFile> getImageFiles() {
        return  getList("imageFiles");
    }

    public void setImageFiles(ArrayList<AVFile> imageFiles) {

       addAll("imageFiles",imageFiles);
    }
}
