package com.jonalmeida.john;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private Item mItem;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.ARG_ITEM)) {
            mItem = getArguments().getParcelable(Constants.ARG_ITEM);

            if (mItem.getType().equals(ItemType.Story.toString())) {
                Log.d(TAG, "Pete, we've got a story on our hands!");

                getActivity().setTitle(((StoryItem) mItem).getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsitem_detail, container, false);

        // Show the dummy content as text in a TextView.
        //if (mItem != null) { }

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.newsitem_detail)).setText(Integer.toString(mItem.getId()));
        }

        return rootView;
    }
}
