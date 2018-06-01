package com.xiaoniu.finance.easyswitch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/6/1
 */

public class SelfSlidingMenu extends ViewGroup {

    private float mX;
    private float mY;
    private View mMenu;

    public SelfSlidingMenu(Context context) {
        this(context, null);
    }

    public SelfSlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View main = getChildAt(0);
        mMenu = getChildAt(1);
        mMenu.layout(-mMenu.getMeasuredWidth(), 0, 0, mMenu.getMeasuredHeight());
        main.layout(0, 0, main.getMeasuredWidth(), main.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float v = x - mX;
                float v1 = y - mY;
                float v2 = getScrollX() - v;
                if (v2 < 0 && v2 > -mMenu.getMeasuredWidth()) {
                    scrollTo((int) v2, (int) -v1);
                }
                mX = x;
                mY = y;
                break;
            case MotionEvent.ACTION_UP:

                if (getScrollX() < -mMenu.getMeasuredWidth() / 2) {
                    scrollTo(-mMenu.getMeasuredWidth(), 0);
                } else {
                    scrollTo(0, 0);
                }
                break;
        }

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
