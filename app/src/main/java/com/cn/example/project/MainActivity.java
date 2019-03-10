package com.cn.example.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.cn.example.project.rn.MyFirstRnActivity;
import com.cn.example.project.rn.MySecondRnActivity;
import com.cn.example.project.rn.dialog.MessageDialog;
import com.cn.example.project.rn.screenshot.ScreenshotUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

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
//        showDialog();

        ScrollView scrollView = findViewById(R.id.sv_conent);
        Bitmap bitmap = ScreenshotUtils.shotScrollView(scrollView);

        String fileName = new Date().getTime() +".png";
        String filePath = getExternalCacheDir().getAbsolutePath()+File.separator+"mypic"+File.separator+fileName;
        Log.i("lvjie", filePath);
        try {
            boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, new BufferedOutputStream(new FileOutputStream(filePath)));
            Log.i("lvjie", "compress is "+result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView imageView = findViewById(R.id.iv_img);
        Bitmap bitmap1 = BitmapFactory.decodeFile(filePath);
        imageView.setImageBitmap(bitmap1);

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
