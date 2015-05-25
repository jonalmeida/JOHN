package com.jonalmeida.john;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;


/**
 * An activity representing a list of NewsItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NewsItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link NewsItemsRecyclerFragment} and the item details
 * (if present) is a {@link NewsItemDetailFragment}.
 * <p/>
 */
public class NewsItemsRecyclerActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase.setAndroidContext(this);
        ItemUpdateHelper.getInstance().init(this);
        setContentView(R.layout.activity_newsitem_list);

        if (findViewById(R.id.newsitem_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            savedInstanceState.putBoolean(Constants.ARG_TWO_PANE_MODE, mTwoPane);

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            //((NewsItemsRecyclerFragment) getSupportFragmentManager()
            //        .findFragmentById(R.id.newsitem_list))
            //        .setActivateOnItemClick(true);
        }

        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }

//    @Override
//    public void onItemSelected(String id) {
//        if (mTwoPane) {
//            // In two-pane mode, show the detail view in this activity by
//            // adding or replacing the detail fragment using a
//            // fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(NewsItemDetailFragment.ARG_ITEM_ID, id);
//            NewsItemDetailFragment fragment = new NewsItemDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.newsitem_detail_container, fragment)
//                    .commit();
//
//        } else {
//            // In single-pane mode, simply start the detail activity
//            // for the selected item ID.
//            Intent detailIntent = new Intent(this, NewsItemDetailActivity.class);
//            detailIntent.putExtra(NewsItemDetailFragment.ARG_ITEM_ID, id);
//            startActivity(detailIntent);
//        }
//    }
}
