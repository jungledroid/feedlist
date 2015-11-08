package com.joe.root.feed.group;


import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.group.FeedGroupPartDefine;
import com.joe.root.feed.partdefine.ArticleImageSingleDefine;
import com.joe.root.feed.partdefine.FeedGapSingleDefine;
import com.joe.root.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoeZ on 2015/10/26.
 */
public class ArticlePartDefine extends FeedGroupPartDefine {
    public static final String TEST_JSON_UMC="{\"title\":\"下面一篇文章\",\"content\":{\"body\":\"<p>欲做精金美玉的人品，定从烈火中煅来；思立掀天揭地的事功，须向薄冰上履过。</p>\\n\\n<p>一念错，便觉百行皆非，防之当如渡海浮囊，勿容一针之罅漏；万善全，始得一生无愧。修之当如凌云宝树，须假众木以撑持.</p>\\n\\n\"},\"subtitle\":\"子标题\",\"type\":\"article\",\"author\":\"徐悲鸿\"}";
    @Override
    public List<FeedPartDefine> getChildrenPartDefines(Card card) {
        List<FeedPartDefine> umcPartList = new ArrayList<FeedPartDefine>();
        umcPartList.add(new ArticleImageSingleDefine());String s = "<a href=\"http://www.baidu.com\" >百度</a>";
        umcPartList.add(new FeedGapSingleDefine());
        return umcPartList;
    }
}
