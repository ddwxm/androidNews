package somnus.example.com.minghuaapp.model;

import java.util.List;

/**
 * Created by Somnus on 2019/2/25.
 */

public class NewsDetails {

    /**
     * status : 1
     * message : OK
     * data : {"info":null,"mark":null,"count":1,"page_num":null,"list":[{"id":1,"title":"asdas","small_title":"dasd","catid":1,"image":"http://pkxybom81.bkt.clouddn.com/2019/01/604f020190108141130280.jpg","content":"<p>sadasd<\/p>","description":"dasdas","is_position":1,"is_head_figure":1,"is_allowcomments":1,"listorder":0,"source_type":0,"create_time":"2019-01-08 14:11:42","update_time":"2019-01-12 17:48:34","status":1,"read_count":213,"upvote_count":0,"comment_count":0,"catname":"综艺"}]}
     * decryption : 0
     */
    private int status;
    private String message;
    private DataBean data;
    private int decryption;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getDecryption() {
        return decryption;
    }

    public void setDecryption(int decryption) {
        this.decryption = decryption;
    }

    public static class DataBean {
        /**
         * info : null
         * mark : null
         * count : 1
         * page_num : null
         * list : [{"id":1,"title":"asdas","small_title":"dasd","catid":1,"image":"http://pkxybom81.bkt.clouddn.com/2019/01/604f020190108141130280.jpg","content":"<p>sadasd<\/p>","description":"dasdas","is_position":1,"is_head_figure":1,"is_allowcomments":1,"listorder":0,"source_type":0,"create_time":"2019-01-08 14:11:42","update_time":"2019-01-12 17:48:34","status":1,"read_count":213,"upvote_count":0,"comment_count":0,"catname":"综艺"}]
         */

        private Object info;
        private Object mark;
        private int count;
        private Object page_num;
        private News list;

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public Object getMark() {
            return mark;
        }

        public void setMark(Object mark) {
            this.mark = mark;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getPage_num() {
            return page_num;
        }

        public void setPage_num(Object page_num) {
            this.page_num = page_num;
        }

        public News getList() {
            return list;
        }

        public void setList(News list) {
            this.list = list;
        }
    }
}
