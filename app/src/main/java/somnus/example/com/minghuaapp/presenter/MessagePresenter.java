package somnus.example.com.minghuaapp.presenter;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.model.NewsDetails;
import somnus.example.com.minghuaapp.model.Notice;
import somnus.example.com.minghuaapp.presenter.IPresenter.IMessagePresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.RetrofitApi;
import somnus.example.com.minghuaapp.view.IView.IMessageFragment;

/**
 * Created by Somnus on 2019/2/28.
 * 消息
 */

public class MessagePresenter implements IMessagePresenter {
    private IMessageFragment iMessageFragment;

    public MessagePresenter(IMessageFragment iMessageFragment){
        this.iMessageFragment = iMessageFragment;
    }

    @Override
    public void getNotice(int id) {
        Call<Notice> call = RetrofitApi.getRequestInterface().GetNotificationRequest(ConstantsUtil.VERSION,
                id, MingHuaApplication.did,ConstantsUtil.VERSION, MingHuaApplication.type,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type)
                ,(new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp())).assembHeaderSign( MingHuaApplication.did,MingHuaApplication.type));
        call.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.isSuccessful() && response.body().getStatus() == 1){

                }else {

                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {

            }
        });
    }
}
