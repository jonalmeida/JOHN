package com.jonalmeida.john;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class NewsItemsRecyclerFragment extends Fragment {

    private static final String TAG = "ItemsRecyclerFragment";

    private RecyclerView mRecyclerView;
    private NewsItemsRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private LinkedList<Item> items;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list_layout, container, false);
        mAdapter = new NewsItemsRecyclerViewAdapter(items, inflater);
        init(v, inflater);
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

    private void init(View v, LayoutInflater inflater) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        // Do Firebase setting here
        insertTopStories();
    }

    private void insertTopStories() {
        ItemUpdateHelper.getInstance().getTopStories(mAdapter, 0, 30);
    }
}
