package com.jonalmeida.john;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.jonalmeida.john.item.Item;
import com.jonalmeida.john.item.StoryItem;

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

    /**
     * Returns a list of the top stories to {@code returnUpdateItem} as part of a callback
     * to {@code updateList(List<Item>)}. The list contains items from the list
     * within the range from {@code beginPos} to {@code endPos}.
     * @param returnUpdatedItem Callback to have a {@link List} of {@link Item} sent to be handled.
     * @param beginPos Position from where to start in a list of size 500.
     * @param endPos Position from where to end in a list of size 500.
     */
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
                    queryUpdateProperties(items.get(iterPos).getId(), returnUpdatedItem);
                    iterPos--;
                }
            }

        }, StoryItem.class);
    }

    /**
     * Returns an updated {@link Item} to {@code relativeUrlPath} as part of a callback to
     * {@code updateList(List<Item>)}.
     * @param relativeUrlPath The path string to be appended to the end of the the base Firebase URL.
     * @param returnUpdatedItem Callback to have a {@link List} of {@link Item} sent to be handled.
     * @param valueType The type inheriting from {@link Item} to cast and return an object to
     *                  (with help from Firebase's usage of Jackson).
     * @param <T> The type inheriting from {@link Item}.
     */
    public <T>void queryItemIds(String relativeUrlPath,
                                final UpdateListener<Item> returnUpdatedItem,
                                final Class<T> valueType) {

        Firebase firebaseRef = new Firebase(baseUrl + relativeUrlPath);
        Log.d(TAG, "Creating a firebaseRef at: " + firebaseRef);

        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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

    /**
     * Returns an {@link Item} to {@code returnUpdateItem} as part of a callback to
     * {@code update(Item)}. A Firebase query is made to {@code /v0/item/[ITEM_ID]}.
     * @param itemId ID of the item to query.
     * @param returnUpdatedItem Callback to have a {@link List} of {@link Item} sent to be handled.
     */
    public void queryUpdateProperties(final int itemId,
                                      final UpdateListener<Item> returnUpdatedItem) {

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

    /**
     * A callback interface used to inform listeners when an updated item or list of items is
     * received.
     * @param <Item> Item base type. This should usually be the inherited type that you
     *              are expecting to see back. Will crash and burn if you get this wrong.
     */
    public interface UpdateListener<Item> {
        void update(Item item);

        void updateList(List<Item> itemList);
    }

}
