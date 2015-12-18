package cn.com.elex.social_life.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.ImageFile;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.ui.base.BaseFragment;

/**
 * Created by zhangweibo on 2015/12/8.
 */
public class ImagePreviewFragment extends BaseFragment {

    @Bind(R.id.image_preview)
    SimpleDraweeView imagePreview;
    ImageFile file;
    private GenericDraweeHierarchy hierarchy;
    private DraweeController controller;

    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_image_preview;
    }

    @Override
    protected void onViewFirstLoading() {

    }

    @Override
    protected void onViewReloading() {

    }

    @Override
    protected void setViewData() {

        imagePreview.setController(getControl());
        imagePreview.setHierarchy(getHierarchy());
    }




    @Override
    protected void preloadDataInit() {
        Bundle bundle= getArguments();
        file= (ImageFile) bundle.getSerializable("file");
    }


    public static ImagePreviewFragment newInstance(ImageFile file) {
        ImagePreviewFragment fragment = new ImagePreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("file",file);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public GenericDraweeHierarchy getHierarchy(){

        if(hierarchy==null){
            GenericDraweeHierarchyBuilder builder =
                    new GenericDraweeHierarchyBuilder(getResources());
            hierarchy = builder
                    .setFadeDuration(300)
                    .setProgressBarImage(new ProgressBarDrawable()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                    .build();
        }
        return  hierarchy;

    }


    public DraweeController getControl(){
        if (controller==null){
            controller = Fresco.newDraweeControllerBuilder()
                    .setLowResImageRequest(ImageRequest.fromUri(file.getThumbUrl()))
                    .setImageRequest(ImageRequest.fromUri(file.getUrl()))
                    .setOldController(imagePreview.getController())
                    .setControllerListener(new BaseControllerListener<ImageInfo>(){
                        @Override
                        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                            super.onFinalImageSet(id, imageInfo, animatable);
                        }
                    })
                    .build();
        }

        return  controller;

    }

}
