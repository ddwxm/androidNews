package somnus.example.com.minghuaapp.util;

/**
 * Created by Somnus on 2019/2/13.
 * 常量字符管理类
 */

public class ConstantsUtil {

    /**
     *  主机
     */
    public static String HOST = "http://www.minghua.ink/";
    /**
     * 版本
     */
    public static String VERSION = "v1";
    /**
     * AES加密言
     */
    public static String AES_KEY ="1454somnus248631";
    /**
     * 验证码URL
     * POST
     * 手机号码
     */
    public static String URL_IDENTIFY = HOST + "api/" + VERSION + "/identify";
    /**
     * 登录URL
     * POST
     * 手机号码+密码 / 手机号码+验证码
     */
    public static String URL_LOGIN = HOST + "api/" + VERSION + "/login";
    /**
     * 注销URL
     * GET
     */
    public static String URL_LOOUT = HOST + "api/" + VERSION + "/logout";
    /**
     * 个人中心URL
     * GET
     */
    public static String URL_USER(String id){
        return HOST + "api/" + VERSION + "/user/" + id;
    }
    /**
     *  用户数据更新URL
     *  PUT
     */
    public static String URL_UPDATE(String id){
        return HOST + "api/" + VERSION + "/user/" + id;
    }
    /**
     * 新闻模糊查询URL
     * GET
     * 通过标题
     */
    public static String URL_SEARCH(String title){
        return URL_NEWS + "?" + "title=" + title;
    }
    /**
     * 新闻URL
     * GET
     */
    public static String URL_NEWS = HOST + "api/" + VERSION + ".news";
    /**
     *  新闻排名URL
     *  GET
     */
    public static String URL_RANK = HOST + "api/" + VERSION + ".rank";
    /**
     *  栏目URL
     *  GET
     */
    public static String URL_CAT = HOST + "api/" + VERSION + ".cat";
    /**
     *  点赞URL
     *  POST
     *  id --新闻ID
     */
    public static String URL_PRAISE = HOST + "api/" + VERSION + "/upvote";
    /**
     *  评论URL
     *  POST
     *  news_id 新闻ID 必需
     *  content 评论内容 必需
     *  to_user_id 回复用户 非必需
     */
    public static String URL_COMMENT = HOST + "api/" + VERSION + "/comment";
    /**
     *  缓存文件名
     */
    public static String CACHE_NAME = "User";
    /**
     * 缓存文件名
     */
    public static String CACHE_MESSAGE = "Message";
    /**
     * 缓存文件键名 num
     */
    public static String CACHE_KEY_NUM = "num";
    /**
     * 缓存文件键名 list
     */
    public static String CACHE_KEY_LIST = "list";
    /**
     * 缓存文件键名 user
     */
    public static String CACHE_KEY_USER = "user";
    /**
     * 缓存文件键名 token
     */
    public static String CACHE_KEY_TOKEN = "token";
    /**
     * 极光sequence 标志
     */
    public static int JS_SEQUENCE = 1;

}

