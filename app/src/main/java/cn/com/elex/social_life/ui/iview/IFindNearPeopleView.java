package cn.com.elex.social_life.ui.iview;

import java.util.List;

import cn.com.elex.social_life.model.bean.UserInfo;

/**
 * Created by zhangweibo on 2015/12/9.
 */
public interface IFindNearPeopleView {


    public int getPagerNum();

    public void setPagerNum(int pager);

    public void updateData(List<UserInfo> infos);


    public void stopRefresh();

    public void closeLoadMore();

    public void openLoadMore();

    public void clearData();

}
