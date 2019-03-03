package somnus.example.com.minghuaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chaychan.viewlib.NumberRunningTextView;
import com.gyf.barlibrary.ImmersionBar;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.model.News;
import somnus.example.com.minghuaapp.presenter.IPresenter.INewsDetailsPresenter;
import somnus.example.com.minghuaapp.presenter.NewsDetailsPresenter;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.CustomProgressDialog;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.util.HttpPostInfoUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;
import somnus.example.com.minghuaapp.view.IView.INewsDetailsActivity;

/**
 * Created by Somnus on 2019/2/24.
 * 新闻详情
 */

public class NewsDetailsActivity extends AppCompatActivity implements INewsDetailsActivity
        ,View.OnClickListener{
    @BindView(R.id.news_details_toolbar)
    public Toolbar toolbar;
    @BindViews({R.id.news_details_title,R.id.news_details_source_content,R.id.news_details_content_time,R.id.news_details_content
            ,R.id.news_details_readCount})
    public List<TextView> news_text;
    @BindViews({R.id.news_details_praise_sb,R.id.news_details_collection_sb,R.id.news_details_comment_sb})
    public List<ShineButton> shineButton;
    @BindView(R.id.news_details_collection_num)
    public TextView news_details_text;
    @BindViews({R.id.news_details_praise_num,R.id.news_details_comment_num})
    public List<NumberRunningTextView> news_details_num;
    private News news;
    @BindString(R.string.activity_news_details)
    public String activity_news_details;
    private INewsDetailsPresenter iNewsDetailsPresenter;
    private CustomProgressDialog customProgressDialog;
    private String data = null;
    private boolean isPraise = false;
    private int praise = 0,comment = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().statusBarColor(R.color.colorWhite).fitsSystemWindows(true).navigationBarDarkIcon(true).statusBarDarkFont(true).init();
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind( this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle(activity_news_details);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textPrimaryLight));
        this.setSupportActionBar(toolbar);
        initIntent();
        iNewsDetailsPresenter = new NewsDetailsPresenter(this);
        initPost();
        customProgressDialog.show();
    }

    private void initIntent(){
        Intent intent = getIntent();
        news = (News) intent.getSerializableExtra("News");
        customProgressDialog = new CustomProgressDialog(this,"正在加载中...");
        shineButton.get(0).setOnClickListener(this);
    }

    private void initPost(){
        if (SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
            iNewsDetailsPresenter.getNewsDetails(news.getId());
            shineButton.get(0).setChecked(false);
            shineButton.get(0).setClickable(false);
            shineButton.get(1).setChecked(false);
            shineButton.get(2).setChecked(false);
        }else {
            try {
                data = new HttpPostInfoUtil(DateTimeUtil.get13TimeStamp()).assemblyUserBody(SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME
                        ,ConstantsUtil.CACHE_KEY_TOKEN,""));
            }catch (Exception e){
                MingHuaApplication.toastShow(NewsDetailsActivity.this,e.getMessage(),2000);
            }
            iNewsDetailsPresenter.getIsPraise(news.getId(),data);
            iNewsDetailsPresenter.getNewsDetails(news.getId());
        }
    }

    private void loadingNews(News news){
        customProgressDialog.dismiss();
        news_text.get(0).setText(news.getTitle());
        switch (news.getSource_type()){
            case 0:
                news_text.get(1).setText("本站");
                break;
            case 1:
                news_text.get(1).setText("第三方");
                break;
        }
        news_text.get(2).setText(news.getCreate_time());
        news_text.get(3).setText(news.getContent().substring(4,(news.getContent().length()-4)));
        praise = news.getUpvote_count();
        comment = news.getComment_count();
        news_text.get(4).setText("阅读数:"+(news.getRead_count()+1));
        news_details_num.get(0).setContent(praise+"");
        news_details_num.get(1).setContent(comment+"");
    }

    @Override
    public void getSuccess(News news) {
        loadingNews(news);
    }

    @Override
    public void getFail(String e) {
        loadingNews(news);
        Log.e("ERROR",e);
    }

    @Override
    public void getIsPraise(int i) {
        switch (i){
            case 1:
                shineButton.get(0).setChecked(true);
                isPraise = true;
                break;
            case 0:
                shineButton.get(0).setChecked(false);
                isPraise = false;
                break;
        }
    }

    @Override
    public void getIsCollection(int i) {

    }

    @Override
    public void PraiseSuccess() {
        MingHuaApplication.toastShow(NewsDetailsActivity.this,"点赞成功",2000);
        shineButton.get(0).setChecked(true);
        isPraise = true;
        praise += 1;
        news_details_num.get(0).setContent(praise+"");
    }

    @Override
    public void PraiseFail(String e) {
        MingHuaApplication.toastShow(NewsDetailsActivity.this,e,2000);
        shineButton.get(0).setChecked(false);
        isPraise = false;
    }

    @Override
    public void CancelPraiseSuccess() {
        MingHuaApplication.toastShow(NewsDetailsActivity.this,"取消点赞成功",2000);
        isPraise = false;
        shineButton.get(0).setChecked(false);
        praise -= 1;
        news_details_num.get(0).setContent(praise+"");
    }

    @Override
    public void CancelPraiseFail(String e) {
        MingHuaApplication.toastShow(NewsDetailsActivity.this,e,2000);
        shineButton.get(0).setChecked(true);
        isPraise = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_details_praise_sb:
                if (!SharedPreferencesUtils.getString(ConstantsUtil.CACHE_NAME,ConstantsUtil.CACHE_KEY_USER,"").equals("")){
                    if (!shineButton.get(0).isChecked()){
                        Log.e("boolean","YES");
                        iNewsDetailsPresenter.CancelPraiseNews(news.getId(),data);
                    }else {
                        Log.e("boolean","NO");
                        iNewsDetailsPresenter.PraiseNews(news.getId(),data);
                    }
                } else {
                    MingHuaApplication.toastShow(NewsDetailsActivity.this,"请先登录",2000);
                    shineButton.get(0).setChecked(false);
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
