package somnus.example.com.minghuaapp.model;

import java.io.Serializable;

/**
 * Created by Somnus on 2019/2/13.
 * 用户类
 */

public class User implements Serializable {

    private int id;
    private String username;
    private String phone;
    private Integer time_out;
    private int dynamic_count;
    private int wpraise_count;
    private int focus_count;
    private int collection_count;
    private String image;
    private String nick;
    private int sex;
    private String signaturel;
    private String description;
    private String address;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignaturel() {
        return signaturel;
    }

    public void setSignaturel(String signaturel) {
        this.signaturel = signaturel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTime_out() {
        return time_out;
    }

    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }

    public int getDynamic_count() {
        return dynamic_count;
    }

    public void setDynamic_count(int dynamic_count) {
        this.dynamic_count = dynamic_count;
    }

    public int getWpraise_count() {
        return wpraise_count;
    }

    public void setWpraise_count(int wpraise_count) {
        this.wpraise_count = wpraise_count;
    }

    public int getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(int focus_count) {
        this.focus_count = focus_count;
    }

    public int getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(int collection_count) {
        this.collection_count = collection_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
