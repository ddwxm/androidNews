package somnus.example.com.minghuaapp.view.IView;

import java.util.List;

import somnus.example.com.minghuaapp.model.HttpReceptionCat;

/**
 * Created by Somnus on 2019/2/23.
 * 欢迎接口
 */

public interface IWelcomeActivity {

    /**
     * 获取栏目成功回调
     * @param catList 栏目list
     */
    void getCatSuccess(List<HttpReceptionCat.Cat> catList);

    /**
     * 获取栏目失败回调
     * @param e 错误信息
     */
    void getCatFail(String e);
}
