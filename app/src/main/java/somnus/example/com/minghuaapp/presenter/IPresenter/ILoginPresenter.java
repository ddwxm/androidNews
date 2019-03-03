package somnus.example.com.minghuaapp.presenter.IPresenter;

/**
 * Created by Somnus on 2019/2/15.
 * 登录Presenter接口
 */

public interface ILoginPresenter {

    void identify(String phone);

    void login(String info);

    void getUser(String info);
}
