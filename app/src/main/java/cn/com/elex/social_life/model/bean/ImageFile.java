package cn.com.elex.social_life.model.bean;

import java.io.Serializable;

/**
 * Created by zhangweibo on 2015/12/8.
 */
public class ImageFile implements Serializable{

    private String thumbUrl;

    private String url;

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
