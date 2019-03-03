package somnus.example.com.minghuaapp.util.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import somnus.example.com.minghuaapp.model.HttpReceptionBean;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.model.HttpReceptionRecommend;
import somnus.example.com.minghuaapp.model.NewsDetails;
import somnus.example.com.minghuaapp.model.Notice;

/**
 * Created by Somnus on 2019/2/13.
 * 网络请求接口
 */

public interface HttpRequestService {

    /**
     * 新闻栏目
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @return
     */
    @GET("api/{ver}.cat")
    Call<HttpReceptionCat> getCat(@Path("ver") String ver,
                                  @Header("did") String did,
                                  @Header("version") String version,
                                  @Header("app_type") String type,
                                  @Header("sign") String sign);

    /**
     * 推荐请求
     * @param ver 请求版本
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @return
     */
    @GET("api/{ver}.index")
    Call<HttpReceptionRecommend> RecommendRequest(@Path("ver") String ver,
                                                  @Header("did") String did,
                                                  @Header("version") String version,
                                                  @Header("app_type") String type,
                                                  @Header("sign") String sign,
                                                  @Header("access_user_token") String access_user_token);

    /**
     * GET请求
     * @param url 请求地址
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @return
     */
    @GET("{url}")
    Call<HttpReceptionBean> GetRequest(@Path("url") String url,
                                       @Header("did") String did,
                                       @Header("version") String version,
                                       @Header("app_type") String type,
                                       @Header("sign") String sign,
                                       @Header("access_user_token") String access_user_token);

    /**
     * 验证码请求
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @param phone 手机号
     * @return
     */
    @FormUrlEncoded
    @POST("api/{ver}/identify")
    Call<HttpReceptionBean> IdentifyRequest(@Path("ver") String ver,
                                            @Header("did") String did,
                                            @Header("version") String version,
                                            @Header("app_type") String type,
                                            @Header("sign") String sign,
                                            @Header("access_user_token") String access_user_token,
                                            @Field("phone") String phone);


    /**
     * 手机号登录
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token 传空
     * @param info 加密串
     * @return
     */
    @FormUrlEncoded
    @POST("api/{ver}/login")
    Call<HttpReceptionBean> LoginRequest(@Path("ver") String ver,
                                         @Header("did") String did,
                                         @Header("version") String version,
                                         @Header("app_type") String type,
                                         @Header("sign") String sign,
                                         @Header("access_user_token") String access_user_token,
                                         @Field("info") String info);

    /**
     * 获取用户信息
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @return
     */
    @GET("api/{ver}/user")
    Call<HttpReceptionBean> GetUserRequest(@Path("ver") String ver,
                                       @Header("did") String did,
                                       @Header("version") String version,
                                       @Header("app_type") String type,
                                       @Header("sign") String sign,
                                       @Header("access_user_token") String access_user_token);


    /**
     * 修改用户资料
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @param nick 昵称
     * @param sex 性别
     * @param image 头像
     * @param signature 签名
     * @param description 个人描述
     * @param address 地址
     * @return
     */
    @FormUrlEncoded
    @PUT("api/{ver}/user/{id}")
    Call<HttpReceptionBean> UpdateRequest(@Path("ver") String ver,
                                          @Path("id") String id,
                                          @Header("did") String did,
                                          @Header("version") String version,
                                          @Header("app_type") String type,
                                          @Header("sign") String sign,
                                          @Header("access_user_token") String access_user_token,
                                          @Field("nick") String nick,
                                          @Field("sex") String sex,
                                          @Field("image") String image,
                                          @Field("signature") String signature,
                                          @Field("description") String description,
                                          @Field("address") String address);

    /**
     * 头像上传
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @param image 本地图片地址
     * @return
     */
    @Multipart
    @POST("api/{ver}/image")
    Call<HttpReceptionBean> ImageRequest(@Path("ver") String ver,
                                         @Header("did") String did,
                                         @Header("version") String version,
                                         @Header("app_type") String type,
                                         @Header("sign") String sign,
                                         @Header("access_user_token") String access_user_token,
                                         @Part("image") String image);

    /**
     * 获取通知信息
     * @param ver 版本号
     * @param id 用户ID
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @return
     */
    @GET("api/{ver}/notification/{id}")
    Call<Notice> GetNotificationRequest(@Path("ver") String ver,
                                        @Path("id") int id,
                                        @Header("did") String did,
                                        @Header("version") String version,
                                        @Header("app_type") String type,
                                        @Header("sign") String sign,
                                        @Header("access_user_token") String access_user_token);

    /**
     * 获取新闻详情
     * @param ver 版本号
     * @param id 新闻ID
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token 可空
     * @return
     */
    @GET("api/{ver}/news/{id}")
    Call<NewsDetails> GetNewsDetailsRequest(@Path("ver") String ver,
                                            @Path("id") int id,
                                            @Header("did") String did,
                                            @Header("version") String version,
                                            @Header("app_type") String type,
                                            @Header("sign") String sign,
                                            @Header("access_user_token") String access_user_token);


    /**
     * 获取新闻是否点赞
     * @param ver 版本号
     * @param id 新闻ID
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @return
     */
    @GET("api/{ver}/upvote/{id}")
    Call<HttpReceptionBean> GetNewsIsPraiseRequest(@Path("ver") String ver,
                                            @Path("id") int id,
                                            @Header("did") String did,
                                            @Header("version") String version,
                                            @Header("app_type") String type,
                                            @Header("sign") String sign,
                                            @Header("access_user_token") String access_user_token);

    /**
     * 新闻点赞
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @param id 新闻ID
     * @return
     */
    @FormUrlEncoded
    @POST("api/{ver}/upvote")
    Call<HttpReceptionBean> UpvoteRequest(@Path("ver") String ver,
                                          @Header("did") String did,
                                          @Header("version") String version,
                                          @Header("app_type") String type,
                                          @Header("sign") String sign,
                                          @Header("access_user_token") String access_user_token,
                                          @Field("id") int id);

    /**
     * 新闻取消点赞
     * @param ver 版本号
     * @param did 设备号
     * @param version app版本
     * @param type 设备类型
     * @param sign 加密串
     * @param access_user_token 用户token
     * @param id 新闻ID
     * @return
     */
    @HTTP(method = "DELETE", path ="api/{ver}/upvote", hasBody = true)
    @FormUrlEncoded
    Call<HttpReceptionBean> CanleUpvoteRequest(@Path("ver") String ver,
                                               @Header("did") String did,
                                               @Header("version") String version,
                                               @Header("app_type") String type,
                                               @Header("sign") String sign,
                                               @Header("access_user_token") String access_user_token,
                                               @Field("id") int id);


}
