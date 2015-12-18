package cn.com.elex.social_life.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.avos.avoscloud.AVFile;

import java.util.List;

import cn.com.elex.social_life.model.ImagePreview;
import cn.com.elex.social_life.ui.fragment.ImagePreviewFragment;

/**
 * Created by zhangweibo on 2015/12/8.
 */
public class ImagePreviewAdapter extends FragmentPagerAdapter{


    private ImagePreview preview;

    public ImagePreviewAdapter(FragmentManager fm , ImagePreview preview) {
        super(fm);
        this.preview=preview;
    }

    @Override
    public Fragment getItem(int position) {

        return  ImagePreviewFragment.newInstance(preview.getFiles().get(position));
    }

    @Override
    public int getCount() {
        return preview.getFiles().size();
    }
}
