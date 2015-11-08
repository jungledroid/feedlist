package com.joe.root.feed.partdefine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.root.ui.R;
import com.joe.root.feed.FeedBinder;
import com.joe.root.feed.FeedHelper;
import com.joe.root.feed.FeedPartDefine;
import com.joe.root.feed.FeedUpdateListener;
import com.joe.root.model.Card;
import com.joe.root.model.ListCard;
import com.joe.root.widget.ChoiceListLayout;


/**
 * Created by JoeZ on 2015/10/13.
 */
public class ListChoiceSingleDefine implements FeedPartDefine {
    public static final String TEST_JSON_OPTIONS_TEXT = "{\"type\":\"list\",\"title\":\"选项\",\"editorial_response\":\"这个描述有点长啊这个描述有点长.\",\"answers\":[{\"id\":1589,\"title\":\"1\",\"percentage_selected\":17},{\"id\":1590,\"title\":\"2\",\"percentage_selected\":42},{\"id\":1591,\"title\":\"3\",\"percentage_selected\":41}]}";
    private ListResultSingleDefine mPollResultSinglePartDeine = new ListResultSingleDefine();
    private int mGroupPosition;

    public ListChoiceSingleDefine(int groupPosition){
        mGroupPosition = groupPosition;
    }

    @Override
    public FeedBinder createBinder(Card card, FeedUpdateListener feedUpdateListener) {
        return new PollChoiceSingleBinder((ListCard) card, mPollResultSinglePartDeine.createBinder(card,feedUpdateListener),mGroupPosition,feedUpdateListener);
    }

    private static final class PollChoiceSingleBinder implements FeedBinder, ChoiceListLayout.OnChoiceChangedListener {
        private ListCard mChoiceListCard;
        private ViewHolder mViewHolder;
        private FeedUpdateListener mOnPollExpandListener;
        private boolean mIsExpand,mIsChecked;
        private String mTitlePrefix;
        private FeedBinder mResultBinder;
        private int mGroupPosition;

        public PollChoiceSingleBinder(ListCard choiceListCard, FeedBinder resultBinder, int groupPosition,FeedUpdateListener feedUpdateListener){
            mChoiceListCard = choiceListCard;
            mOnPollExpandListener = feedUpdateListener;
            mTitlePrefix = "列表: %s";
            mResultBinder = resultBinder;
            mGroupPosition = groupPosition;
        }

        @Override
        public void prepare() {
            mResultBinder.prepare();
        }

        @Override
        public View bind(View convertView, ViewGroup parent) {
            int key = getViewResourceId();
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(key,parent,false);
                mViewHolder = new ViewHolder();
                mViewHolder.mTvPollTitle = (TextView) convertView.findViewById(R.id.tv_feed_poll_title);
                mViewHolder.mChoiceListLayout = (ChoiceListLayout) convertView.findViewById(R.id.tv_feed_choice_list);
                convertView.setTag(key,mViewHolder);
            }else{
                mViewHolder = (ViewHolder) convertView.getTag(key);
                unbind();
            }
            mViewHolder.mTvPollTitle.setText(String.format(mTitlePrefix, mChoiceListCard.getTitle()));
            mViewHolder.mChoiceListLayout.addOnChoiceChangeListener(this);
            for (ListCard.Answer answer : mChoiceListCard.getAnswers()) {
                mViewHolder.mChoiceListLayout.addOption(answer.getTitle(), answer.isChecked());
                if(answer.isChecked()){
                    mIsChecked = true;
                }
            }
            return convertView;
        }

        @Override
        public void unbind() {
            mViewHolder.mChoiceListLayout.clearOptions();
        }

        @Override
        public int getViewResourceId() {
            return R.layout.feed_item_choice;
        }

        @Override
        public int getViewType() {
            return 2;
        }

        @Override
        public void onRelativeBinderCollapse() {

        }


        @Override
        public void onChoiceChange(int checkPosition) {
            mIsExpand = !mIsExpand;
            if(mIsExpand){
                if(!mIsChecked){
                    mIsChecked = true;
                    mChoiceListCard.getAnswers().get(checkPosition).setChecked(true);
                    FeedHelper.getInstance().notifyPollCheckChanged(mGroupPosition,(ListCard)mChoiceListCard);
                }
                mOnPollExpandListener.expandBinder(this, mResultBinder);
            }else{
                mOnPollExpandListener.collapseBinder(mResultBinder);
            }
        }

    }

    private static final class ViewHolder{
        private TextView mTvPollTitle;
        private ChoiceListLayout mChoiceListLayout;
    }


}
