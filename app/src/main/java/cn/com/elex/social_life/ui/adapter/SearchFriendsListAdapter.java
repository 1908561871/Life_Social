package cn.com.elex.social_life.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.support.util.ToastUtils;

/**
 * Created by zhangweibo on 2015/11/4.
 */
public class SearchFriendsListAdapter extends RecyclerView.Adapter<SearchFriendsListAdapter.ViewHolder> {

    private Context context;


    private OnItemClickLitener mOnItemClickLitener;

    private List<UserInfo> userInfos;

    public SearchFriendsListAdapter(Context context, List<UserInfo> userInfos) {
        this.context = context;
        this.userInfos = userInfos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        UserInfo userInfo=userInfos.get(position);
        ToastUtils.show(context,userInfo.getObjectId());
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'adapter_search_friend     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.icon_user)
        SimpleDraweeView iconUser;
        @Bind(R.id.tv_userName)
        TextView tvUserName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'fragment_tab_msg_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */



 public interface OnItemClickLitener {
    void onItemClick(View view, int position);
}

}
