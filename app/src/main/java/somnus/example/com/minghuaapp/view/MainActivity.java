package somnus.example.com.minghuaapp.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.model.JGBean;
import somnus.example.com.minghuaapp.model.MessageEvent;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.BottomNavigationViewUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.JGReceiver;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;

import static somnus.example.com.minghuaapp.MingHuaApplication.toastShow;

/**
 * 主文件
 * Created by Somnus on 2019/1/23.
 */

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener
        {
    private JGReceiver jgReceiver;
    private IntentFilter intentFilter;
    @BindView(R.id.main_bnv)
    public BottomNavigationView bottomNavigationView;
    private MineFragment mineFragment;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private Fragment fromFragment = null;
    public List<HttpReceptionCat.Cat> cats;
    public int message = 0;
    public enum RequestCode { LOGIN, LOGOUT,SITING }
    private List<JGBean> jgBeans = new ArrayList<>();
    @BindView(R.id.main_badge)
    public TextView main_badge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().navigationBarDarkIcon(true).statusBarDarkFont(true, 0.2f).init();
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this) ;
        EventBus.getDefault().register(this);
        initIntent();
        init();
        // 默认显示 homeFragment
        homeFragment.getCatList(cats);
        switchFragment(homeFragment);
    }


    private void initIntent() {
        Intent intent = getIntent();
        cats = (List<HttpReceptionCat.Cat>) intent.getSerializableExtra("Cat");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Gson gson = new Gson();
        message = Integer.parseInt(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_NUM,"0"));
        message += 1;
        JGBean jgBean = gson.fromJson(messageEvent.getMessage(),JGBean.class);
        if (SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_NUM,"999").equals("999")){
            SharedPreferencesUtils.putString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_NUM,message+"");
        }else {
            SharedPreferencesUtils.updateData(ConstantsUtil.CACHE_MESSAGE, ConstantsUtil.CACHE_KEY_NUM, message + "");
        }
        jgBean.setNum(message);
        main_badge.setVisibility(View.VISIBLE);
        main_badge.setText(message+"");
        messageFragment.getJGBean(jgBean);
        Log.e("JG","JG");
    }


    private void init() {
        BottomNavigationViewUtil.disableShiftMode(bottomNavigationView);
        mineFragment = new MineFragment();
        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        Menu bnvMenu = bottomNavigationView.getMenu();
        bnvMenu.findItem(R.id.navigation_mine).setOnMenuItemClickListener(this);
        bnvMenu.findItem(R.id.navigation_home).setOnMenuItemClickListener(this);
        bnvMenu.findItem(R.id.navigation_message).setOnMenuItemClickListener(this);
        if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE, ConstantsUtil.CACHE_KEY_NUM, "0").equals("0")) {
            main_badge.setVisibility(View.VISIBLE);
            main_badge.setText(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_NUM,"1"));
        } else {
            main_badge.setVisibility(View.GONE);
        }
    }

    /**
     * 切换 fragment
     * @param toFragment 将要显示的 fragment
     */
    private void switchFragment(Fragment toFragment) {
        if (fromFragment != null) {
            // 如果当前显示的 fragment 存在
            if (fromFragment.equals(toFragment)) {
                // 但却和将要显示的相同，那么就什么也不做
                return;
            } else if (!toFragment.isAdded()) {
                // 并且将要显示的 fragment 未被添加过
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .hide(fromFragment) // 隐藏当前显示的
                        .add(R.id.main_fragment, toFragment) // 添加并显示将要显示的
                        .commit();
            } else {
                // 并且将要显示的 fragment 也存在
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .hide(fromFragment) // 隐藏当前显示的
                        .show(toFragment) // 显示出即将要显示的
                        .commit();
            }
        } else {
            // 如果当前显示的 fragment 不存在（一般在第一次挑用这个方法时）
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .add(R.id.main_fragment, toFragment) // 直接显示将要显示的即可
                    .commit();
        }
        fromFragment = toFragment; // 更新当前 fragment
    }

    public void IntentActivity(String type){
        Intent intent;
        switch (type){
            case "login":
                intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, RequestCode.LOGIN.ordinal());
                break;
            case "setting":
                intent = new Intent(this, SettingActivity.class);
                startActivityForResult(intent, RequestCode.LOGOUT.ordinal());
                break;
        }
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchFragment(homeFragment);
                break;
            case R.id.navigation_message:
                switchFragment(messageFragment);
                break;
            case R.id.navigation_mine:
                switchFragment(mineFragment);
                break;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.LOGIN.ordinal()) {
            if (resultCode == LoginActivity.ResultCode.LOGIN_SUCCESS.ordinal()) {
                mineFragment.isLogin();
                messageFragment.isLogin();
            } else {
                // do noting
            }
        }else if (requestCode == RequestCode.LOGOUT.ordinal()) {
            if (resultCode == SettingActivity.ResultCode.LOGOUT_SUCCESS.ordinal()) {
                mineFragment.isLogin();
                messageFragment.isLogin();
            } else {
                // do noting
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}


