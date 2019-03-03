package somnus.example.com.minghuaapp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.presenter.IPresenter.ILoginPresenter;
import somnus.example.com.minghuaapp.presenter.LoginPresenter;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.CustomProgressDialog;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;
import somnus.example.com.minghuaapp.view.IView.ILoginActivity;

/**
 * Created by Somnus on 2019/2/15.
 * 登录Activity
 */

public class LoginActivity extends AppCompatActivity implements ILoginActivity
        ,View.OnClickListener {
    private ILoginPresenter iLoginPresenter;
    @BindViews({R.id.login_code,R.id.login_password,R.id.login_verify})
    public List<RelativeLayout> login_page;
    @BindView(R.id.login_code_input_area)
    public LinearLayout login_code_input_area;
    @BindViews({R.id.login_code_close,R.id.login_password_close,R.id.login_verify_back,R.id.login_code_other_qq
            ,R.id.login_password_other_qq,R.id.login_code_login_image})
    public List<ImageView> login_image;
    @BindViews({R.id.login_code_type,R.id.login_code_help,R.id.login_code_treaty,R.id.login_code_privacy
            ,R.id.login_password_type,R.id.login_password_help,R.id.login_verify_code_phone,R.id.login_verify_code_timer
            ,R.id.login_code_input_area_num})
    public List<TextView> login_text;
    @BindView(R.id.login_verify_input_code)
    public PinEntryEditText login_verify_code;
    @BindViews({R.id.login_code_input_phone,R.id.login_password_input_phone,R.id.login_password_input_password})
    public List<EditText> login_input;
    @BindViews({R.id.login_code_login_btn,R.id.login_password_login_btn})
    public List<Button> login_button;
    @BindView(R.id.login_code_login_progressBar)
    public ProgressBar login_progressBar;
    private CustomProgressDialog progressDialogUtil;
    private String phone;
    public enum ResultCode { LOGIN_SUCCESS, LOGIN_CLOSE }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().statusBarDarkFont(true, 0.2f).init();
        setContentView(R.layout.activity_login);
        ButterKnife.bind( this) ;
        iLoginPresenter = new LoginPresenter(this);
        init();
    }

    private void init(){
        progressDialogUtil = new CustomProgressDialog(LoginActivity.this,"登录中...");
        login_page.get(0).setVisibility(View.VISIBLE);
        login_page.get(1).setVisibility(View.GONE);
        login_page.get(2).setVisibility(View.GONE);
        login_button.get(0).setEnabled(false);
        login_button.get(0).setClickable(false);
        login_button.get(0).setOnClickListener(this);
        login_input.get(0).setOnClickListener(this);
        login_input.get(0).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() != 11){
                    login_button.get(0).setEnabled(false);
                    login_button.get(0).setClickable(false);
                    login_button.get(0).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }else {
                    login_button.get(0).setEnabled(true);
                    login_button.get(0).setClickable(true);
                    login_button.get(0).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 11){
                    login_button.get(0).setEnabled(false);
                    login_button.get(0).setClickable(false);
                    login_button.get(0).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }else {
                    login_button.get(0).setEnabled(true);
                    login_button.get(0).setClickable(true);
                    login_button.get(0).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 11){
                    login_button.get(0).setEnabled(false);
                    login_button.get(0).setClickable(false);
                    login_button.get(0).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }else {
                    login_button.get(0).setEnabled(true);
                    login_button.get(0).setClickable(true);
                    login_button.get(0).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }
            }
        });
        login_verify_code.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                String info = null;
                try {
                    info = new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp()).assemblyLoginBody(phone,"",str.toString());
                }catch (Exception e){
                    MingHuaApplication.toastShow(LoginActivity.this,e.getMessage(),2000);
                }
                iLoginPresenter.login(info);
                progressDialogUtil.show();
            }
        });

        login_text.get(0).setOnClickListener(this);
        login_text.get(4).setOnClickListener(this);
        login_button.get(1).setEnabled(false);
        login_button.get(1).setClickable(false);
        login_input.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 11 && !login_input.get(2).getText().toString().equals("")){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11 && !login_input.get(2).getText().toString().equals("")){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11 && !login_input.get(2).getText().toString().equals("")){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }
        });
        login_input.get(2).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().equals("") && login_input.get(1).getText().length() == 11){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("") && login_input.get(1).getText().length() == 11){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && login_input.get(1).getText().length() == 11){
                    login_button.get(1).setEnabled(true);
                    login_button.get(1).setClickable(true);
                    login_button.get(1).setBackground(getResources().getDrawable(R.drawable.ic_login_btn_input));
                }else {
                    login_button.get(1).setEnabled(false);
                    login_button.get(1).setClickable(false);
                    login_button.get(1).setBackground(getResources().getDrawable(R.color.ButtonColor));
                }
            }
        });
        login_button.get(1).setOnClickListener(this);
        login_image.get(0).setOnClickListener(this);
        login_image.get(1).setOnClickListener(this);
        login_input.get(1).setOnClickListener(this);
        login_input.get(2).setOnClickListener(this);
        login_image.get(2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_code_input_phone:
                login_input.get(0).setCursorVisible(true);
                break;
            case R.id.login_password_input_phone:
                login_input.get(1).setCursorVisible(true);
                break;
            case R.id.login_password_input_password:
                login_input.get(2).setCursorVisible(true);
                break;
            case R.id.login_code_login_btn:
                login_progressBar.setVisibility(View.VISIBLE);
                login_button.get(0).setVisibility(View.GONE);
                iLoginPresenter.identify(login_input.get(0).getText().toString());
                break;
            case R.id.login_code_type:
                login_page.get(0).setVisibility(View.GONE);
                login_page.get(1).setVisibility(View.VISIBLE);
                break;
            case R.id.login_password_type:
                login_page.get(0).setVisibility(View.VISIBLE);
                login_page.get(1).setVisibility(View.GONE);
                break;
            case R.id.login_password_login_btn:
                String info = null;
                try {
                    info = new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp()).assemblyLoginBody(login_input.get(1).getText().toString()
                            ,login_input.get(2).getText().toString(),"");
                }catch (Exception e){
                    MingHuaApplication.toastShow(LoginActivity.this,e.getMessage(),2000);
                }
                iLoginPresenter.login(info);
                progressDialogUtil.show();
                break;
            case R.id.login_code_close:
                finish();
                break;
            case R.id.login_password_close:
                finish();
                break;
            case R.id.login_verify_back:
                login_page.get(0).setVisibility(View.VISIBLE);
                login_page.get(2).setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 时间触发器
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            login_text.get(7).setText((millisUntilFinished / 1000) + "秒后可重发");
            login_text.get(7).setEnabled(false);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onFinish() {
            login_text.get(7).setEnabled(true);
            login_text.get(7).setText("重发验证码");
            login_text.get(7).setTextColor(R.color.colorPrimary);
        }
    };

    @Override
    public void identifySuccess(String phone) {
        this.phone = phone;
        login_progressBar.setVisibility(View.GONE);
        login_button.get(0).setVisibility(View.VISIBLE);
        login_button.get(0).setText("已发送");
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task
                login_page.get(0).setVisibility(View.GONE);
                login_page.get(2).setVisibility(View.VISIBLE);
                timer.start();
            }
        }, 1000);
        login_text.get(6).setText("+86"+phone);
    }

    @Override
    public void identifyFail(String e) {
        login_progressBar.setVisibility(View.GONE);
        login_button.get(0).setVisibility(View.VISIBLE);
        login_button.get(0).setText("重新发送");
        MingHuaApplication.toastShow(LoginActivity.this,e,2000);
    }

    @Override
    public void loginSuccess(String info) {
        Log.e("TAG",info);
        if (!SharedPreferencesUtils.getString("User","token","").equals("")){
            SharedPreferencesUtils.putString("User","token",info);
        }else {
            SharedPreferencesUtils.updateData("User","token",info);
        }
        String data = null;
        try {
            data = new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp()).assemblyUserBody(info);
        }catch (Exception e){
            MingHuaApplication.toastShow(LoginActivity.this,e.getMessage(),2000);
        }
        iLoginPresenter.getUser(data);
    }

    @Override
    public void loginFail(String e) {
        MingHuaApplication.toastShow(LoginActivity.this,"登录失败"+e,2000);
        Log.e("LOGIN_ERROR",e);
        progressDialogUtil.dismiss();
    }

    @Override
    public void getUserSuccess(String info) {
        progressDialogUtil.dismiss();
        MingHuaApplication.toastShow(LoginActivity.this,"登录成功",2000);
        SharedPreferencesUtils.putString("User","user",info);
        Log.e("TIMEOU",(((new AesUtil()).getUser(info).getTime_out()))+"");
        setResult(ResultCode.LOGIN_SUCCESS.ordinal());
        JPushInterface.setAlias(LoginActivity.this, ConstantsUtil.JS_SEQUENCE, (((new AesUtil()).getUser(info).getUsername())));
        finish();
    }

    @Override
    public void getUserFail(String e) {
        MingHuaApplication.toastShow(LoginActivity.this,"登录失败"+e,2000);
        Log.e("LOGIN_ERROR",e);
        progressDialogUtil.dismiss();
    }
}
