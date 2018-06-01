package com.xiaoniu.finance.easyswitch;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    private ScrollSwitch mSwitchs;
    private ScrollSwitchLock mSwitchScroll;
    private ViewGroupChanged mGroup;
    private Intent mIntent;
    private DownLoadService.DownLoadBinder mBinder;
    private int mProgress;
    private FrameLayout mLayout;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchs = findViewById(R.id.switch_click);
        mSwitchScroll = findViewById(R.id.scroll_lock);
        mLayout = findViewById(R.id.progress);
        mGroup = findViewById(R.id.group);
        mIntent = new Intent(MainActivity.this, DownLoadService.class);
        mSwitchs.setSwitchChangeListner(new ScrollSwitch.onClickLisetner() {
            @Override
            public void changeSwitchStatus(boolean flag) {
                String switchState = flag ? "开关打开" : "开关关闭";
                Toast.makeText(MainActivity.this, switchState, Toast.LENGTH_LONG).show();

                if (flag) {
                    startService(mIntent);
                } else {
                    stopService(mIntent);
                }
            }
        });
        mSwitchScroll.setSwitchChanged(new ScrollSwitchLock.ISwicthLockListner() {
            @Override
            public void switchTurnOn() {
                Toast.makeText(MainActivity.this, "解锁成功", Toast.LENGTH_LONG).show();
                bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });
        mGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGroup.change();
            }
        });
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mProgressBar = new ProgressBar(MainActivity.this);
        mProgressBar.setMax(99);
        mProgressBar.setProgress(60);
        mLayout.addView(mProgressBar, mParams);
        Log.e(TAG, "onCreate: progress   "+mProgress );
        mLayout.setVisibility(View.VISIBLE);

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (DownLoadService.DownLoadBinder) service;
            mBinder.startDownload();

//            mProgress = DownLoadService.getProgress();
            mProgressBar.setProgress(mProgress);
            if (mProgress == mProgressBar.getMax()) {
                mProgressBar.setVisibility(View.GONE);
            }

            Log.e(TAG, "onServiceConnected: &&&&&&&&&&&&&&&&&" );
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null)
            unbindService(mServiceConnection);
    }


}
