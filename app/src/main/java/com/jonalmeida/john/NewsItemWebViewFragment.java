package com.jonalmeida.john;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jonalmeida.john.item.Item;
import com.jonalmeida.john.item.StoryItem;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsItemWebViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsItemWebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsItemWebViewFragment extends Fragment {

    private boolean mTwoPane = false;
    private Item mItem;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param twoPane If the app is in tablet or phone view.
     * @param item Item passed to WebView. Should be a StoryItem by default.
     * @return A new instance of fragment NewsItemWebViewFragment.
     */
    public static NewsItemWebViewFragment newInstance(Boolean twoPane, Item item) {
        NewsItemWebViewFragment fragment = new NewsItemWebViewFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_TWO_PANE_MODE, twoPane);
        args.putParcelable(Constants.ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsItemWebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTwoPane = getArguments().getBoolean(Constants.ARG_TWO_PANE_MODE);
            mItem = getArguments().getParcelable(Constants.ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web_view_item, container, false);

        ((TextView) rootView.findViewById(R.id.newsitem_detail)).setText(mItem.toString());

        WebView webView = (WebView) rootView.findViewById(R.id.web_view);

        configureWebView(webView, rootView);

        webView.loadUrl(((StoryItem) mItem).getUrl());
        //String data = "<div>\n  \n\n\n\n\n\n\n\n<div class=\"entry\">\n\t<p>When we launched Readability a few weeks ago, we left a whole host of ideas and features on the drawing board. The ideas kept on coming, but we also wanted to go live sooner than later. Now that Readability has launched, we&#x2019;re excited to pick up those ideas and run with them.</p>\n<p>But great ideas don&#x2019;t only come from us. Since we launched, we&#x2019;ve been flooded with great ideas from users via email, Twitter, and other channels. Every time a good idea landed in our laps, we&#x2019;d be excited and then anxious about losing it amidst all the noise&#x2026;until now.</p>\n<p>Introducing <a href=\"http://readability.bonfireapp.com\">Readability Ideas</a>. It&#x2019;s a place where the Readability community &#x2013; both readers and publishers &#x2013; and the Readability team share, talk about and bring ideas to reality. You can log in with your Facebook or Readability account right into Readability Ideas.</p>\n<p>We couldn&#x2019;t be more excited about where we take Readability from here. We look forward to hearing your feedback on how we can make Readability the best reading platform on the web.</p>\n    \n\t\n\n\n \n\n </div>\n</div>";
        //webView.loadData(data, "text/html; charset=UTF-8", null);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void configureWebView(WebView webView, View rootView) {
        final ProgressBar progress = (ProgressBar) rootView.findViewById(R.id.web_view_progress_bar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
            }
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
