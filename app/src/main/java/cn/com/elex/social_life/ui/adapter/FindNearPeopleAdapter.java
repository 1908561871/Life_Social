package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avos.avoscloud.AVFile;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.util.ScreenUtil;
import cn.com.elex.social_life.support.util.ToastUtils;

/**
 * Created by zhangweibo on 2015/12/9.
 */
public class FindNearPeopleAdapter extends RecyclerView.Adapter<FindNearPeopleAdapter.ViewHolder> {
    private List<UserInfo> infos;
    private Context context;

    private OnItemClickListen clickListen;
    public FindNearPeopleAdapter(List<UserInfo> infos, Context context) {
        this.infos = infos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_near_people, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserInfo info=infos.get(position);
        AVFile file=info.getHeadIconUrl();
        holder.userIcon.setImageURI(Uri.parse(file.getUrl()));
        float[] size=getRadioSize((int) file.getMetaData().get("width"),(int) file.getMetaData().get("height"));
        ViewGroup.LayoutParams params= holder.userIcon.getLayoutParams();
        params.height= (int) size[1];
        params.width= (int) size[0];
        holder.userIcon.setLayoutParams(params);
        holder.userIcon.setImageURI(Uri.parse(file.getThumbnailUrl(true,(int)size[0],(int)size[1])));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListen!=null)
                {
                    clickListen.onClick(info);
                }

            }
        });
        //  holder.userIcon.setImageURI(Uri.parse(file.getUrl()));
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_near_people.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_icon)
        SimpleDraweeView userIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public float[] getRadioSize(float width,float height){

        float[] size=new float[2];
        float itemWidth=ScreenUtil.getWidth(context)/3;
        size[0]=itemWidth;
        size[1]=(height/width)*itemWidth;
        return  size;

    }



   public interface  OnItemClickListen{


        void onClick(UserInfo info);
    }

    public void setClickListen(OnItemClickListen clickListen) {
        this.clickListen = clickListen;
    }
}
