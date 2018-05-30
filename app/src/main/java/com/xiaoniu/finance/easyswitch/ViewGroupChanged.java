package com.xiaoniu.finance.easyswitch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/5/30
 */

public class ViewGroupChanged extends ViewGroup {
    private boolean flag;

    public ViewGroupChanged(Context context) {
        this(context, null);
    }

    public ViewGroupChanged(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getChildAt(0).getMeasuredWidth();
        int height = getChildAt(0).getMeasuredHeight();
        int left = 0;
        int top = 0;
        for (int i = 0; i < getChildCount(); i++) {
               /* if(i%2 == 0){
                    getChildAt(i).layout(0, i*height, width, height + i * height);
                }else {
                    Log.e(TAG, "onLayout: "+getMeasuredWidth() );
                    Log.e(TAG, "onLayout: "+ width );
                    getChildAt(i).layout(getMeasuredWidth()-width, i*height, width, height + i * height);
                }*/
            if (flag) {
                if (i % 2 == 0) {
                    //偶数
                    left = 0;
                } else {
                    left = getMeasuredWidth() - getChildAt(0).getMeasuredWidth();
                }
                int right = left + getChildAt(0).getMeasuredWidth();

                int button = top + getChildAt(0).getMeasuredHeight();

                getChildAt(i).layout(left, top, right, button);
                top = button;
            } else {
                if (i % 2 == 1) {
                    //偶数
                    left = 0;
                } else {
                    left = getMeasuredWidth() - getChildAt(0).getMeasuredWidth();
                }
                int right = left + getChildAt(0).getMeasuredWidth();

                int button = top + getChildAt(0).getMeasuredHeight();

                getChildAt(i).layout(left, top, right, button);
                top = button;
            }


        }
    }

    public void change() {
        flag = !flag;
//        postInvalidate();
        requestLayout();
    }
}
