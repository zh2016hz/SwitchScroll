package com.xiaoniu.finance.easyswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/5/30
 */

public class ScrollSwitch extends View {

    private Bitmap mBackground;
    private Bitmap mSwitch;
    private Paint mPaint;
    private boolean flag;
    private onClickLisetner listener;

    public ScrollSwitch(Context context) {
        this(context, null);
    }

    public ScrollSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initEvent();
    }

    private void initEvent() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                invalidate();
                if(listener != null){
                    listener.changeSwitchStatus(flag);
                }
            }
        });
    }

    private void init() {
        mBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        mSwitch = BitmapFactory.decodeResource(getResources(), R.drawable.switchs);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBackground.getWidth(), mBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float  left;
        canvas.drawBitmap(mBackground,0,0,mPaint);
        if(!flag){
            left = 0;
        }else {
            left =mBackground.getWidth() - mSwitch.getWidth();
        }
        canvas.drawBitmap(mSwitch,left,0,mPaint);
    }

    public   interface  onClickLisetner{
        void  changeSwitchStatus(boolean flag);
    }
    public   void  setSwitchChangeListner(onClickLisetner l){
        this.listener  = l;
    }
}
