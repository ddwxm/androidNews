package somnus.example.com.minghuaapp.model;

/**
 * Created by Somnus on 2019/2/26.
 * 极光推送附加字段
 */

public class JGBean {

    /**
     * ObjectUserId : 6
     * PraiseUserImage : http://image.minghua.ink/2019/02/8d3ef20190208114342133.jpg
     * PraiseUserNick : 小明
     * newTitle : 大洼
     * newsId : 7
     * num : 0
     * time : 2019-03-02 19:39:08
     */

    private String ObjectUserId;
    private String PraiseUserImage;
    private String PraiseUserNick;
    private String newTitle;
    private String newsId;
    private int num;
    private String time;

    public String getObjectUserId() {
        return ObjectUserId;
    }

    public void setObjectUserId(String ObjectUserId) {
        this.ObjectUserId = ObjectUserId;
    }

    public String getPraiseUserImage() {
        return PraiseUserImage;
    }

    public void setPraiseUserImage(String PraiseUserImage) {
        this.PraiseUserImage = PraiseUserImage;
    }

    public String getPraiseUserNick() {
        return PraiseUserNick;
    }

    public void setPraiseUserNick(String PraiseUserNick) {
        this.PraiseUserNick = PraiseUserNick;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
