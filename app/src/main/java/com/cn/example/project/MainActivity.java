package com.cn.example.project;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cn.example.project.rn.MyFirstRnActivity;
import com.cn.example.project.rn.MySecondRnActivity;
import com.cn.example.project.rn.dialog.MessageDialog;

public class MainActivity extends AppCompatActivity {

    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        mRootView = findViewById(R.id.layout_root_view);

        mRootView.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.i("lvjie", "onDraw...");
            }
        });
    }

    public void goRnPage(View view){
        Intent intent = new Intent(this, MyFirstRnActivity.class);
        startActivity(intent);
    }

    public void goSecondRnPage(View view){
        Intent intent = new Intent(this, MySecondRnActivity.class);
        startActivity(intent);
    }

    // js 调用 java  需要返回值

    public void testBtn(View view){
//        is24HourFormat();

//        getWifiInfo();
        showDialog();
    }

    private void is24HourFormat(){
        boolean is24 = DateFormat.is24HourFormat(this);
        Log.i("lvjie", "is24="+is24);
    }

    private void getWifiInfo(){

        //获取wifi服务
        WifiManager mWifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(mWifiManager.isWifiEnabled()){
            Log.i("lvjie", "wifi is enabled");
        }

        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        if(mWifiInfo != null){
            String mac = mWifiInfo.getMacAddress();
        }else{
            Log.i("lvjie", "wifi info is null...");
        }

    }


    private void showDialog(){
        MessageDialog dialog = new MessageDialog(this);
        dialog.show();
    }

}
