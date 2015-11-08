package com.joe.root.feed.partdefine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.ui.R;
import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.model.Card;

/**
 * Created by JoeZ on 2015/10/16.
 */
public class FeedGapSingleDefine implements FeedPartDefine {
    @Override
    public FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener) {
        return new FeedGapBinder();
    }

    private static final class FeedGapBinder implements FeedBinder{

        @Override
        public void prepare() {

        }

        @Override
        public View bind(View convertView, ViewGroup parent) {
            int key = getViewResourceId();
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(key,parent,false);
            }
            return convertView;
        }

        @Override
        public void unbind() {

        }

        @Override
        public int getViewResourceId() {
            return R.layout.feed_gap;
        }

        @Override
        public int getViewType() {
            return 3;
        }

        @Override
        public void onRelativeBinderCollapse() {

        }

    }

}
