package somnus.example.com.minghuaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.JGBean;
import somnus.example.com.minghuaapp.model.News;
import somnus.example.com.minghuaapp.util.ConstantsUtil;
import somnus.example.com.minghuaapp.util.SharedPreferencesUtils;
import somnus.example.com.minghuaapp.view.MainActivity;
import somnus.example.com.minghuaapp.view.NewsDetailsActivity;

/**
 * Created by Somnus on 2019/3/2.
 * 通知
 */

public class NoticeAdapter extends RecyclerView.Adapter {
    private RecyclerView recyclerView;
    private List<JGBean> jgBeans = new ArrayList<>();
    private Context context;

    public NoticeAdapter(Context context, RecyclerView rv){
        this.context = context;
        this.recyclerView = rv;
    }

    public void setNoticeList(List<JGBean> jgBeans) {
        this.jgBeans = new ArrayList<>(jgBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_notice, parent, false);
        return new NoticeItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoticeItemViewHolder) {
            JGBean n = jgBeans.get(jgBeans.size()-1);
            ((NoticeItemViewHolder) holder).setNotice(n);
        }
    }

    @Override
    public int getItemCount() {
        if (jgBeans.size() == 0){
            return jgBeans.size();
        }else {
            return 1;
        }
    }

    public class NoticeItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_notice_title,item_notice_creatTime,item_notice_nick,item_notice_badge;
        ImageView item_notice_image;
        private JGBean jgBean;

        public NoticeItemViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View view){
            item_notice_image = (ImageView) view.findViewById(R.id.item_notice_image);
            item_notice_title = (TextView) view.findViewById(R.id.item_notice_message);
            item_notice_creatTime = (TextView) view.findViewById(R.id.item_notice_time);
            item_notice_nick = (TextView) view.findViewById(R.id.item_notice_nick);
            item_notice_badge = (TextView) view.findViewById(R.id.item_notice_badge);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setNotice(JGBean jgBean) {
            this.jgBean = jgBean;
            item_notice_title.setText(jgBean.getPraiseUserNick()+"喜欢了您的帖子："+jgBean.getNewTitle());
            item_notice_nick.setText(jgBean.getPraiseUserNick());
            if (context instanceof MainActivity){
                item_notice_badge.setVisibility(View.GONE);
                new QBadgeView(context).bindTarget(itemView.findViewById(R.id.item_notice)).setBadgeGravity(Gravity.CENTER|Gravity.END)
                        .setGravityOffset(16,true).setShowShadow(false).setBadgeNumber(jgBean.getNum()).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (dragState == STATE_SUCCEED) {
                            ((MainActivity)context).main_badge.setVisibility(View.GONE);
                            ((MainActivity)context).message = 0;
                            SharedPreferencesUtils.updateData(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_NUM,"0");
                            SharedPreferencesUtils.updateData(ConstantsUtil.CACHE_MESSAGE,ConstantsUtil.CACHE_KEY_LIST,"0");
                        }
                    }
                });
                item_notice_creatTime.setVisibility(View.GONE);
            }else {
                item_notice_badge.setVisibility(View.GONE);
                item_notice_creatTime.setText(jgBean.getTime());
            }
            Glide.with(context).load(jgBean.getPraiseUserImage()).apply(new RequestOptions().circleCrop().error(R.drawable.ic_pic_error_grey_24dp)
                    .placeholder(R.drawable.ic_avatar_24dp)).into(item_notice_image);

        }

        public JGBean getJGBean() {
            return jgBean;
        }

    }
}
