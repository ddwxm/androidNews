package somnus.example.com.minghuaapp;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;

/**
 * MingHuaApplication
 * Created by Somnus on 2019/2/12.
 */

public class MingHuaApplication extends MultiDexApplication {
    // 设备屏幕缩放级别
    public static float density;
    private static Toast toast;
    private static Context context;

    public static String did = Build.SERIAL;
    public static String type = "android" ;
    public static boolean isLogin = false;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        density = getResources().getDisplayMetrics().density;

        // 极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        jPushSet();
        // 储存工具初始化
        SharedPreferencesUtils.init(this);
        isLoginOverdue();

    }

    public void isLoginOverdue(){
        if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")) {
            Log.e("TIME",DateTimeUtil.stampToDate((new AesUtil())
                    .getUser(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME, ConstantsUtil.CACHE_KEY_USER, "")).getTime_out())+"");
            if (DateTimeUtil.Current_Time(5) == DateTimeUtil.stampToDateMonth((new AesUtil())
                    .getUser(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME, ConstantsUtil.CACHE_KEY_USER, "")).getTime_out())){
                if ((DateTimeUtil.Current_Time(0) - DateTimeUtil.stampToDate((new AesUtil())
                        .getUser(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME, ConstantsUtil.CACHE_KEY_USER, "")).getTime_out())) > 7) {
                    if (SharedPreferencesUtils.deleteData(ConstantsUtil.CACHE_NAME)){
                        toastShow(this, "登录已过期", 2000);
                    }
                }else {
                }
            }
        }else {

        }
    }

    public static Context getContext() {
        return context;
    }

    private void jPushSet() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable =  R.drawable.ic_logo_red_40dp;
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);

        /**
         * 设置通知栏样式 - 定义通知栏Layout
         */

        CustomPushNotificationBuilder builder1 =
                new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout,
                        R.id.icon, R.id.title, R.id.text);
        builder1.layoutIconDrawable = R.drawable.ic_logo_red_40dp;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder1);
    }

    /**
     * 弹出一条 全局 Toast
     * @param msg Toast 内容
     * @param duration Toast 持续时间
     */
    public static void toastShow(Context context, String msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
            toast.setDuration(duration);
        }
        toast.show();
    }

    /**
     * 把文件路径转换为 content uri 以适配 Android N
     * @param file 文件对象
     */
    public static Uri getContentUri(Context context, File file) {
        return FileProvider.getUriForFile(context, "com.xiaoming.bomda.fileprovider", file);
    }

    /**
     * 检查网络连接是否可用
     * @return true 表示可用，false 表示不可用
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected();
    }
}
