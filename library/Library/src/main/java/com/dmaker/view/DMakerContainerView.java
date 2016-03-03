package com.dmaker.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Daduck on 3/2/16.
 */
public class DMakerContainerView extends LinearLayout {
    private boolean isSquare = true;

    public DMakerContainerView(Context context) {
        super(context);
    }

    public DMakerContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DMakerContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DMakerContainerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if(isSquare && measuredHeight > measuredWidth){
            setMeasuredDimension(measuredWidth, measuredWidth);
            View child = getChildAt(0);
            measureChild(child, widthMeasureSpec, widthMeasureSpec);
        }
    }
    public void setIsSquare(boolean isSquare){
        this.isSquare = isSquare;
    }
}
