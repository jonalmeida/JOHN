package com.jonalmeida.john;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NewsItemsRecyclerViewAdapter
        extends RecyclerView.Adapter<NewsItemsRecyclerViewAdapter.ViewHolder>
        implements ItemUpdateHelper.Update<StoryItem> {
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
        mItemsList.add(position, storyItem);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mItemsList.size());
    }

    public StoryItem removeItem(int position) {
        StoryItem storyItem = mItemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItemsList.size());
        return storyItem;
    }

    @Override
    public void update(StoryItem item) {
        addItemAtEnd(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "Adapter.ViewHolder";
        private PrettyTime prettyTime = new PrettyTime();

        public StoryItem mStoryItem;

        protected TextView title;
        protected TextView url;
        protected TextView score;
        protected TextView author;
        protected TextView descendants;
        protected TextView time;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.newsitem_title);
            url = (TextView) v.findViewById(R.id.newsitem_url);
            score = (TextView) v.findViewById(R.id.newsitem_score);
            author = (TextView) v.findViewById(R.id.newsitem_author);
            descendants = (TextView) v.findViewById(R.id.newsitem_descendants);
            time = (TextView) v.findViewById(R.id.newsitem_time);
            v.setOnClickListener(this);
        }

        public void bindItem(StoryItem storyItem) {
            mStoryItem = storyItem;
            title.setText(storyItem.title);
            url.setText(storyItem.url);
            score.setText(Integer.toString(storyItem.score));
            author.setText(storyItem.by);
            descendants.setText(Integer.toString(storyItem.descendants));
            time.setText(prettyTime.format(new Date(storyItem.time * 1000L)));
        }

        @Override
        public void onClick(View view) {
            if (mStoryItem != null) {
                Log.d(TAG, "Click all you want, shit ain't gonna work!");
            }
        }
    }
}
