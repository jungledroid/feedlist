package com.joe.root.feed.group;


import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.model.Card;

import java.util.List;

/**
 * Created by dell on 2015/10/9.
 */
public abstract class FeedGroupPartDefine implements FeedPartDefine {
    private int mGroupPosition;
    public abstract List<FeedPartDefine> getChildrenPartDefines(Card card);
    public void compileBinders(Card card,FeedUpdateListener feedUpdateListener){
        FeedBinder tmpFeedBinder;
        for(FeedPartDefine feedPartDefine:getChildrenPartDefines(card)){
            tmpFeedBinder = feedPartDefine.createBinder(card,feedUpdateListener);
            if(tmpFeedBinder == null){
                ((FeedGroupPartDefine)feedPartDefine).compileBinders(card,feedUpdateListener);
            }else if(feedUpdateListener != null){
                feedUpdateListener.onBinderCompile(tmpFeedBinder);
            }
        }
    }

    public int getGroupPosition() {
        return mGroupPosition;
    }

    public void setGroupPosition(int mGroupPosition) {
        this.mGroupPosition = mGroupPosition;
    }

    @Override
    public FeedBinder createBinder(Card card,FeedUpdateListener feedUpdateListener) {
        return null;
    }
}
