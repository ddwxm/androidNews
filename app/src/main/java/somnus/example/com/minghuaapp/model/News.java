package somnus.example.com.minghuaapp.model;

import java.io.Serializable;

/**
 * Created by Somnus on 2019/2/14.
 * 新闻实体类
 */

public class News implements Serializable {

    private int id;
    private String title;
    private String small_title;
    private int catid;
    private String image;
    private String content;
    private String description;
    private int is_position;
    private int is_head_figure;
    private int is_allowcomments;
    private int listorder;
    private int source_type;
    private String create_time;
    private String update_time;
    private int status;
    private int read_count;
    private int upvote_count;
    private int comment_count;
    private String catname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmall_title() {
        return small_title;
    }

    public void setSmall_title(String small_title) {
        this.small_title = small_title;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_position() {
        return is_position;
    }

    public void setIs_position(int is_position) {
        this.is_position = is_position;
    }

    public int getIs_head_figure() {
        return is_head_figure;
    }

    public void setIs_head_figure(int is_head_figure) {
        this.is_head_figure = is_head_figure;
    }

    public int getIs_allowcomments() {
        return is_allowcomments;
    }

    public void setIs_allowcomments(int is_allowcomments) {
        this.is_allowcomments = is_allowcomments;
    }

    public int getListorder() {
        return listorder;
    }

    public void setListorder(int listorder) {
        this.listorder = listorder;
    }

    public int getSource_type() {
        return source_type;
    }

    public void setSource_type(int source_type) {
        this.source_type = source_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getUpvote_count() {
        return upvote_count;
    }

    public void setUpvote_count(int upvote_count) {
        this.upvote_count = upvote_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
