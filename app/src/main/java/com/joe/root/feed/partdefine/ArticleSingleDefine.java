package com.joe.root.feed.partdefine;

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.root.ui.R;
import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.feed.HtmlTagHandler;
import com.joe.root.model.ArticleCard;
import com.joe.root.model.Card;

/**
 * Created by JoeZ on 2015/10/26.
 */
public class ArticleSingleDefine implements FeedPartDefine {

    @Override
    public FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener) {
        return new UmcArticleBinder(card,feedUpdateListener);
    }

    private static final class UmcArticleBinder implements FeedBinder{

        private ArticleCard mUmcArticle;
        private Spanned mArticleBody;
        private ViewHolder mViewHolder;
        private FeedUpdateListener mFeedUpdateListener;

        public UmcArticleBinder(Card card, FeedUpdateListener feedUpdateListener){
            mUmcArticle = (ArticleCard) card;
            mFeedUpdateListener = feedUpdateListener;
        }

        @Override
        public void prepare() {
            mArticleBody = getSpanned(mUmcArticle.getBody());
        }

        private Spanned getSpanned(String raw) {
            return raw == null ? null : Html.fromHtml(raw, null,
                    new HtmlTagHandler());
        }

        @Override
        public View bind(View convertView, ViewGroup parent) {
            int key = getViewResourceId();
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(key,parent,false);
                mViewHolder = new ViewHolder();
                mViewHolder.mTvTitle = (TextView)convertView.findViewById(R.id.tv_umc_title);
                mViewHolder.mTvSubTitle = (TextView)convertView.findViewById(R.id.tv_umc_subtitle);
                mViewHolder.mTvBody = (TextView)convertView.findViewById(R.id.tv_umc_article);
                mViewHolder.mTvPhotoCredit = (TextView)convertView.findViewById(R.id.tv_umc_photo_credit);
                mViewHolder.mTvClose = (TextView)convertView.findViewById(R.id.feed_result_close);
                convertView.setTag(key,mViewHolder);
            }else{
                mViewHolder = (ViewHolder) convertView.getTag(key);
            }

            mViewHolder.mTvTitle.setText(mUmcArticle.getTitle());
            mViewHolder.mTvSubTitle.setText(mUmcArticle.getSubtitle());
            if(mArticleBody != null){
                mViewHolder.mTvBody.setText(mArticleBody);
                mViewHolder.mTvBody.setMovementMethod(LinkMovementMethod.getInstance());
            }
            mViewHolder.mTvPhotoCredit.setText(mUmcArticle.getAuthor());
            mViewHolder.mTvTitle.setOnClickListener(mOnArticleCloseListener);
            mViewHolder.mTvClose.setOnClickListener(mOnArticleCloseListener);
            return convertView;
        }

        private View.OnClickListener mOnArticleCloseListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFeedUpdateListener.collapseBinder(UmcArticleBinder.this);
            }

        };

        @Override
        public void unbind() {

        }

        @Override
        public int getViewResourceId() {
            return R.layout.feed_item_article;
        }

        @Override
        public int getViewType() {
            return 5;
        }

        @Override
        public void onRelativeBinderCollapse() {

        }

    }


    private static final class ViewHolder{
        private TextView mTvTitle;
        private TextView mTvSubTitle;
        private TextView mTvBody;
        private TextView mTvPhotoCredit;
        private TextView mTvClose;
    }
}
