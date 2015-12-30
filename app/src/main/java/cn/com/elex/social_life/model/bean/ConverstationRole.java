package cn.com.elex.social_life.model.bean;

import android.os.Parcel;

import com.avos.avoscloud.AVObject;

/**
 * Created by zhangweibo on 2015/12/30.
 */
public class ConverstationRole extends AVObject{

    //此处为我们的默认实现，当然你也可以自行实现
    public static final Creator CREATOR = AVObjectCreator.instance;


    public ConverstationRole(){
    }

    public ConverstationRole(Parcel in){
        super(in);
    }





}
