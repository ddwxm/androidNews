package somnus.example.com.minghuaapp.view.IView;

import somnus.example.com.minghuaapp.model.News;

/**
 * Created by Somnus on 2019/2/25.
 * 新闻详情
 */

public interface INewsDetailsActivity {

    /**
     * 获取成功
     * @param news 新闻
     */
    void getSuccess(News news);

    /**
     * 获取失败
     * @param e 错误信息
     */
    void getFail(String e);

    /**
     * 获取是否点赞
     * @param i 标志
     */
    void getIsPraise(int i);

    /**
     * 获取是否收藏
     * @param i 标志
     */
    void getIsCollection(int i);

    void PraiseSuccess();

    void PraiseFail(String e);

    void CancelPraiseSuccess();

    void CancelPraiseFail(String e);
}
