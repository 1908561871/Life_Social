package cn.com.elex.social_life.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.model.bean.FragmentMessage;
import cn.com.elex.social_life.support.view.CustomDialog;
import cn.com.elex.social_life.support.view.DividerItemDecoration;
import cn.com.elex.social_life.ui.activity.AddFriendActivity;
import cn.com.elex.social_life.ui.activity.ChatRoomActivity;
import cn.com.elex.social_life.ui.adapter.ChatRoomMsgAdapter;
import cn.com.elex.social_life.ui.adapter.MessageAdapter;
import cn.com.elex.social_life.ui.base.BaseFragment;

/**
 * Created by Administrator on 2015/10/30.
 */
    public class MessageTabFragment extends BaseFragment {

    @Bind(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    private List<FragmentMessage> messages;
    private MessageAdapter adapter;
    private CustomDialog dialog;


    @Override
    protected int getResourceLyoutID() {
        return R.layout.fragment_tab_msg;
    }

    @Override
    protected void onViewFirstLoading() {

    }

    @Override
    protected void onViewReloading() {

    }

    @Override
    protected void setViewData() {
        initRecycleView();


    }

    public void initRecycleView() {

        adapter = new MessageAdapter(getContext(), messages);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        adapter.setmOnItemClickLitener(new MessageAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
               goToPagerByIntent(AddFriendActivity.class);
            }
        });
    }

    public void setData() {
        messages = new ArrayList<>();
        FragmentMessage msg;
        msg = new FragmentMessage("周五", "暂无消息", R.drawable.icon_item_latest_msg_gms_friend_trend, getResources().getString(R.string.friend_dynamic));
        messages.add(msg);
        msg = new FragmentMessage("周五", "暂无消息", R.drawable.icon_item_latest_msg_gms, getResources().getString(R.string.friend_message));
        messages.add(msg);
        msg = new FragmentMessage("周五", "暂无消息", R.drawable.icon_item_latest_msg_friend_request, getResources().getString(R.string.friend_request));
        messages.add(msg);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void preloadDataInit() {
        setData();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}
