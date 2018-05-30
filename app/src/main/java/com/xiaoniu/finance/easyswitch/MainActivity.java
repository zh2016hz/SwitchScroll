package com.xiaoniu.finance.easyswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ScrollSwitch mSwitchs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchs = findViewById(R.id.switch_click);
        mSwitchs.setSwitchChangeListner(new ScrollSwitch.onClickLisetner() {
            @Override
            public void changeSwitchStatus(boolean flag) {
                String  switchState = flag?"开关打开":"开关关闭";
                Toast.makeText(MainActivity.this,switchState,Toast.LENGTH_LONG).show();
            }
        });
    }
}
