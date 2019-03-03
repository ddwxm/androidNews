package somnus.example.com.minghuaapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import java.io.File;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;

import static somnus.example.com.minghuaapp.MingHuaApplication.toastShow;

/**
 * Created by Somnus on 2019/2/22.
 * 设置
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    public enum ResultCode { LOGOUT_SUCCESS, NOT_LOGOUT }
    @BindView(R.id.exit_settings)
    public TextView setting_exit;
    @BindView(R.id.setting_toolbar)
    public Toolbar toolbar;
    @BindString(R.string.activity_setting)
    public String activity_setting;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().statusBarColor(R.color.colorWhite).fitsSystemWindows(true).navigationBarDarkIcon(true).statusBarDarkFont(true).init();
        setContentView(R.layout.activity_setting);
        ButterKnife.bind( this) ;
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle(activity_setting);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textPrimaryLight));
        this.setSupportActionBar(toolbar);
        initClick();
    }

    private void initClick(){
        setting_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_settings:
                if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
                    if (SharedPreferencesUtils.deleteData(ConstantsUtil.CACHE_NAME)){
                        JPushInterface.deleteAlias(SettingActivity.this,ConstantsUtil.JS_SEQUENCE);
                        toastShow(this,"退出登录成功",2000);
                        setResult(ResultCode.LOGOUT_SUCCESS.ordinal());
                        finish();
                    }
                } else {
                    toastShow(this,"您还未登录",2000);
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }
}
