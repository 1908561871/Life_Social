package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.ImagePreview;
import cn.com.elex.social_life.model.bean.ImageFile;
import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.util.ScreenUtil;
import cn.com.elex.social_life.support.util.TimeUtils;
import cn.com.elex.social_life.support.view.BadgeView;
import cn.com.elex.social_life.ui.activity.ImagePreviewActivity;

/**
 * Created by zhangweibo on 2015/12/4.
 */
public class ZoneDynamicAdapter extends RecyclerView.Adapter<ZoneDynamicAdapter.ViewHolder> {


    private List<PublishLogBean> logs;

    private Context context;

    public ZoneDynamicAdapter(Context context, List<PublishLogBean> logs) {
        this.logs = logs;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_zone_dynamic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PublishLogBean data = logs.get(position);
        UserInfo info = (UserInfo) data.getPublishUser();
        holder.userIcon.setImageURI(Uri.parse(info.getHeadIconUrl().getThumbnailUrl(true, 50, 50)));
        holder.nicker.setText(info.getNickName());
        holder.postingTime.setText(TimeUtils.getTime(data.getCreatedAt().getTime()));
        holder.location.setText(data.getLocation().getProvince() + " " + data.getLocation().getCity());
        holder.logTitle.setText(data.getTitle());
        holder.logContent.setText(data.getContent());
        setLogImage(holder.logImages, data.getImageFiles());
        setImageOnclickListen(holder.logImages, data.getImageFiles());
    }



    @Override
    public int getItemCount() {
        return logs.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_zone_dynamic_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_icon)
        SimpleDraweeView userIcon;
        @Bind(R.id.nicker)
        TextView nicker;
        @Bind(R.id.posting_time)
        TextView postingTime;
        @Bind(R.id.location)
        TextView location;
        @Bind(R.id.log_title)
        TextView logTitle;
        @Bind(R.id.log_content)
        TextView logContent;
        @Bind(R.id.log_images)
        GridLayout logImages;
        @Bind(R.id.log_comments)
        TextView logComments;
        @Bind(R.id.log_praise)
        ImageView logPraise;
        @Bind(R.id.log_comment)
        ImageView logComment;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


    public void setLogImage(GridLayout layout, List<AVFile> imageFiles) {

        int size = getItemSize(layout, imageFiles);
        layout.removeAllViews();
        for (int i = 0; i < imageFiles.size(); i++) {
            SimpleDraweeView image = new SimpleDraweeView(context);
            GridLayoutManager.LayoutParams params = new GridLayoutManager.LayoutParams(size, size);
            image.setPadding(2, 2, 2, 2);
            image.setLayoutParams(params);
            image.setImageURI(Uri.parse(imageFiles.get(i).getThumbnailUrl(true, size, size)));
            layout.addView(image);
        }
        layout.setVisibility(View.VISIBLE);
    }


    public int getItemSize(GridLayout layout, List<AVFile> imageFiles) {
        int width = ScreenUtil.getWidth(context) - context.getResources().getDimensionPixelOffset(R.dimen.verticl_margin) * 2;
        return width / 3;
       /* if (imageFiles.size()<3){
            return width/imageFiles.size();
        }else{
            return width/3;
        }*/

    }

    public void setImageOnclickListen(GridLayout layout, final List<AVFile> imageFiles){
        for (int i = 0; i < layout.getChildCount(); i++) {
            final int choose = i;
            layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ImagePreviewActivity.class);
                    intent.putExtra("ImagePreview",getImagePreview(imageFiles, choose));
                    context.startActivity(intent);
                }
            });

        }


    }


    public ImagePreview getImagePreview(List<AVFile> imageFiles,int chooseItem){


        ImageFile file;
        ImagePreview preview=new ImagePreview();
        List<ImageFile> files;
        files=new ArrayList<ImageFile>();
        for (int i = 0; i < imageFiles.size(); i++) {
            file=new ImageFile();
            file.setThumbUrl(imageFiles.get(i).getThumbnailUrl(true,100,100));
            file.setUrl(imageFiles.get(i).getUrl());
            files.add(file);
        }
        preview.setFiles(files);
        preview.setChoosePosion(chooseItem);
        return preview;
    }


}
