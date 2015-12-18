package cn.com.elex.social_life.model;

import java.io.Serializable;
import java.util.List;

import cn.com.elex.social_life.model.bean.ImageFile;

/**
 * Created by zhangweibo on 2015/12/8.
 */
public class ImagePreview implements Serializable{

    private List<ImageFile> files;

    private int choosePosion;


    public List<ImageFile> getFiles() {
        return files;
    }

    public void setFiles(List<ImageFile> files) {
        this.files = files;
    }

    public int getChoosePosion() {
        return choosePosion;
    }

    public void setChoosePosion(int choosePosion) {
        this.choosePosion = choosePosion;
    }
}
