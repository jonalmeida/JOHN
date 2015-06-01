package com.jonalmeida.john;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonalmeida.john.item.Item;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentItemsFragment extends Fragment {

    private Boolean mTwoPane;
    private Item mItem;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param twoPane If the app is in tablet or phone view.
     * @param item Item passed to WebView. Should be a StoryItem by default.
     * @return A new instance of fragment CommentsFragment.
     */
    public static CommentItemsFragment newInstance(boolean twoPane, Item item) {
        CommentItemsFragment fragment = new CommentItemsFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_TWO_PANE_MODE, twoPane);
        args.putParcelable(Constants.ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public CommentItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTwoPane = getArguments().getBoolean(Constants.ARG_TWO_PANE_MODE);
            mItem = getArguments().getParcelable(Constants.ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_items, container, false);
    }
}
