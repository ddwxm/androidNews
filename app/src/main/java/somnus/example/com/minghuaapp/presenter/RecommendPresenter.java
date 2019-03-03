package somnus.example.com.minghuaapp.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.model.HttpReceptionRecommend;
import somnus.example.com.minghuaapp.presenter.IPresenter.IRecommendPresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.RetrofitApi;
import somnus.example.com.minghuaapp.view.IView.IRecommendFragment;

/**
 * Created by Somnus on 2019/2/23.
 * 推荐
 */

public class RecommendPresenter implements IRecommendPresenter {
    private IRecommendFragment iRecommendFragment;

    public RecommendPresenter(IRecommendFragment iRecommendFragment){
        this.iRecommendFragment = iRecommendFragment;
    }

    @Override
    public void getRecommendNews() {
        Call<HttpReceptionRecommend> call = RetrofitApi.getRequestInterface().RecommendRequest(ConstantsUtil.VERSION, MingHuaApplication.did,
                ConstantsUtil.VERSION,MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type),
        "");
        call.enqueue(new Callback<HttpReceptionRecommend>() {
            @Override
            public void onResponse(Call<HttpReceptionRecommend> call, Response<HttpReceptionRecommend> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iRecommendFragment.getRecommendSuccess(response.body().getData().getList());
                }else {
                    try {
                        iRecommendFragment.getRecommendFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionRecommend> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                iRecommendFragment.getRecommendFail(t.getMessage());
            }
        });
    }
}
