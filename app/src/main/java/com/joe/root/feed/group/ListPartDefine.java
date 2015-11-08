package com.joe.root.feed.group;

import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.partdefine.FeedGapSingleDefine;
import com.joe.root.feed.partdefine.ListChoiceSingleDefine;
import com.joe.root.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/10/9.
 */
public class ListPartDefine extends FeedGroupPartDefine {

    @Override
    public List<FeedPartDefine> getChildrenPartDefines(Card card) {
        List<FeedPartDefine> pollFeedPartDefines = new ArrayList<FeedPartDefine>();
        pollFeedPartDefines.add(new ListChoiceSingleDefine(getGroupPosition()));
        pollFeedPartDefines.add(new FeedGapSingleDefine());
        return pollFeedPartDefines;
    }

}
