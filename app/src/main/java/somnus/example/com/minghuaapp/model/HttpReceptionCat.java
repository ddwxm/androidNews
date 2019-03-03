package somnus.example.com.minghuaapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Somnus on 2019/2/14.
 * 栏目类
 */

public class HttpReceptionCat {
    private int status;
    private String message;
    private List<Cat> data;
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

    public int getDecryption() {
        return decryption;
    }

    public void setDecryption(int decryption) {
        this.decryption = decryption;
    }

    public List<Cat> getData() {
        return data;
    }

    public void setData(List<Cat> data) {
        this.data = data;
    }

    public class Cat implements Serializable{
        private int catid;
        private String catname;

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }
    }

}
