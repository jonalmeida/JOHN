package com.jonalmeida.john;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.LinkedList;

/**
 * A list fragment representing a list of NewsItems. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link NewsItemDetailFragment}.
 * <p/>
 */
public class NewsItemsRecyclerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsItemsRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private LinkedList<NewsItem> items;
    private Firebase fb;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsItemsRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity().getApplicationContext());
        fb = new Firebase("https://hacker-news.firebaseio.com/v0");
        items = new LinkedList<>();
        items.push(new NewsItem("Foo bar story title", "http://example.com", 9, "jonalmeida", 0));
        items.push(new NewsItem("Foo bar story title", "http://example.com", 9, "jonalmeida", 0));
        populateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list_layout, container, false);
        init(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new NewsItemsRecyclerViewAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addItem(new NewsItem("Foo bar story title", "http://notexample.com", 9, "jonalmeida", 0));
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Do Firebase setting here
    }

    private void populateList() {
        fb.child("topstories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("RecycleFrag", "Full spitting: " + dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Log.d("RecycleFrag", ds.toString());
                    Log.d("RecyclerFlag", "Value trying to add: " + ds.getValue().toString());
                    insertNewsItem(ds);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void insertNewsItem(DataSnapshot ds) {
        items.push(new NewsItem(ds.getValue().toString(), "http://example.com", 0, "jonalmeida", 1));
    }

}
