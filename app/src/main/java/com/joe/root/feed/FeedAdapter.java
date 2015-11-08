package com.joe.root.feed;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoeZ on 2015/10/9.
 */
public class FeedAdapter extends BaseAdapter implements FeedUpdateListener {
    private List<FeedBinder> mFeedBinders = new ArrayList<FeedBinder>();

    public FeedAdapter() {
    }

    @Override
    public int getCount() {
        return mFeedBinders.size();
    }

    @Override
    public Object getItem(int position) {
        return mFeedBinders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return mFeedBinders.get(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mFeedBinders.get(position).bind(convertView, parent);
    }

    @Override
    public void expandBinder(FeedBinder choiceBinder, FeedBinder resultBinder) {
        mFeedBinders.add(mFeedBinders.indexOf(choiceBinder) + 1, resultBinder);
        notifyDataSetChanged();
    }

    @Override
    public void collapseBinder(FeedBinder resultBinder) {
        int removeIndex = mFeedBinders.indexOf(resultBinder);
        if(removeIndex != -1) {
            mFeedBinders.remove(removeIndex);
            mFeedBinders.get(removeIndex - 1).onRelativeBinderCollapse();
            notifyDataSetChanged();
        }
    }


    @Override
    public void onBinderCompile(FeedBinder binder) {
        mFeedBinders.add(binder);
    }

    @Override
    public void onBindersCompileEnd() {
        for (int position = 0; position < mFeedBinders.size(); position++) {
            FeedBinder feedBinder = mFeedBinders.get(position);
            feedBinder.prepare();
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindersCompileStart() {
        mFeedBinders.clear();
    }

}
