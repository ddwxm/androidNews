package somnus.example.com.minghuaapp.view;

import android.os.Bundle;
import android.support.constraint.solver.LinearSystem;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.adapter.CatAdapter;
import somnus.example.com.minghuaapp.model.HttpReceptionCat;
import somnus.example.com.minghuaapp.view.IView.IHomeFragment;

/**
 * Created by Somnus on 2019/2/23.
 * 首页
 */

public class HomeFragment extends Fragment implements IHomeFragment {
    private Unbinder unbinder;
    private View rootView;
    private List<HttpReceptionCat.Cat> cats;
    @BindView(R.id.home_tab)
    public SlidingTabLayout home_tab;
    @BindView(R.id.home_viewPager)
    public ViewPager home_viewPager;
    @BindView(R.id.home_search_txt)
    public LinearLayout home_search_txt;
    @BindView(R.id.home_search_translation)
    public ImageView home_search_translation;
    private CatAdapter catAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind( this , rootView );
        init();
        return rootView;
    }

    private void init(){
        if (cats != null){
            String [] title = new String[cats.size()];
            for (int i = 0;i<cats.size();i++){
                title[i] = cats.get(i).getCatname();
            }
            catAdapter = new CatAdapter(getChildFragmentManager(),getContext(),title);
            Log.e("TAG","CAT");
        }else {
            catAdapter = new CatAdapter(getChildFragmentManager(),getContext());
        }
        home_viewPager.setAdapter(catAdapter);
        home_viewPager.setCurrentItem(0);
        home_tab.setViewPager(home_viewPager);

    }

    public void getCatList(List<HttpReceptionCat.Cat> catList){
        this.cats = catList;
    }
}
