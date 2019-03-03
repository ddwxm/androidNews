package somnus.example.com.minghuaapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.adapter.NoticeAdapter;
import somnus.example.com.minghuaapp.model.JGBean;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;
import somnus.example.com.minghuaapp.view.IView.IMessageFragment;

import static somnus.example.com.minghuaapp.MingHuaApplication.toastShow;

/**
 * Created by Somnus on 2019/2/26.
 * 消息
 */

public class MessageFragment extends Fragment implements IMessageFragment
        ,View.OnClickListener {
    private Unbinder unbinder;
    private View rootView;
    @BindView(R.id.message_noMessage)
    public RelativeLayout message_noMessage;
    @BindView(R.id.message_login_text)
    public LinearLayout message_text;
    private JGBean jgBean;
    private List<JGBean> jgBeans = new ArrayList<>();
    @BindView(R.id.message_notifications_re)
    public RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind( this , rootView );
        init();
        isLogin();
        initClick();
        return rootView;
    }

    private void init(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        noticeAdapter = new NoticeAdapter(getContext(),recyclerView);
        recyclerView.setAdapter(noticeAdapter);
    }

    public void isLogin(){
        if (SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
            Log.e("JJJ","JJJ");
            message_noMessage.setVisibility(View.VISIBLE);
            message_text.setVisibility(View.VISIBLE);
        } else {
            Log.e("LIST",SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE, ConstantsUtil.CACHE_KEY_LIST, "0"));
            if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE, ConstantsUtil.CACHE_KEY_LIST, "0").equals("0")) {
                message_noMessage.setVisibility(View.GONE);
                message_text.setVisibility(View.GONE);
                Gson gson = new Gson();
                List<JGBean> jgBeans = gson.fromJson(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_LIST,""),new TypeToken<List<JGBean>>() {}.getType());
                noticeAdapter.setNoticeList(jgBeans);
            } else {
                message_noMessage.setVisibility(View.VISIBLE);
                message_text.setVisibility(View.GONE);
            }
        }
    }

    public void getJGBean(JGBean jgBean){
        this.jgBean = jgBean;
        jgBeans.add(jgBean);
        Gson gson = new Gson();
        String json = gson.toJson(jgBeans);
        Log.e("JSON",json);
        if (SharedPreferencesUtils.getString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_LIST,"999").equals("999")){
            SharedPreferencesUtils.putString(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_LIST,json);
        }else {
            SharedPreferencesUtils.updateData(ConstantsUtil.CACHE_MESSAGE, ConstantsUtil.CACHE_KEY_LIST, json);
        }
        noticeAdapter.setNoticeList(jgBeans);
        message_noMessage.setVisibility(View.GONE);
    }

    private void initClick(){
        message_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_login_text:
                if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
                    Log.e("USER",SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"TAG"));
                } else {
                    Log.e("USER",SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"TAG"));
                }
                break;
        }
    }


    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

}
