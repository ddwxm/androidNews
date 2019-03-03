package somnus.example.com.minghuaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import somnus.example.com.minghuaapp.MingHuaApplication;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.adapter.NewsAdapter;
import somnus.example.com.minghuaapp.model.News;
import somnus.example.com.minghuaapp.presenter.IPresenter.IRecommendPresenter;
import somnus.example.com.minghuaapp.presenter.RecommendPresenter;
import somnus.example.com.minghuaapp.util.CustomProgressDialog;
import somnus.example.com.minghuaapp.view.IView.IRecommendFragment;

/**
 * Created by Somnus on 2019/2/23.
 * 推荐
 */

public class RecommendFragment extends Fragment implements IRecommendFragment
        ,SwipeRefreshLayout.OnRefreshListener{
    private Unbinder unbinder;
    private View rootView;
    @BindView(R.id.fragment_recommend_banner)
    public Banner banner;
    @BindView(R.id.fragment_recommend_re)
    public RecyclerView recyclerView;
    private IRecommendPresenter iRecommendPresenter;
    private List<List<News>> recommendListList;
    private CustomProgressDialog customProgressDialog;
    @BindView(R.id.fragment_recommend_Refresh)
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind( this , rootView );
        iRecommendPresenter = new RecommendPresenter(this);
        iRecommendPresenter.getRecommendNews();
        init();
        return rootView;
    }

    private void init(){
        customProgressDialog = new CustomProgressDialog(getActivity(),"加载中...");
        customProgressDialog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initHeader(){
        final String[] images = new String[recommendListList.get(0).size()];
        String[] titles = new String[recommendListList.get(0).size()];
        for (int i =0;i<recommendListList.get(0).size();i++){
            images[i] = recommendListList.get(0).get(i).getImage();
            titles[i] = recommendListList.get(0).get(i).getTitle();
        }
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true) ;

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getContext()).load(url).into(view);
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Intent intent = new Intent(getContext(),NewsDetailsActivity.class);
                intent.putExtra("News",(Serializable) recommendListList.get(0).get(position-1));
                startActivity(intent);
            }
        });
    }

    private void initNews(){
        NewsAdapter newsAdapter = new NewsAdapter(getContext(),recyclerView);
        List<News> news = new ArrayList<>();
        news.addAll(recommendListList.get(1));
        newsAdapter.setActivitiesList(news);
        recyclerView.setAdapter(newsAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void getRecommendSuccess(List<List<News>> recommendListList) {
        this.recommendListList = recommendListList;
        customProgressDialog.dismiss();
        initHeader();
        initNews();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
            MingHuaApplication.toastShow(getContext(),"刷新成功",2000);
        }
    }

    @Override
    public void getRecommendFail(String s) {
        MingHuaApplication.toastShow(getContext(),"页面数据加载失败"+s,2000);
        customProgressDialog.dismiss();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
            MingHuaApplication.toastShow(getContext(),"刷新失败",2000);
        }
    }

    @Override
    public void onRefresh() {
        iRecommendPresenter.getRecommendNews();
    }
}
