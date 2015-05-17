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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
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

    private LinkedList<StoryItem> items;
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
        fb = new Firebase("https://hacker-news.firebaseio.com/v0");
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
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        // Do Firebase setting here
        insertTopStories();
    }

    private void insertTopStories() {
        final StoryItem[] frontPageIds = new StoryItem[30];
        Firebase fbItemRef;
        fb.child("topstories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int pos = 0;
                //Log.d(TAG, "Full spitting: " + dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Log.d(TAG, "Value trying to add: " + ds.getValue().toString());
                    if (pos >= 30) break;
                    frontPageIds[pos] = new StoryItem(ds.getValue().toString());
                    updateStoryItem(frontPageIds[pos]);
                    pos++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        for (int i=0; i < 30; i++) {
//            final StoryItem storyItem = frontPageIds[i];
//            fbItemRef = new Firebase("https://hacker-news.firebaseio.com/v0/item/" + storyItem.id);
//            final Firebase finalFbItemRef = fbItemRef;
//            fbItemRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    storyItem.title = dataSnapshot.child("title").getValue(String.class);
//                    storyItem.author = dataSnapshot.child("by").getValue(String.class);
//                    storyItem.url = dataSnapshot.child("url").getValue(String.class);
//                    mAdapter.addItemAtEnd(storyItem);
//                    finalFbItemRef.removeEventListener(this);
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });
//        }
    }

    private void updateStoryItem(final StoryItem storyItem) {
            final Firebase fbItemRef = new Firebase("https://hacker-news.firebaseio.com/v0/item/" + storyItem.id);
            fbItemRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    storyItem.title = dataSnapshot.child("title").getValue(String.class);
                    storyItem.author = dataSnapshot.child("by").getValue(String.class);
                    storyItem.url = dataSnapshot.child("url").getValue(String.class);
                    mAdapter.addItemAtEnd(storyItem);
                    fbItemRef.removeEventListener(this);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
    }
}
