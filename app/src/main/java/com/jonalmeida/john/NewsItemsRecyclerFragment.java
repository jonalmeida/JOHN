package com.jonalmeida.john;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonalmeida.john.item.Item;

import java.util.LinkedList;

/**
 * A list fragment representing a list of NewsItems. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link NewsItemDetailFragment}.
 * <p/>
 */
public class NewsItemsRecyclerFragment extends Fragment
        implements NewsItemsRecyclerAdapter.OnListItemClickListener {

    private static final String TAG = "ItemsRecyclerFragment";

    private RecyclerView mRecyclerView;
    private NewsItemsRecyclerAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LayoutInflater mLayoutInflater;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinkedList<Item> items;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane = false;
    private RecyclerView itemDecoration;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsItemsRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new LinkedList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list_layout, container, false);

        mLayoutInflater = inflater;
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.fragment_main_swipe_refresh_layout);
        mAdapter = new NewsItemsRecyclerAdapter(items, inflater);

        insertTopStories();
        init(v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
        setSwipeRefresher();
    }

    private void refreshContent() {
        // Shit, is this the best way to do this?!
        final NewsItemsRecyclerFragment newsItemsRecyclerFragment = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                items.clear();
                mAdapter = new NewsItemsRecyclerAdapter(items, mLayoutInflater);
                mAdapter.setOnClickListener(newsItemsRecyclerFragment);
                mRecyclerView.setAdapter(mAdapter);
                insertTopStories();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        items.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void init(View v) {
        mTwoPane = getResources().getBoolean(R.bool.twoPane);
        mAdapter.setOnClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_story_list);

        setItemDecoration(mRecyclerView);

        setLayoutManager(mRecyclerView);

        setScrollListener(mRecyclerView);

    }

    @Override
    public void onStoryClicked(Item i) {
        Log.d(TAG, "We're getting the item here: " + i.getId());
        if (mTwoPane) {
            NewsItemDetailFragment fragment = NewsItemDetailFragment.newInstance(mTwoPane, i);
            this.getFragmentManager().beginTransaction()
                    .replace(R.id.newsitem_detail_container, fragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(getActivity(), NewsItemDetailActivity.class);
            detailIntent.putExtra(Constants.ARG_ITEM, i);
            detailIntent.putExtra(Constants.ARG_TWO_PANE_MODE, mTwoPane);
            startActivity(detailIntent);
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    @Override
    public void onCommentClicked(Item i) {
        Log.d(TAG, "We're getting an item's comments here: " + i.getId());
        if (mTwoPane) {
            CommentItemsFragment fragment = CommentItemsFragment.newInstance(mTwoPane, i);
            this.getFragmentManager().beginTransaction()
                    .replace(R.id.newsitem_detail_container, fragment)
                    .commit();
        } else {
            Intent commentIntent = new Intent(getActivity(), CommentItemsActivity.class);
            commentIntent.putExtra(Constants.ARG_ITEM, i);
            commentIntent.putExtra(Constants.ARG_TWO_PANE_MODE, mTwoPane);
            startActivity(commentIntent);
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    private void insertTopStories() {
        if (mAdapter.getItemCount() <= 0)
            Log.d(TAG, "No items in top stories adapter, getting updated ones.");
            ItemUpdateHelper.getInstance().getTopStories(mAdapter, 0, 30);
    }

    private void setSwipeRefresher() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary);
    }

    private void setScrollListener(RecyclerView view) {
        view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int currentFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {
                    ((NewsItemsRecyclerActivity) getActivity()).getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    ((NewsItemsRecyclerActivity) getActivity()).getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });
    }

    private void setLayoutManager(RecyclerView view) {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        view.setLayoutManager(mLinearLayoutManager);
    }

    public void setItemDecoration(RecyclerView view) {
        view.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

    }
}
