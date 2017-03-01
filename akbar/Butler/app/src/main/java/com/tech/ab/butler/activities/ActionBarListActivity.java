package com.tech.ab.butler.activities;

/**
 * Created by shreenath on 29/1/17.
 */

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public abstract class

ActionBarListActivity extends AppCompatActivity {

    private final class ListOnItemClickListener implements OnItemClickListener {

        public void onItemClick(AdapterView<?> lv, View v, int position, long id) {
            onListItemClick((ListView) lv, v, position, id);
            // String str = ((TextView) arg1).getText().toString();
            // Toast.makeText(getBaseContext(), str,
            // Toast.LENGTH_LONG).show();
            // Intent intent = new Intent(getBaseContext(),
            // your_new_Intent.class);
            // intent.putExtra("list_view_value", str);
            // startActivity(intent);
        }
    }


    private final class ListOnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            Toast.makeText(getBaseContext(), "Long Click",
                    Toast.LENGTH_LONG).show();
            onListItemLongClick((ListView) adapterView, view, position, id);
            return true;
        }
    }

    private ListView mListView;

    protected ListView getListView() {
        if (mListView == null) {
            initListView();
        }
        return mListView;
    }

    private void initListView() {
        mListView = (ListView) findViewById(getListViewId());
        if (mListView == null) {
            throw new RuntimeException(
                    "ListView cannot be null. Please set a valid ListViewId");
        }

        mListView.setOnItemClickListener(new ListOnItemClickListener());
        mListView.setOnItemLongClickListener(new ListOnItemLongClickListener());
    }

    protected abstract int getListViewId();

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected void onListItemClick(ListView lv, View v, int position, long id) {
        // No default functionality. To override
    }

    protected void onListItemLongClick(ListView lv, View v, int position, long id) {
        // No default functionality. To override
    }

    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }
}
