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

    private static ItemUpdateHelper instance = new ItemUpdateHelper();
    private Context context;

    public static ItemUpdateHelper getInstance() { return instance; }

    public void init(Context context) {
        Firebase.setAndroidContext(context);
        this.context = context;
    }

    public List<StoryItem> getTopStories() {
        return null;
    }

    public <T>void queryItemIds(String relativeUrlPath, final Update<Item> returnUpdateItem,
                             final Class<T> valueType) {
        Firebase firebaseRef = new Firebase(baseUrl + relativeUrlPath);
        Log.d(TAG, "Creating a firebaseRef at: " + firebaseRef);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int pos = 0;
                LinkedList<Item> list = new LinkedList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // TODO: Move '*' lines to `getTopStories`
                    if (pos >= 30) break; // *
                    final T i = ds.getValue(valueType);
                    queryUpdateProperties(((Item)i).id, returnUpdateItem); // *
                    list.push((Item) i);
                    pos++; // *
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // How the hell do I handle this?
            }
        });
    }

    public void queryUpdateProperties(final int itemId, final Update<Item> returnUpdateItem) {
        Firebase firebaseRef = new Firebase(itemUrl + itemId);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d(TAG, "dataSnapshot: " + dataSnapshot);
                final StoryItem returnItem = dataSnapshot.getValue(StoryItem.class);
                //Log.d(TAG, "returnItem: " + returnItem);
                if (returnUpdateItem != null) {
                    returnUpdateItem.update(returnItem);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // How the hell do I handle this?
            }
        });
    }

    private ItemUpdateHelper() {}

    public interface Update<Item> {
        void update(Item item);

        void updateList(List<Item> itemList);
    }

}
