package somnus.example.com.minghuaapp.util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import somnus.example.com.minghuaapp.util.api.HttpRequestService;

/**
 * Created by Somnus on 2019/2/15.
 *
 */

public class RetrofitApi {
    private static Retrofit retrofit;
    private static final int DEFAULT_TIMEOUT = 10;//超时时间
    private static volatile HttpRequestService httpRequestService = null;

    /**
     * 创建网络请求接口实例
     *
     * @return
     */
    public static HttpRequestService getRequestInterface() {
        if (httpRequestService == null) {
            synchronized (HttpRequestService.class) {
                httpRequestService = provideRetrofit().create(HttpRequestService.class);
            }
        }
        return httpRequestService;
    }

    /**
     * 初始化必要对象和参数
     *
     * @return
     */
    private static Retrofit provideRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())//Application拦截器
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsUtil.HOST)//设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava平台
                .client(client)
                .build();

        return retrofit;
    }

    /**
     * 日志拦截器
     *
     * @return
     */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("OkHttpLog", "log = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }



    /**
     * 请求头拦截器
     *
     * @return
     */
    private static Interceptor getRequestHeader() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request originalRequest = chain.request();
                okhttp3.Request.Builder builder = originalRequest.newBuilder();
                builder.addHeader("Content-Type", "application/json; charset=utf-8");
                builder.addHeader("token", "d08986536b5e3678119aac9b892439a8");
                okhttp3.Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
                okhttp3.Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }


}
