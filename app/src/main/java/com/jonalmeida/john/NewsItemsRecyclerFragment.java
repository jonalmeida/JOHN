package com.jonalmeida.john;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonalmeida.john.item.Item;

import java.net.ContentHandler;
import java.util.LinkedList;

/**
 * A list fragment representing a list of NewsItems. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link NewsItemDetailFragment}.
 * <p/>
 */
public class NewsItemsRecyclerFragment extends Fragment
        implements NewsItemsRecyclerViewAdapter.OnListItemClickListener {

    private static final String TAG = "ItemsRecyclerFragment";

    private RecyclerView mRecyclerView;
    private NewsItemsRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private LinkedList<Item> items;
    private boolean mTwoPane;

    @Override
    public void onClick(Item i) {
        Log.d(TAG, "We're getting the item here: " + i.getId());
        if (mTwoPane) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.ARG_ITEM, i);
            NewsItemDetailFragment fragment = new NewsItemDetailFragment();
            fragment.setArguments(bundle);
            this.getFragmentManager().beginTransaction()
                    .replace(R.id.newsitem_detail_container, fragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(getActivity(), NewsItemDetailActivity.class);
            detailIntent.putExtra(Constants.ARG_ITEM, i);
            startActivity(detailIntent);
        }
    }


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
        if (getActivity().findViewById(R.id.newsitem_detail_container) != null) {
            mTwoPane = true;
        }
        if (savedInstanceState != null) {
            savedInstanceState.putBoolean(Constants.ARG_TWO_PANE_MODE, mTwoPane);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list_layout, container, false);
        mAdapter = new NewsItemsRecyclerViewAdapter(items, inflater);
        mAdapter.mOnClickListener = this;
        init(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
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
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        insertTopStories();
    }

    private void insertTopStories() {
        ItemUpdateHelper.getInstance().getTopStories(mAdapter, 0, 30);
    }
}
