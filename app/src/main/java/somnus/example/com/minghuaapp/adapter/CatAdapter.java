package somnus.example.com.minghuaapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import somnus.example.com.minghuaapp.view.HanYuFragment;
import somnus.example.com.minghuaapp.view.RecommendFragment;
import somnus.example.com.minghuaapp.view.StarFragment;
import somnus.example.com.minghuaapp.view.VarietyFragment;
import somnus.example.com.minghuaapp.view.WatchFragment;

/**
 * Created by Somnus on 2018/4/12.
 * 探索ViewPager适配器
 */

public class CatAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 5;
    private String[] tableTitle;
    private Context mContext;
    private List<Fragment> mFragmentTab;
    private RecommendFragment recommendFragment; // 推荐
    private VarietyFragment varietyFragment; // 综艺
    private StarFragment starFragment; //明星
    private HanYuFragment hanYuFragment; // 韩娱
    private WatchFragment watchFragment; // 看点

    public CatAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        mContext = context;
        tableTitle = new String[] {"推荐","综艺","明星","韩娱","看点"};
        initFragmentTab();
    }

    public CatAdapter(FragmentManager fragmentManager, Context context,String[] tableTitle) {
        super(fragmentManager);
        mContext = context;
        this.tableTitle = tableTitle;
        initFragmentTab();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentTab.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitle[position];
    }

    private void initFragmentTab() {

        recommendFragment = new RecommendFragment();
        varietyFragment = new VarietyFragment();
        starFragment = new StarFragment();
        hanYuFragment = new HanYuFragment();
        watchFragment = new WatchFragment();

        mFragmentTab = new ArrayList<Fragment>();
        mFragmentTab.add(recommendFragment);
        mFragmentTab.add(varietyFragment);
        mFragmentTab.add(starFragment);
        mFragmentTab.add(hanYuFragment);
        mFragmentTab.add(watchFragment);

    }
}

