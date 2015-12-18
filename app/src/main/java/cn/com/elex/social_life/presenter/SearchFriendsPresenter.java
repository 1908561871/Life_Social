package cn.com.elex.social_life.presenter;

import android.text.TextUtils;

import java.util.List;

import cn.com.elex.social_life.model.AddFriendsModel;
import cn.com.elex.social_life.model.imodel.IAddFriendsModel;
import cn.com.elex.social_life.support.callback.DataQueryCallBack;
import cn.com.elex.social_life.ui.iview.IAddFriendView;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public class SearchFriendsPresenter {


    private IAddFriendView addFriendView;

    private IAddFriendsModel addFriendsModel;

    public SearchFriendsPresenter(IAddFriendView addFriendView) {
        this.addFriendView = addFriendView;
        addFriendsModel=new AddFriendsModel();
    }


    public void search(String content){
        if (!TextUtils.isEmpty(content))
        {
            addFriendView.showLoadingView();
            addFriendsModel.search(content, new DataQueryCallBack() {
                @Override
                public void success(List list) {
                    addFriendView.updateSearchView(list);
                    addFriendView.HideLoadingView();
                }

                @Override
                public void failure(String msg) {
                    addFriendView.HideLoadingView();
                }
            });
        }

    }



}
