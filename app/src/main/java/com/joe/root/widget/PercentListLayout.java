package com.joe.root.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.root.ui.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/10/14.
 */
public class PercentListLayout extends LinearLayout {
    private List<VoteResultItem> mVoteList = new ArrayList<VoteResultItem>();

    public PercentListLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
    }

    public PercentListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
    }

    public PercentListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(LinearLayout.VERTICAL);
    }

    private static final class VoteResultItem{
        ImageView ivVotePercent;
        TextView tvVoteName;
    }

    public void clearPercents(){
        removeAllViews();
    }

    public void addPercent(Long percentSelected, String title, boolean isChecked){
        VoteResultItem voteResultItem = new VoteResultItem();
        Context context = getContext();
        LinearLayout lLpercent = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.feed_item_percent, this, false);
        ImageView ivVote = (ImageView) lLpercent.findViewById(R.id.iv_vote);
        LayoutParams llVoteParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        llVoteParams.weight = 100 - percentSelected;
        ivVote.setLayoutParams(llVoteParams);

        ImageView ivRest = (ImageView) lLpercent.findViewById(R.id.iv_rest);
        LayoutParams llivRestParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        llVoteParams.weight = percentSelected;
        ivRest.setLayoutParams(llivRestParams);

        TextView tvVoteName = (TextView) lLpercent.findViewById(R.id.tv_feed_vote_name);
        tvVoteName.setText(String.format("%s%% %s", percentSelected, title));
        if(isChecked){
            RelativeLayout rlCheckedContainner = new RelativeLayout(context);
            rlCheckedContainner.addView(lLpercent);
            ImageView ivChecked = new ImageView(context);
            ivChecked.setBackgroundResource(R.drawable.tick_white_small);
            RelativeLayout.LayoutParams checkedParams = new RelativeLayout.LayoutParams(-2, -2);
            checkedParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            checkedParams.leftMargin = (int) getResources().getDimension(R.dimen.d_20);
//            checkedParams.topMargin = (int) getResources().getDimension(R.dimen.d_14);
            ivChecked.setLayoutParams(checkedParams);
            rlCheckedContainner.addView(ivChecked);
            this.addView(rlCheckedContainner);
        }else{
            this.addView(lLpercent);
        }
        voteResultItem.ivVotePercent = ivVote;
        voteResultItem.tvVoteName = tvVoteName;
        mVoteList.add(voteResultItem);
    }

}
