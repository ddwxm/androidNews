package somnus.example.com.minghuaapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.presenter.IPresenter.IWelcomePresenter;
import somnus.example.com.minghuaapp.presenter.WelcomePresenter;
import somnus.example.com.minghuaapp.view.IView.IWelcomeActivity;

/**
 * Created by Somnus on 2019/2/23.
 * 欢迎
 */

public class WelcomeActivity extends AppCompatActivity implements IWelcomeActivity {
    private IWelcomePresenter iWelcomePresenter;
    private final long DELAY = 3000; //自动跳转的延时
    private Timer timer;
    private List<HttpReceptionCat.Cat> cats = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        iWelcomePresenter = new WelcomePresenter(this);
        iWelcomePresenter.getCat();
    }

    @Override
    public void getCatSuccess(List<HttpReceptionCat.Cat> catList) {
        this.cats = catList;
    }

    @Override
    public void getCatFail(String e) {
        cats = null;
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

    /**
     * 如果 timer 正在执行，就取消他
     */
    private void cancelTimer() {
        // 如果 timer 正在执行，就取消他
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 延迟 delay 秒后自动进入主界面
     * @param delay 延时
     */
    private void autoStartMainActivity(final long delay) {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int repeatCount = (int) (delay / 1000);
            @Override
            public void run() { runOnUiThread( new Runnable() { @Override public void run() {
                if (repeatCount == 0) {
                    timer.cancel(); // 中止计时器
                    startMainActivity();
                }
                repeatCount -= 1;
            }});}
        };
        timer.schedule(timerTask, 0, 1000); // 立即执行，1000ms 重复一次
    }

    /**
     * 启动主界面
     */
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Cat",(Serializable) cats);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoStartMainActivity(DELAY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelTimer();
    }
}
