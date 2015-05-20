package com.jonalmeida.john;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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

    private List<Item> queryItemIds(String relativeUrlPath, int id) {
        return null;
    }

    public void queryUpdateProperties(final Item item, final Update returnList) {
        Firebase firebaseRef = new Firebase(itemUrl + item.id);
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d(TAG, "dataSnapshot: " + dataSnapshot);
                final StoryItem returnItem = dataSnapshot.getValue(StoryItem.class);
                //Log.d(TAG, "returnItem: " + returnItem);
                returnList.update(returnItem);
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
    }

}
