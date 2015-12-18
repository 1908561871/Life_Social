package cn.com.elex.social_life.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.ImagePreview;
import cn.com.elex.social_life.ui.adapter.ImagePreviewAdapter;
import cn.com.elex.social_life.ui.base.BaseActivity;

/**
 * Created by zhangweibo on 2015/12/8.
 */
public class ImagePreviewActivity extends BaseActivity {


    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tv_count)
    TextView tvCount;
    private ImagePreviewAdapter adapter;
    private ImagePreview imagePreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        imagePreview = (ImagePreview) getIntent().getSerializableExtra("ImagePreview");
        adapter = new ImagePreviewAdapter(getSupportFragmentManager(), imagePreview);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(imagePreview.getChoosePosion());
        tvCount.setText(imagePreview.getChoosePosion()+1+" / "+imagePreview.getFiles().size());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCount.setText(position+1+" / "+imagePreview.getFiles().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
