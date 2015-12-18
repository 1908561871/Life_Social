package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.Introduce;
import cn.com.elex.social_life.support.view.imageview.CircleImageView;
import cn.com.elex.social_life.support.view.indicator.InfinitePagerAdapter;

/**
 * Created by zhangweibo on 2015/11/18.
 */
public class GuideIntroduceAdpter extends InfinitePagerAdapter {
    public static List<Introduce> introduces;
    private Context context;

    static {
        introduces = new ArrayList<>();
        introduces.add(new Introduce(R.drawable.introduce_bg2, "听说", "随时随刻聊天"));
        introduces.add(new Introduce(R.drawable.introduce_bg3, "发现", "只听声音找到本命"));
        introduces.add(new Introduce(R.drawable.introduce_bg4, "知音", "聊得来才是真朋友"));
    }

    public GuideIntroduceAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return introduces == null ? 0 : introduces.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder holder;
        Introduce introdu=introduces.get(position);
        if (convertView != null) {
            holder= (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_guide_introduce, container, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.icon.setImageResource(introdu.icon);
        holder.introduce.setText(introdu.introduce);
        holder.tag.setText(introdu.tag);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_guide_introduce.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.icon)
        CircleImageView icon;
        @Bind(R.id.tag)
        TextView tag;
        @Bind(R.id.introduce)
        TextView introduce;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
