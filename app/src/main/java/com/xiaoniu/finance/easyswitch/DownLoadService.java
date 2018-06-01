package com.xiaoniu.finance.easyswitch;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * 文件描述：    模拟下载的服务
 * Created by  xn069392
 * 创建时间    2018/5/31
 */

public class DownLoadService extends Service {

    private int mProgress;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: 开启服务");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onCreate: 关闭服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onCreate: onStartCommand!!!!!!!!!!!!!!!!!!!!!!!");
        return super.onStartCommand(intent, flags, startId);
    }

    private DownLoadBinder binder = new DownLoadBinder();

    class DownLoadBinder extends Binder {
        public void startDownload() {
            //开启下载
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            Thread.sleep(50);
                            mProgress = i;
                            Log.e(TAG, "run: "+ i );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        public int getProgress() {
            Log.e(TAG, "getProgress: "+mProgress );
            return mProgress;
        }


    }
    public  int getProgress() {
        Log.e(TAG, "getProgress: "+mProgress );
        return mProgress;
    }
}
