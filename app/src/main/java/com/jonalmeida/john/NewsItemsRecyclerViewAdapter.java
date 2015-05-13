package com.jonalmeida.john;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class NewsItemsRecyclerViewAdapter extends RecyclerView.Adapter<NewsItemsRecyclerViewAdapter.ViewHolder> {
    private LinkedList<NewsItem> mItemsList;

    public NewsItemsRecyclerViewAdapter(List<NewsItem> newsItemList) {
        mItemsList = (LinkedList<NewsItem>) newsItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(mItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public void addItem(NewsItem newsItem) {
        mItemsList.push(newsItem);
        notifyItemInserted(mItemsList.size() - 1);
        notifyItemRangeChanged(mItemsList.size() - 1, mItemsList.size());
    }

    public NewsItem removeItem(int position) {
        NewsItem newsItem = mItemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItemsList.size());
        return newsItem;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "Adapter.ViewHolder";

        public NewsItem mNewsItem;

        protected TextView title;
        protected TextView url;
        protected TextView upvoteCount;
        protected TextView author;
        protected TextView commentCount;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.newsitem_title);
            url = (TextView) v.findViewById(R.id.newsitem_url);
            upvoteCount = (TextView) v.findViewById(R.id.newsitem_upvote_count);
            author = (TextView) v.findViewById(R.id.newsitem_author);
            commentCount = (TextView) v.findViewById(R.id.newsitem_comment_count);
            v.setOnClickListener(this);
        }

        public void bindItem(NewsItem newsItem) {
            mNewsItem = newsItem;
            title.setText(newsItem.title);
            url.setText(newsItem.url);
            upvoteCount.setText(newsItem.upvoteCount);
            author.setText(newsItem.author);
            commentCount.setText(newsItem.commentCount);
        }

        @Override
        public void onClick(View view) {
            if (mNewsItem != null) {
                Log.d(TAG, "Click all you want, shit ain't gonna work!");
            }
        }
    }
}
