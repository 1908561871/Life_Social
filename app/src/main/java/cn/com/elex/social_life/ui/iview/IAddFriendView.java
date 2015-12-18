package cn.com.elex.social_life.ui.iview;

import java.util.List;

/**
 * Created by zhangweibo on 2015/11/6.
 */
public interface IAddFriendView extends  ILoadingView{


    String getSearchContent();


    void updateSearchView(List datas);


    void showLoadingView();



}
