package cn.com.elex.social_life.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.elex.social_life.R;
import cn.com.elex.social_life.cloud.data.DataStorage;
import cn.com.elex.social_life.model.bean.UserInfo;
import cn.com.elex.social_life.presenter.SearchFriendsPresenter;
import cn.com.elex.social_life.support.callback.DataSaveCallBack;
import cn.com.elex.social_life.support.util.ToastUtils;
import cn.com.elex.social_life.support.view.load.LoadViewHelper;
import cn.com.elex.social_life.ui.adapter.SearchFriendsListAdapter;
import cn.com.elex.social_life.ui.base.BaseActivity;
import cn.com.elex.social_life.ui.iview.IAddFriendView;

/**
 * Created by zhangweibo on 2015/11/5.
 */
public class AddFriendActivity extends BaseActivity  implements IAddFriendView{


    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.et_search_content)
    EditText et_search_content;
    private LoadViewHelper viewHelper;
    private SearchFriendsListAdapter adapter;
    private List<UserInfo> infos;
    private SearchFriendsPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        ButterKnife.bind(this);
        initData();

    }

    public void initData(){
        viewHelper=new LoadViewHelper(recycleView);
        presenter=new SearchFriendsPresenter(this);
        infos=new ArrayList<>();
        adapter=new SearchFriendsListAdapter(this,infos);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter);
        adapter.setmOnItemClickLitener(new SearchFriendsListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent =new Intent(AddFriendActivity.this,ChatRoomActivity.class);
                intent.putExtra("member",new String[]{infos.get(position).getUsername()});
                startActivity(intent);
            }
        });
    }

    @Override
    public String getSearchContent() {
        return et_search_content.getText().toString().trim();
    }

    @Override
    public void updateSearchView(List datas) {
        infos.clear();
        infos.addAll(datas);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showLoadingView() {
        viewHelper.showLoading();
    }

    @Override
    public void HideLoadingView() {
        viewHelper.restore();
    }


    @OnClick({R.id.tv_search})
    public void search(){
        presenter.search(getSearchContent());
    }

}
