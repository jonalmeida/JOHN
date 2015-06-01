package com.jonalmeida.john;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonalmeida.john.item.Item;
import com.jonalmeida.john.item.ItemType;
import com.jonalmeida.john.item.StoryItem;


/**
 * A fragment representing a single StoryItem detail screen.
 * This fragment is either contained in a {@link NewsItemsRecyclerActivity}
 * in two-pane mode (on tablets) or a {@link NewsItemDetailActivity}
 * on handsets.
 */
public class NewsItemDetailFragment extends Fragment {

    private static final String TAG = "NewsItemDetailFragment";

    private boolean mTwoPane = false;
    private Item mItem;
    private ItemType mItemType = ItemType.Unknown;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param twoPane If the app is in tablet or phone view.
     * @param item Item passed to WebView. Should be a StoryItem by default.
     * @return A new instance of fragment WebViewFragment.
     */
    public static NewsItemDetailFragment newInstance(Boolean twoPane, Item item) {
        NewsItemDetailFragment fragment = new NewsItemDetailFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_TWO_PANE_MODE, twoPane);
        args.putParcelable(Constants.ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mTwoPane = getArguments().getBoolean(Constants.ARG_TWO_PANE_MODE);

            mItem = getArguments().getParcelable(Constants.ARG_ITEM);

            if (mItem.getType().equals(ItemType.Story.toString())) {
                Log.d(TAG, "Pete, we've got a story on our hands!");

                if (!mTwoPane)
                    getActivity().setTitle(((StoryItem) mItem).getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_detail, container, false);

        if (mItem != null) {

            WebViewFragment fragment = WebViewFragment.newInstance(mTwoPane, mItem);
            getFragmentManager().beginTransaction()
                    .replace(R.id.newsitem_detail_container, fragment)
                    .commit();
        }

        return rootView;
    }

}
