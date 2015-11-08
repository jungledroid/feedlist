package com.joe.root.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by JoeZ on 2015/10/26.
 */
public class HeightEqualImageView extends ImageView {
    public HeightEqualImageView(Context context) {
        super(context);
    }

    public HeightEqualImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeightEqualImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
