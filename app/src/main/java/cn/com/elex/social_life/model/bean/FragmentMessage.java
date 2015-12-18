package cn.com.elex.social_life.model.bean;

/**
 * Created by zhangweibo on 2015/11/4.
 */
public class FragmentMessage {

    /**
     * 更新的时间
     */
    private String update;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 图片
     */
    private int  icon;

    /**
     * 标题
     */
    private String title;

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FragmentMessage(String update, String content, int icon, String title) {
        this.update = update;
        this.content = content;
        this.icon = icon;
        this.title = title;
    }
}
