package somnus.example.com.minghuaapp.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import retrofit2.Call;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import somnus.example.com.minghuaapp.model.HttpReceptionBean;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.util.api.HttpRequestService;

/**
 * Created by Somnus on 2019/2/14.
 * HTTP请求类
 */

public class HttpPostUtil {

    private static HttpPostUtil instance = null;
    private Retrofit retrofit = null;
    private Call<HttpReceptionBean> call;
    private HttpRequestService httpRequestService;
    private OkHttpClient.Builder httpClientBuilder;
    private static final int DEFAULT_TIMEOUT = 10000;
    private Handler mDelivery;

    private HttpPostUtil(){
        httpClientBuilder= new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(ConstantsUtil.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpRequestService = retrofit.create(HttpRequestService.class);
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static HttpPostUtil getInstance() {
        if (instance == null) {
            synchronized (HttpPostUtil.class) {
                if (instance == null) {
                    instance = new HttpPostUtil();
                }
            }
        }
        return instance;
    }

    private <T> void IdentifyAsynRequest(String ver, String did, String type,String sign,String access_user_token,
                                     String phone,final ResultCallback<T> mCallback) throws IOException{
        call = httpRequestService.IdentifyRequest(ver,did,ver,type,sign,access_user_token,phone);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (mCallback != null){
                    if (response.isSuccessful()){
                        HttpReceptionBean httpReceptionBean = response.body();
                        if (mCallback.mType != null){
                            if (mCallback.mType == HttpReceptionBean.class) {
                                exeSuccessCallback(httpReceptionBean,mCallback);
                            } else {
                                Exception e = new Exception(
                                        "AesEncryptionUtil.decrypt(string) return null!");
                                exeFailedCallback(call,e, mCallback);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                if (mCallback != null){
                    mCallback.onError(null,t);
                }
            }
        });
    }

    private <T> void getAsynRequest (String url, String did, String type,String sign,String access_user_token
            ,final ResultCallback<T> mCallback) throws IOException{
        call = httpRequestService.GetRequest(url,did,ConstantsUtil.VERSION,type,sign,access_user_token);
        call.enqueue(new Callback<HttpReceptionBean>() {
            @Override
            public void onResponse(Call<HttpReceptionBean> call, Response<HttpReceptionBean> response) {
                if (mCallback != null){
                    if (response.isSuccessful()){
                        HttpReceptionBean httpReceptionBean = response.body();
                        if (mCallback.mType != null){
                            if (mCallback.mType == HttpReceptionBean.class) {
                                exeSuccessCallback(httpReceptionBean,mCallback);
                            } else {
                                Exception e = new Exception(
                                        "AesEncryptionUtil.decrypt(string) return null!");
                                exeFailedCallback(call,e, mCallback);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpReceptionBean> call, Throwable t) {
                if (mCallback != null){
                    mCallback.onError(null,t);
                }
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void exeFailedCallback(final Call call, final Throwable e,
                                  final ResultCallback<?> callback) {
        if (callback != null) {
            callback.onError(call, e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void exeSuccessCallback(Object o, final ResultCallback callback) {
        if (callback != null) {
            callback.onResponse(o);
        }
    }

    public abstract static class ResultCallback<T> {
        public Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types
                    .canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        public abstract void onError(Call<T> request, Throwable e);

        public abstract void onResponse(T response);
    }

    public static void getAsync(String url, String did, String type,String sign
            ,String access_user_token,final ResultCallback callback) throws IOException{
         getInstance().getAsynRequest(url,did,type,sign,access_user_token,callback);
    }

    public static void postAsync(String ver, String did, String type,String sign
            ,String access_user_token,String phone,final ResultCallback callback) throws IOException{
        getInstance().IdentifyAsynRequest(ver,did,type,sign,access_user_token,phone,callback);
    }
}
