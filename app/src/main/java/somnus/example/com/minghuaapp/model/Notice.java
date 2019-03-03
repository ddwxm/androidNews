package somnus.example.com.minghuaapp.model;

import java.util.List;

/**
 * Created by Somnus on 2019/2/27.
 * 通知
 */

public class Notice {

    /**
     * status : 1
     * message : OK
     * data : [{"user_id":6,"image":"http://image.minghua.ink/2019/02/8d3ef20190208114342133.jpg","nick":"小明","news_id":2,"small_title":"csc","create_time":1550050979},{"user_id":1,"image":"http://image.minghua.ink/2019/02/8d3ef20190208114342133.jpg","nick":"哈哈哈","news_id":5,"small_title":"带我去","create_time":1549807600}]
     * decryption : 0
     */

    private int status;
    private String message;
    private int decryption;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDecryption() {
        return decryption;
    }

    public void setDecryption(int decryption) {
        this.decryption = decryption;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 6
         * image : http://image.minghua.ink/2019/02/8d3ef20190208114342133.jpg
         * nick : 小明
         * news_id : 2
         * small_title : csc
         * create_time : 1550050979
         */

        private int user_id;
        private String image;
        private String nick;
        private int news_id;
        private String small_title;
        private int create_time;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getSmall_title() {
            return small_title;
        }

        public void setSmall_title(String small_title) {
            this.small_title = small_title;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
