package cn.com.elex.social_life.ui.iview;

import java.util.List;

import cn.com.elex.social_life.model.bean.PublishLogBean;
import cn.com.elex.social_life.model.bean.UserInfo;

/**
 * Created by zhangweibo on 2015/12/28.
 */
public interface IZoneDynamicView {

    public UserInfo getUserInfo();

    public void updateLogs();

    public List<PublishLogBean> getPublishLog();

    public void closeLoadView();


    public int getPageNum();

    public int getPageSize();

    public void setPageSize(int pageSize);

    public void setLoadMoreStatue(boolean isLoadMore);

}
