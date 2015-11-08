package com.joe.root.feed;


import com.joe.root.model.Card;

/**
 * Created by dell on 2015/10/9.
 */
public interface FeedPartDefine {
    FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener);
}
