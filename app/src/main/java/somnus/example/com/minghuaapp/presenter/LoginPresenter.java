package somnus.example.com.minghuaapp.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.model.HttpReceptionBean;
import somnus.example.com.minghuaapp.presenter.IPresenter.ILoginPresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.RetrofitApi;
import somnus.example.com.minghuaapp.view.IView.ILoginActivity;

/**
 * Created by Somnus on 2019/2/15.
 * 登录Presenter
 */

public class LoginPresenter implements ILoginPresenter{
    private ILoginActivity iLoginActivity;

    public LoginPresenter(ILoginActivity iLoginActivity){
        this.iLoginActivity = iLoginActivity;
    }

    @Override
    public void identify(final String phone) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().IdentifyRequest(ConstantsUtil.VERSION,
                MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,"",phone);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iLoginActivity.identifySuccess(phone);
                }else {
                    try {
                        iLoginActivity.identifyFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iLoginActivity.identifyFail(t.getMessage());
            }
        });
    }

    @Override
    public void login(String info) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().LoginRequest(ConstantsUtil.VERSION,
                MingHuaApplication.did,ConstantsUtil.VERSION,MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
        ,"",info);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iLoginActivity.loginSuccess(response.body().getData().getInfo());
                }else {
                    try {
                        iLoginActivity.loginFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iLoginActivity.loginFail(t.getMessage());
            }
        });
    }

    @Override
    public void getUser(String info) {
        Call<HttpReceptionBean> call = RetrofitApi.getRequestInterface().GetUserRequest(ConstantsUtil.VERSION
                , MingHuaApplication.did,ConstantsUtil.VERSION,MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,info);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){
                    iLoginActivity.getUserSuccess(response.body().getData().getInfo());
                }else {
                    try {
                        iLoginActivity.getUserFail(response.errorBody().string());
                        Log.e("ERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                iLoginActivity.getUserFail(t.getMessage());
            }
        });
    }
}
