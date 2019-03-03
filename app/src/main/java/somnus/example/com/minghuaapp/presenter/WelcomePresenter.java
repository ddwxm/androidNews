package somnus.example.com.minghuaapp.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.presenter.IPresenter.IWelcomePresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.RetrofitApi;
import somnus.example.com.minghuaapp.view.IView.IWelcomeActivity;

/**
 * Created by Somnus on 2019/2/23.
 * 欢迎
 */

public class WelcomePresenter implements IWelcomePresenter {
    private IWelcomeActivity iWelcomeAcitivy;

    public WelcomePresenter(IWelcomeActivity iWelcomeAcitivy){
        this.iWelcomeAcitivy = iWelcomeAcitivy;
    }


    @Override
    public void getCat() {
        Call<HttpReceptionCat> call = RetrofitApi.getRequestInterface().getCat(ConstantsUtil.VERSION,MingHuaApplication.did,
                ConstantsUtil.VERSION,MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
        );
        call.enqueue(new Callback<HttpReceptionCat>() {
            @Override
            public void onResponse(Call<HttpReceptionCat> call, Response<HttpReceptionCat> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iWelcomeAcitivy.getCatSuccess(response.body().getData());
                }else {
                    try {
                        iWelcomeAcitivy.getCatFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionCat> call, Throwable t) {
                iWelcomeAcitivy.getCatFail(t.getMessage());
            }
        });
    }
}
