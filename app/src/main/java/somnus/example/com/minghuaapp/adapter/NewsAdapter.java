package somnus.example.com.minghuaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import somnus.example.com.minghuaapp.R;
import somnus.example.com.minghuaapp.model.News;
import somnus.example.com.minghuaapp.util.DateTimeUtil;
import somnus.example.com.minghuaapp.view.NewsDetailsActivity;

/**
 * Created by Somnus on 2018/4/4.
 * 活动Recyclerview适配器
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private RecyclerView recyclerView;
    private List<News> news = new ArrayList<>();
    private Context context;

    public NewsAdapter(Context context, RecyclerView rv){
        this.context = context;
        this.recyclerView = rv;
    }

    public void setActivitiesList(List<News> news) {
        this.news = new ArrayList<>(news);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_news, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsItemViewHolder) {
            News n = news.get(position);
            ((NewsItemViewHolder) holder).setNews(n);
        }
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_news_title,item_news_type,item_news_time;
        ImageView item_news_image;
        private News news;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View view){
            item_news_image = (ImageView) view.findViewById(R.id.item_news_image);
            item_news_title = (TextView) view.findViewById(R.id.item_news_title);
            item_news_type = (TextView) view.findViewById(R.id.item_news_type);
            item_news_time = (TextView) view.findViewById(R.id.item_news_time);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("News",(Serializable) news);
                    context.startActivity(intent);
                }
            });
        }

        public void setNews(News news) {
            this.news = news;
            item_news_title.setText(news.getTitle());
            item_news_type.setText(news.getCatname());
            item_news_time.setText(news.getCreate_time());
            Glide.with(context).load(news.getImage()).into(item_news_image);

        }

        public News getNewsDetails() {
            return news;
        }

    }
}

