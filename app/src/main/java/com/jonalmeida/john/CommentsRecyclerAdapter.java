package com.jonalmeida.john;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonalmeida.john.item.CommentItem;
import com.jonalmeida.john.item.Item;

import java.util.LinkedList;
import java.util.List;

public class CommentsRecyclerAdapter
        extends RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder> {

    private static final String TAG = "CommentsRecyclerAdapter";

    private LinkedList<CommentItem> mItemsList;
    private LayoutInflater mInflater;

    public CommentsRecyclerAdapter(List<CommentItem> commentItemList) {
        mItemsList = (LinkedList<CommentItem>) commentItemList;
    }

    public CommentsRecyclerAdapter(List<CommentItem> commentItemList, LayoutInflater inflater) {
        mItemsList = (LinkedList<CommentItem>) commentItemList;
        mInflater = inflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (mInflater != null) {
            Log.d(TAG, "Reusing LayoutInflater given to us to create view.");
            v = mInflater.inflate(R.layout.comment_item, parent, false);
        } else {
            Log.d(TAG, "Didn't have a LayoutInflater; getting new one to create view.");
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment_item, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "Comments.ViewHolder";

        public CommentItem mCommentItem;

        public ViewHolder(View v) {
            super(v);
        }

        public void bindItem(Item commentItem) {
            mCommentItem = (CommentItem) commentItem;
        }
    }
}
