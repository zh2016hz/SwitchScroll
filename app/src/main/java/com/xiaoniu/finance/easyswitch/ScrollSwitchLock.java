package com.xiaoniu.finance.easyswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 文件描述：    滑动解锁屏幕
 * Created by  xn069392
 * 创建时间    2018/5/30
 */

public class ScrollSwitchLock extends View {

    private Bitmap mSwicth;
    private Paint mPaint;

    public ScrollSwitchLock(Context context) {
        this(context, null);
    }

    public ScrollSwitchLock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        mSwicth = BitmapFactory.decodeResource(getResources(), R.drawable.switchs);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, mSwicth.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mSwicth, 0, 0, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getRawX();
                int dex;
                if (x < mSwicth.getWidth() / 2 || x > mSwicth.getWidth()) {
                    dex = 0;
                } else {
                    dex = (int) (-x + mSwicth.getWidth() / 2);
                }
                scrollTo(dex, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getRawX();
                if (x1 > mSwicth.getWidth() / 2 && x1 < getWidth() - mSwicth.getWidth() / 2) {
                    scrollTo((int) -x1 + mSwicth.getWidth() / 2, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                float rawX = event.getRawX();
                if(rawX<getWidth()-mSwicth.getWidth()/2){
                    scrollTo(0,0);
                }else {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return true;
    }
}
