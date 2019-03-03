package somnus.example.com.minghuaapp.view.IView;

import java.util.List;

import somnus.example.com.minghuaapp.model.News;
import somnus.example.com.minghuaapp.model.RecommendList;

/**
 * Created by Somnus on 2019/2/23.
 * 推荐
 */

public interface IRecommendFragment {


    /**
     * 成功回调
     * @param recommendListList 新闻LIST
     */
    void getRecommendSuccess(List<List<News>> recommendListList);

    /**
     * 失败回调
     * @param s 错误信息
     */
    void getRecommendFail(String s);
}
