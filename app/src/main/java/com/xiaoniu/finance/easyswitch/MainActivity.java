package com.xiaoniu.finance.easyswitch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ScrollSwitch mSwitchs;
    private ScrollSwitchLock mSwitchScroll;
    private ViewGroupChanged mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchs = findViewById(R.id.switch_click);
        mSwitchScroll = findViewById(R.id.scroll_lock);
        mGroup = findViewById(R.id.group);
        mSwitchs.setSwitchChangeListner(new ScrollSwitch.onClickLisetner() {
            @Override
            public void changeSwitchStatus(boolean flag) {
                String switchState = flag ? "开关打开" : "开关关闭";
                Toast.makeText(MainActivity.this, switchState, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,DownLoadService.class);
                if(flag){
                    startService(intent);
                }else {
                    stopService(intent);
                }
            }
        });
        mSwitchScroll.setSwitchChanged(new ScrollSwitchLock.ISwicthLockListner() {
            @Override
            public void switchTurnOn() {
                Toast.makeText(MainActivity.this, "解锁成功", Toast.LENGTH_LONG).show();
            }
        });
        mGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGroup.change();
            }
        });
    }
}
