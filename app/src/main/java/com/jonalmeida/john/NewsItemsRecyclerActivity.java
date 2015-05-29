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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemUpdateHelper.getInstance().init(this);
        setContentView(R.layout.activity_newsitem_list);

        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);


        setTitle(R.string.app_full_name);

    }

}
