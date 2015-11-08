package com.joe.root.feed.partdefine;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.root.ui.R;
import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.model.Card;
import com.joe.root.model.ListCard;
import com.joe.root.widget.PercentListLayout;
import java.util.List;

/**
 * Created by dell on 2015/10/16.
 */
public class ListResultSingleDefine implements FeedPartDefine {
    @Override
    public FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener) {
        return new PollResultBinder((ListCard) card,feedUpdateListener);
    }

    private static final class PollResultBinder implements FeedBinder{
        private ListCard mChoiceListCard;
        private ViewHolder mViewHolder;
        private FeedUpdateListener mOnPollExpandListener;

        public PollResultBinder(ListCard choiceListCard, FeedUpdateListener feedUpdateListener){
            mChoiceListCard = choiceListCard;
            mOnPollExpandListener = feedUpdateListener;
        }
        @Override
        public void prepare() {

        }

        @Override
        public View bind(View convertView, ViewGroup parent) {
            int key = getViewResourceId();
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(key,parent,false);
                mViewHolder = new ViewHolder();
                mViewHolder.mPercentListLayout = (PercentListLayout) convertView.findViewById(R.id.tv_feed_poll_result);
                mViewHolder.mTvDescription = (TextView) convertView.findViewById(R.id.tv_feed_poll_body);
                mViewHolder.mResultContainer = (ViewGroup) convertView.findViewById(R.id.rl_poll_result_container);
                convertView.setTag(key,mViewHolder);
            }else{
                mViewHolder = (ViewHolder) convertView.getTag(key);
                unbind();
            }
            List<ListCard.Answer> answers = mChoiceListCard.getAnswers();
            for (ListCard.Answer answer : answers) {
                mViewHolder.mPercentListLayout.addPercent(answer.getPercentage_selected(),answer.getTitle(), answer.isChecked());
            }
            if(TextUtils.isEmpty(mChoiceListCard.getEditorialResponse())){
                mViewHolder.mTvDescription.setVisibility(View.GONE);
            }else{
                mViewHolder.mTvDescription.setVisibility(View.VISIBLE);
                mViewHolder.mTvDescription.setText(mChoiceListCard.getEditorialResponse());
            }
            mViewHolder.mResultContainer.setOnClickListener(mOnResultCloseListener);
            return convertView;
        }

        private View.OnClickListener mOnResultCloseListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnPollExpandListener.collapseBinder(PollResultBinder.this);
            }

        };

        @Override
        public void unbind() {
            mViewHolder.mPercentListLayout.clearPercents();
        }

        @Override
        public int getViewResourceId() {
            return R.layout.feed_item_voteresult;
        }

        @Override
        public int getViewType() {
            return 1;
        }

        @Override
        public void onRelativeBinderCollapse() {

        }

    }

    private static final class ViewHolder{
        private PercentListLayout mPercentListLayout;
        private TextView mTvDescription;
        private ViewGroup mResultContainer;
    }
}
