package com.xiaoniu.finance.easyswitch;

import android.app.Activity;

import java.util.ArrayList;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/5/31
 */

public class ActivityCollector {
    private ArrayList<Activity>  al = new ArrayList<>();
    private  void addActivity(Activity activity){
        al.add(activity);
    }
    private  void removeActivity(Activity activity){
        for (Activity activitys : al){
            if(!activitys.isFinishing()){
                activitys.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }
}
