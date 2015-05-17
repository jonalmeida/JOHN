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
    private LinkedList<StoryItem> mItemsList;
    private LayoutInflater mInflater;

    public NewsItemsRecyclerViewAdapter(List<StoryItem> storyItemList, LayoutInflater inflater) {
        mItemsList = (LinkedList<StoryItem>) storyItemList;
        mInflater = inflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(mItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public void addItemAtEnd(StoryItem storyItem) {
        addItem(storyItem, mItemsList.size());
    }

    public void addItem(StoryItem storyItem, int position) {
        mItemsList.push(storyItem);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mItemsList.size());
    }

    public StoryItem removeItem(int position) {
        StoryItem storyItem = mItemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItemsList.size());
        return storyItem;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "Adapter.ViewHolder";

        public StoryItem mStoryItem;

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

        public void bindItem(StoryItem storyItem) {
            mStoryItem = storyItem;
            title.setText(storyItem.title);
            url.setText(storyItem.url);
            upvoteCount.setText(Integer.toString(storyItem.upvoteCount));
            author.setText(storyItem.author);
            commentCount.setText(Integer.toString(storyItem.commentCount));
        }

        @Override
        public void onClick(View view) {
            if (mStoryItem != null) {
                Log.d(TAG, "Click all you want, shit ain't gonna work!");
            }
        }
    }
}
