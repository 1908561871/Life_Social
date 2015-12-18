package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;

/**
 * Created by zhangweibo on 2015/12/2.
 */
public class GridlayoutImagesAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<String> imagePaths;

    private int itemHeight;

    public GridlayoutImagesAdapter(Context context, ArrayList<String> imagePaths,int itemHeight) {
        this.context = context;
        this.imagePaths = imagePaths;
        this.itemHeight=itemHeight;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if (convertView == null) {
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                    itemHeight,
                    itemHeight);
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_image_item,null);
            holder=new ViewHolder(convertView);
            holder.param=param;
            convertView.setTag(holder);

        }

        holder= (ViewHolder) convertView.getTag();
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePaths.remove(position);
                notifyDataSetChanged();
            }
        });
        convertView.setLayoutParams(holder.param);
       // adjustItemSize(convertView);
        Picasso.with(context).load(new File(imagePaths.get(position))).resize(itemHeight,itemHeight).centerCrop().into(holder.ivIcons);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_image_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */


    public void adjustItemSize(View view){
        view.measure(0,0);
        ViewGroup.LayoutParams params= view.getLayoutParams();
        params.height=view.getMeasuredWidth();
        view.setLayoutParams(params);
    }



    class ViewHolder {
        @Bind(R.id.iv_icons)
        ImageView ivIcons;
        @Bind(R.id.iv_delete)
        ImageView ivDelete;
        AbsListView.LayoutParams param;
        @Bind(R.id.parent_layout)
         RelativeLayout parentLayout;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}
