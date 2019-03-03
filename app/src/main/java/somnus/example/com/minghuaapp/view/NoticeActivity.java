package somnus.example.com.minghuaapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.view.IView.INoticeActivity;

/**
 * Created by Somnus on 2019/2/27.
 * 通知
 */

public class NoticeActivity extends AppCompatActivity implements INoticeActivity {
    @BindView(R.id.notice_toolbar)
    public Toolbar toolbarl;
    @BindView(R.id.notice_re)
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentBar().statusBarColor(R.color.colorWhite).fitsSystemWindows(true).navigationBarDarkIcon(true).statusBarDarkFont(true).init();
        setContentView(R.layout.activity_notice);
        ButterKnife.bind( this) ;
    }

    private void init(){
        recyclerView.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
