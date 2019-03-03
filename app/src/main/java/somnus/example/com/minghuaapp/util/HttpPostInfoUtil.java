package somnus.example.com.minghuaapp.util;

import android.util.Log;

/**
 * Created by Somnus on 2019/2/13.
 * http请求数据处理类
 */

public class HttpPostInfoUtil {

    private String time = null;

    public HttpPostInfoUtil(String time){
        this.time = time;
    }

    /**
     * 组装请求头数据
     */
    public String assembHeaderSign(String SerialNumber,String type){
        try {
            return (new AesUtil()).encrypt("did="+SerialNumber+"&version="+ConstantsUtil.VERSION +"&app_type="+type+"&time="+time);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 组装登录请求数据
     * @return
     */
    public String assemblyLoginBody(String phone, String password, String code){
        if (code.equals("") && !password.equals("")){
            String info = "phone="+phone+"&password="+password+"&post_time="+time;
            try {
                return (new AesUtil()).encrypt(info);
            } catch (Exception e) {
                return "";
            }
        }else {
            String info = "phone="+phone+"&code="+code+"&post_time="+time;
            try {
                return (new AesUtil()).encrypt(info);
            } catch (Exception e) {
                return "";
            }
        }
    }

    /**
     * 组装获取用户信息数据
     * @param token 登录成功服务器返回token
     * @return
     */
    public String assemblyUserBody(String token){
        String info = "token="+token+"&post_time="+time;
        try {
            return (new AesUtil()).encrypt(info);
        } catch (Exception e) {
            return "";
        }
    }
}
