package somnus.example.com.minghuaapp.view.IView;

/**
 * Created by Somnus on 2019/2/15.
 * 登录接口
 */

public interface ILoginActivity {

    /**
     * 验证码请求成功回调
     * @param phone 手机号码
     */
    void identifySuccess(String phone);

    /**
     * 验证码请求失败回调
     * @param e 错误信息
     */
    void identifyFail(String e);

    /**
     * 登录成功回调
     * @param info token
     */
    void loginSuccess(String info);

    /**
     * 登录失败回调
     * @param e 错误信息
     */
    void loginFail(String e);

    /**
     * 获取用户信息成功回调
     * @param info 用户信息加密串
     */
    void getUserSuccess(String info);

    /**
     * 获取用户信息失败回调
     * @param e 错误信息
     */
    void getUserFail(String e);
}
