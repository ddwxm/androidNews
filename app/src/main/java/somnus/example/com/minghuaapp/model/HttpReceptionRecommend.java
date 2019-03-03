package somnus.example.com.minghuaapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Somnus on 2019/2/23.
 * 推荐
 */

public class HttpReceptionRecommend {
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;
    @SerializedName("decryption")
    private Integer decryption;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {
        private String info;
        private String mark;
        private Integer count;
        private Integer page_num;
        private List<List<News>> list;


        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getPage_num() {
            return page_num;
        }

        public void setPage_num(Integer page_num) {
            this.page_num = page_num;
        }

        public List<List<News>> getList() {
            return list;
        }

        public void setList(List<List<News>> list) {
            this.list = list;
        }
    }
}
