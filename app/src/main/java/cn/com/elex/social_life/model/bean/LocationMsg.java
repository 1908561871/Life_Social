package cn.com.elex.social_life.model.bean;

import java.io.Serializable;

/**
 * Created by zhangweibo on 2015/12/2.
 */
public class LocationMsg implements Serializable{

    private double lon;
    private double lat;
    private String addr;
    private String province;
    private String city;


    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
