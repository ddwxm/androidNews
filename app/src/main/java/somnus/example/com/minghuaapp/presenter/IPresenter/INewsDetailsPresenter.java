package somnus.example.com.minghuaapp.presenter.IPresenter;

/**
 * Created by Somnus on 2019/2/25.
 * 新闻详情
 */

public interface INewsDetailsPresenter {

    /**
     * 获取新闻详情
     * @param id 新闻ID
     */
    void getNewsDetails(int id);

    void getIsPraise(int id,String info);

    void getIscollection(int id,String info);

    void PraiseNews(int id,String info);

    void CancelPraiseNews(int id,String info);

}
