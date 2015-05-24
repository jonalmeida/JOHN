package com.jonalmeida.john;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class ItemUpdateHelper {

    private static final String TAG = "ItemUpdateHelper";
    private static final String baseUrl = "https://hacker-news.firebaseio.com/v0/";
    private static final String itemUrl = baseUrl + "item/";

    private static final String TOP_STORIES = "topstories";

    private static ItemUpdateHelper instance = new ItemUpdateHelper();
    private Context context;

    public static ItemUpdateHelper getInstance() { return instance; }

    public void init(Context context) {
        Firebase.setAndroidContext(context);
        this.context = context;
    }

    public void getTopStories(final UpdateListener<Item> returnUpdatedItem,
                              final int beginPos, final int endPos) {

        queryItemIds(TOP_STORIES, new UpdateListener<Item>() {
            @Override
            public void update(Item item) {
                throw new UnsupportedOperationException("Not implemented. \n" +
                        "Intentional, this method shouldn't need to be called.");
            }

            @Override
            public void updateList(List<Item> items) {
                int iterPos = items.size() - beginPos - 1;
                final int iterEnd = items.size() - endPos - 1;
                Log.d(TAG, "items.size() -> " + items.size());
                Log.d(TAG, "iterPos      -> " + iterPos);
                Log.d(TAG, "iterEnd      -> " + iterEnd);
                while (iterPos != iterEnd) {
                    queryUpdateProperties(items.get(iterPos).id, returnUpdatedItem);
                    iterPos--;
                }
            }

        }, StoryItem.class);
    }

    public <T>void queryItemIds(String relativeUrlPath, final UpdateListener<Item> returnUpdatedItem,
                             final Class<T> valueType) {
        Firebase firebaseRef = new Firebase(baseUrl + relativeUrlPath);
        Log.d(TAG, "Creating a firebaseRef at: " + firebaseRef);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int pos = 0;
                LinkedList<Item> list = new LinkedList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    final T i = ds.getValue(valueType);
                    list.push((Item) i);
                }
                returnUpdatedItem.updateList(list);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // How the hell do I handle this?
                Log.e(TAG, "queryItemIds failed: " + firebaseError);
            }
        });
    }

    public void queryUpdateProperties(final int itemId, final UpdateListener<Item> returnUpdatedItem) {
        Firebase firebaseRef = new Firebase(itemUrl + itemId);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d(TAG, "dataSnapshot: " + dataSnapshot);
                final StoryItem returnItem = dataSnapshot.getValue(StoryItem.class);
                //Log.d(TAG, "returnItem: " + returnItem);
                if (returnUpdatedItem != null) {
                    returnUpdatedItem.update(returnItem);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // How the hell do I handle this?
                Log.e(TAG, "queryUpdateProperties failed for id: " + itemId + ": " + firebaseError);
            }
        });
    }

    private ItemUpdateHelper() {}

    public interface UpdateListener<Item> {
        void update(Item item);

        void updateList(List<Item> itemList);
    }

}
