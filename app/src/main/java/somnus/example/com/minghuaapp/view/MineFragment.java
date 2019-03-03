package somnus.example.com.minghuaapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.User;
import somnus.example.com.minghuaapp.util.AesUtil;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;
import somnus.example.com.minghuaapp.view.IView.IMineFragment;

/**
 * Created by Somnus on 2019/2/22.
 * 我的
 */

public class MineFragment extends Fragment implements IMineFragment,
        View.OnClickListener {
    private Unbinder unbinder;
    @BindViews({R.id.mine_isLogin,R.id.mine_noLogin})
    public List<RelativeLayout> login_page;
    private View rootView;
    @BindViews({R.id.mine_dynamic_num,R.id.mine_love_num,R.id.mine_focus_num,R.id.mine_collection_num,R.id.mine_tool})
    public List<TextView> mine_textView;
    @BindView(R.id.mine_nick)
    public TextView mine_text;
    @BindString(R.string.mine_null)
    public String mine_null;
    @BindViews({R.id.mine_avatar,R.id.mine_sex})
    public List<ImageView> mine_image;
    @BindView(R.id.mine_go_login)
    public Button mine_btn;
    public enum RequestCode { LOGIN, LOGOUT,SITING }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind( this , rootView );
        isLogin();
        initClick();
        return rootView;
    }

    private void initClick(){
        mine_btn.setOnClickListener(this);
        mine_textView.get(4).setOnClickListener(this);
    }

    /**
     * 是否登录
     */
    public void isLogin(){
        if (SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
            login_page.get(0).setVisibility(View.GONE);
            login_page.get(1).setVisibility(View.VISIBLE);
        } else {
            showCurrentUserInfo((new AesUtil()).getUser(SharedPreferencesUtils.getString("User","user","")));
            login_page.get(0).setVisibility(View.VISIBLE);
            login_page.get(1).setVisibility(View.GONE);
        }
    }

    /**
     * 加载用户信息
     * @param user 用户信息对象
     */
    @SuppressLint("SetTextI18n")
    public void showCurrentUserInfo(User user){
        if (user != null){
            mine_textView.get(0).setText(user.getDynamic_count()+mine_null);
            mine_textView.get(1).setText(user.getWpraise_count()+mine_null);
            mine_textView.get(2).setText(user.getFocus_count()+mine_null);
            mine_textView.get(3).setText(user.getCollection_count()+mine_null);
            Glide.with(this)
                    .load(user.getImage())
                    .apply(new RequestOptions().circleCrop().error(R.drawable.ic_pic_error_grey_24dp)
                            .placeholder(R.drawable.ic_avatar_24dp))
                    .into(mine_image.get(0));
            // 加载性别
            int sex = user.getSex();
            switch (sex){
                case 0:
                    mine_image.get(1).setImageResource(R.drawable.ic_man_blue_24dp);
                    break;
                case 1:
                    mine_image.get(1).setImageResource(R.drawable.ic_woman_pink_24dp);
                    break;
            }
            if (user.getNick() != null){
                mine_text.setText(user.getNick());
            }
        }else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_go_login:
                assert ((MainActivity)getActivity()) != null;
                ((MainActivity)getActivity()).IntentActivity("login");
                break;
            case R.id.mine_tool:
                assert ((MainActivity)getActivity()) != null;
                ((MainActivity)getActivity()).IntentActivity("setting");
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
