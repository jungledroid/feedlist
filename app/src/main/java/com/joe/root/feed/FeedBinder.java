package com.joe.root.feed;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 2015/10/9.
 */
public interface FeedBinder {
    void prepare();
    View bind(View convertView, ViewGroup parent);
    void unbind();
    int getViewResourceId();
    int getViewType();
    void onRelativeBinderCollapse();
}
