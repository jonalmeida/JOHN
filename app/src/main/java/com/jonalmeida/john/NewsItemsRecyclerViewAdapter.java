package com.jonalmeida.john;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonalmeida.john.item.Item;
import com.jonalmeida.john.item.StoryItem;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NewsItemsRecyclerViewAdapter
        extends RecyclerView.Adapter<NewsItemsRecyclerViewAdapter.ViewHolder>
        implements ItemUpdateHelper.UpdateListener<Item> {

    private static final String TAG = "RecyclerViewAdapter";

    private LinkedList<Item> mItemsList;
    private LayoutInflater mInflater;

    private NewsItemsRecyclerFragment mOnClickListener;

    public interface OnListItemClickListener {
        /**
         * Called when a story item is clicked.
         * @param i The item index in the adapter.
         */
        void onStoryClicked(Item i);

        /**
         * Called when a story's comment is clicked.
         * @param i The item index in the adapter.
         */
        void onCommentClicked(Item i);
    }

    public NewsItemsRecyclerViewAdapter(List<Item> storyItemList, LayoutInflater inflater) {
        mItemsList = (LinkedList<Item>) storyItemList;
        mInflater = inflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_news_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bindItem(mItemsList.get(position));
        holder.itemView.findViewById(R.id.inner_item_layout)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onStoryClicked(mItemsList.get(position));
            }
        });
        holder.itemView.findViewById(R.id.descendant_layout)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "We looking for comments for: " + mItemsList.get(position).getId());
                mOnClickListener.onCommentClicked(mItemsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    public void addItemAtEnd(Item item) {
        addItem(item, mItemsList.size());
    }

    public void addItem(Item item, int position) {
        mItemsList.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mItemsList.size());
    }

    public StoryItem removeItem(int position) {
        StoryItem storyItem = (StoryItem) mItemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItemsList.size());
        return storyItem;
    }

    @Override
    public void update(Item item) {
        addItemAtEnd(item);
    }

    @Override
    public void updateList(List<Item> storyItems) {
        for (Item i : storyItems) {
            addItemAtEnd(i);
        }
    }

    public void setOnClickListener(NewsItemsRecyclerFragment mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        }

        public void bindItem(Item storyItem) {
            mStoryItem = (StoryItem) storyItem;
            title.setText(mStoryItem.getTitle());
            url.setText(mStoryItem.getUrl());
            score.setText(Integer.toString(mStoryItem.getScore()));
            author.setText(mStoryItem.getBy());
            descendants.setText(Integer.toString(mStoryItem.getDescendants()));
            time.setText(prettyTime.format(new Date(mStoryItem.getTime() * 1000L)));
        }

    }
}
