package com.joe.root.feed.partdefine;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.root.ui.R;
import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.model.ArticleCard;
import com.joe.root.model.Card;

/**
 * Created by JoeZ on 2015/10/26.
 */
public class ArticleImageSingleDefine implements FeedPartDefine {
    private ArticleSingleDefine mUmcArticleSinglePartDefine = new ArticleSingleDefine();

    @Override
    public FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener) {
        return new ArticleImageBinder(card,mUmcArticleSinglePartDefine.createBinder(card,feedUpdateListener),feedUpdateListener);
    }


    private static final class ArticleImageBinder implements FeedBinder{
        private ArticleCard mUmcMediaCard;
        private ViewHolder mViewHolder;
        private boolean mIsExpand;
        private FeedUpdateListener mFeedUpdateListener;
        private FeedBinder mHideArticleBinder;

        public ArticleImageBinder(Card card, FeedBinder feedBinder, FeedUpdateListener feedUpdateListener){
            mUmcMediaCard = (ArticleCard) card;
            mFeedUpdateListener = feedUpdateListener;
            mHideArticleBinder = feedBinder;
        }

        @Override
        public void prepare() {
            mHideArticleBinder.prepare();
        }

        @Override
        public View bind(View convertView, ViewGroup parent) {
            int key = getViewResourceId();
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(key,parent,false);
                mViewHolder = new ViewHolder();
                mViewHolder.mIvUmcImage = (ImageView)convertView.findViewById(R.id.iv_umc_image);
                mViewHolder.mTvPhotoCredit = (TextView)convertView.findViewById(R.id.tv_umc_photo_credit);
                mViewHolder.mTvUmcTitle = (TextView)convertView.findViewById(R.id.tv_umc_title);
                convertView.setTag(key,mViewHolder);
            }else{
                mViewHolder = (ViewHolder)convertView.getTag(key);
            }

            mViewHolder.mIvUmcImage.setImageResource(R.color.vote_selected);
            if(!TextUtils.isEmpty(mUmcMediaCard.getAuthor())){
                mViewHolder.mTvPhotoCredit.setText(mUmcMediaCard.getAuthor());
            }

            mViewHolder.mTvUmcTitle.setVisibility(mIsExpand ? View.GONE : View.VISIBLE);
            mViewHolder.mTvUmcTitle.setText(mUmcMediaCard.getTitle());
            mViewHolder.mTvUmcTitle.setOnClickListener(mOnExpandUmcArticleListener);
            return convertView;
        }

        private View.OnClickListener mOnExpandUmcArticleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsExpand = true;
                mFeedUpdateListener.expandBinder(ArticleImageBinder.this, mHideArticleBinder);
            }

        };

        @Override
        public void unbind() {

        }

        @Override
        public int getViewResourceId() {
            return R.layout.feed_item_article_image;
        }

        @Override
        public int getViewType() {
            return 4;
        }

        @Override
        public void onRelativeBinderCollapse() {
            mIsExpand = false;
        }

    }

    private static class ViewHolder{
        private ImageView mIvUmcImage;
        private TextView mTvPhotoCredit;
        private TextView mTvUmcTitle;
    }
}
