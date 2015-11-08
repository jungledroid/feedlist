package com.example.root.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joe.root.feed.FeedAdapter;
import com.joe.root.feed.FeedHelper;
import com.joe.root.listener.Callback;

import org.json.JSONArray;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedFragment extends Fragment {
    private FeedAdapter mFeedAdapter;

    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        ListView lvFeed = (ListView) rootView.findViewById(R.id.lv_feeds);
        mFeedAdapter = new FeedAdapter();
        lvFeed.setAdapter(mFeedAdapter);
        FeedHelper.getInstance().refreshFeeds(mFeedCallback);
        return rootView;
    }

    private Callback<JSONArray> mFeedCallback = new Callback<JSONArray>() {
        @Override
        public void success(JSONArray jsonArray) {
            FeedHelper.getInstance().parseFeedBinders(jsonArray,mFeedAdapter);
        }

        @Override
        public void failure() {

        }
    };
}
