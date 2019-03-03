package somnus.example.com.minghuaapp.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.model.HttpReceptionBean;
import somnus.example.com.minghuaapp.model.NewsDetails;
import somnus.example.com.minghuaapp.presenter.IPresenter.INewsDetailsPresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.RetrofitApi;
import somnus.example.com.minghuaapp.view.IView.INewsDetailsActivity;

/**
 * Created by Somnus on 2019/2/25.
 * 新闻详情
 */

public class NewsDetailsPresenter implements INewsDetailsPresenter {
    private INewsDetailsActivity iNewsDetailsActivity;

    public NewsDetailsPresenter(INewsDetailsActivity iNewsDetailsActivity){
        this.iNewsDetailsActivity = iNewsDetailsActivity;
    }

    @Override
    public void getNewsDetails(final int id) {
        Call<NewsDetails> call = RetrofitApi.getRequestInterface().GetNewsDetailsRequest(ConstantsUtil.VERSION,
                id,MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,"");
        call.enqueue(new Callback<NewsDetails>() {
            @Override
            public void onResponse(Call<NewsDetails> call, Response<NewsDetails> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iNewsDetailsActivity.getSuccess(response.body().getData().getList());
                }else {
                    try {
                        iNewsDetailsActivity.getFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsDetails> call, Throwable t) {
                iNewsDetailsActivity.getFail(t.getMessage());
            }
        });
    }

    @Override
    public void getIsPraise(int id, String info) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().GetNewsIsPraiseRequest(ConstantsUtil.VERSION,
                id,MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,info);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iNewsDetailsActivity.getIsPraise(Integer.parseInt(response.body().getData().getInfo()));
                }else {
                    try {
                        iNewsDetailsActivity.getFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iNewsDetailsActivity.getFail(t.getMessage());
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void getIscollection(int id, String info) {

    }

    @Override
    public void PraiseNews(int id, String info) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().UpvoteRequest(ConstantsUtil.VERSION
                ,MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,info,id);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1 && response.body().getData().getInfo().equals("OK")){
                    iNewsDetailsActivity.PraiseSuccess();
                }else {
                    try {
                        iNewsDetailsActivity.PraiseFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iNewsDetailsActivity.PraiseFail(t.getMessage());
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void CancelPraiseNews(int id, String info) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().CanleUpvoteRequest(ConstantsUtil.VERSION
                ,MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,info,id);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1 && response.body().getData().getInfo().equals("OK")){
                    iNewsDetailsActivity.CancelPraiseSuccess();
                }else {
                    try {
                        iNewsDetailsActivity.CancelPraiseFail(response.body().getData().getInfo());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iNewsDetailsActivity.CancelPraiseFail(t.getMessage());
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}
