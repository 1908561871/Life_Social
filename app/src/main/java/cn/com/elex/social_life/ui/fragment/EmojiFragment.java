package cn.com.elex.social_life.ui.fragment;

import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.ui.adapter.EmoticonsGridAdapter;
import cn.com.elex.social_life.ui.adapter.EmoticonsPagerAdapter;
import cn.com.elex.social_life.ui.base.BaseFragment;

/**
 * Created by zhangweibo on 2016/1/5.
 */
public class EmojiFragment extends BaseFragment implements EmoticonsGridAdapter.KeyClickListener {

    private static final int NO_OF_EMOTICONS = 54;

    private KeyClickListen keyClickListen;
    @Bind(R.id.emoticons_pager)
    ViewPager pager;
    private ArrayList<String> paths;

    @Override
    protected int getResourceLyoutID() {
        return R.layout.emoticons_popup;
    }

    @Override
    protected void onViewFirstLoading() {

    }

    @Override
    protected void onViewReloading() {

    }

    @Override
    protected void setViewData() {
        pager.setOffscreenPageLimit(3);
        EmoticonsPagerAdapter adapter = new EmoticonsPagerAdapter(getActivity(), paths, this);
        pager.setAdapter(adapter);
    }

    @Override
    protected void preloadDataInit() {
        paths = new ArrayList<String>();
        for (short i = 1; i <= NO_OF_EMOTICONS; i++) {
            paths.add(i + ".png");
        }
    }


    @Override
    public void keyClickedIndex(String index) {

        if (keyClickListen!=null){
            keyClickListen.onKeyClickListen(index);
        }
    }


    public interface  KeyClickListen{

        void onKeyClickListen(String index);

    }

    public void setKeyClickListen(KeyClickListen keyClickListen) {
        this.keyClickListen = keyClickListen;
    }
}
