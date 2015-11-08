package com.joe.root.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.example.root.ui.R;

/**
 * Created by JoeZ on 2015/10/14.
 */
public class ChoiceListLayout extends LinearLayout {


    public ChoiceListLayout(Context context) {
        super(context);
    }

    public ChoiceListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoiceListLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addOption(String description,boolean isChecked) {
        addSentenceOption(description,isChecked);
    }

    private OnChoiceChangedListener mOnChoiceChangedListener;
    public void addOnChoiceChangeListener(OnChoiceChangedListener onChoiceChangedListener){
        mOnChoiceChangedListener = onChoiceChangedListener;
    }

    private LinearLayout mCheckBoxOptionsContainer;

    private void addSentenceOption(String description, boolean isChecked) {
        Context context = getContext();
        if (mCheckBoxOptionsContainer == null) {
            mCheckBoxOptionsContainer = new LinearLayout(context);
            setOrientation(LinearLayout.VERTICAL);
            mCheckBoxOptionsContainer.setOrientation(LinearLayout.VERTICAL);
            LayoutParams params = generateDefaultLayoutParams();
            params.leftMargin = (int) getResources().getDimension(R.dimen.d_32);
            params.rightMargin = params.leftMargin;
            this.addView(mCheckBoxOptionsContainer,params);
        }
        addCheckBoxOption(context, description, isChecked);
    }

    private boolean mIsWidgetChecked;
    private void addCheckBoxOption(Context context,String title,boolean isChecked) {
        ViewGroup cbContainner = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.widget_feed_checkbox_option,mCheckBoxOptionsContainer,false);
        final CheckBox checkBox = (CheckBox) cbContainner.findViewById(R.id.cb_feed_option);
        checkBox.setText(title);
        checkBox.setChecked(isChecked);
        if(isChecked){
            mIsWidgetChecked = true;
        }
        checkBox.setTag(R.id.cb_feed_option,isChecked);
        checkBox.setTag(mCheckBoxOptionsContainer.getChildCount());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBox.setChecked(mIsWidgetChecked?(Boolean)checkBox.getTag(R.id.cb_feed_option):true);
                mOnChoiceChangedListener.onChoiceChange((Integer) checkBox.getTag());
            }
        });
        mCheckBoxOptionsContainer.addView(cbContainner);
    }

    public void clearOptions() {
        if (mCheckBoxOptionsContainer != null) {
            mCheckBoxOptionsContainer.removeAllViews();
            mCheckBoxOptionsContainer = null;
        }
        this.removeAllViews();
    }

    public interface OnChoiceChangedListener {
        void onChoiceChange(int checkPosition);
    }
}
