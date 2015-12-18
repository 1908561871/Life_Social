package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.FragmentMessage;
import cn.com.elex.social_life.support.view.imageview.CircleImageView;

/**
 * Created by zhangweibo on 2015/11/4.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;

    private List<FragmentMessage> messages;

    private OnItemClickLitener mOnItemClickLitener;


    public MessageAdapter(Context context, List<FragmentMessage> messages) {
        this.context = context;
        this.messages=messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_tab_msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        FragmentMessage msg=messages.get(position);
        if (mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
        holder.imgIcon.setImageResource(msg.getIcon());
        holder.tvDate.setText(msg.getUpdate());
        holder.tvDescrip.setText(msg.getContent());
        holder.tvTitle.setText(msg.getTitle());
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'fragment_tab_msg_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_icon)
        CircleImageView imgIcon;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_descrip)
        TextView tvDescrip;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }


    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
