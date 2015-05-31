package com.jonalmeida.john;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

    private boolean mTwoPane = false;
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

        if (getArguments().containsKey(Constants.ARG_TWO_PANE_MODE)) {
            mTwoPane = getArguments().getBoolean(Constants.ARG_TWO_PANE_MODE);
        }

        if (getArguments().containsKey(Constants.ARG_ITEM)) {
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
            ((TextView) rootView.findViewById(R.id.newsitem_detail)).setText(mItem.toString());

            WebView webView = (WebView) rootView.findViewById(R.id.mywebview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(((StoryItem) mItem).getUrl());
//            String data = "<div>\n  \n\n\n\n\n\n\n\n<div class=\"entry\">\n\t<p>When we launched Readability a few weeks ago, we left a whole host of ideas and features on the drawing board. The ideas kept on coming, but we also wanted to go live sooner than later. Now that Readability has launched, we&#x2019;re excited to pick up those ideas and run with them.</p>\n<p>But great ideas don&#x2019;t only come from us. Since we launched, we&#x2019;ve been flooded with great ideas from users via email, Twitter, and other channels. Every time a good idea landed in our laps, we&#x2019;d be excited and then anxious about losing it amidst all the noise&#x2026;until now.</p>\n<p>Introducing <a href=\"http://readability.bonfireapp.com\">Readability Ideas</a>. It&#x2019;s a place where the Readability community &#x2013; both readers and publishers &#x2013; and the Readability team share, talk about and bring ideas to reality. You can log in with your Facebook or Readability account right into Readability Ideas.</p>\n<p>We couldn&#x2019;t be more excited about where we take Readability from here. We look forward to hearing your feedback on how we can make Readability the best reading platform on the web.</p>\n    \n\t\n\n\n \n\n </div>\n</div>";
//            webView.loadData(data, "text/html; charset=UTF-8", null);
        }

        return rootView;
    }
}
