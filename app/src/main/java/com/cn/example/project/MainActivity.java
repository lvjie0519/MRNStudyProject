package com.cn.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cn.example.project.rn.MyBigScreenRnActivity;
import com.cn.example.project.rn.MyFirstRnActivity;
import com.cn.example.project.rn.MyFourthRnActivity;
import com.cn.example.project.rn.MySecondRnActivity;
import com.cn.example.project.rn.MyThirdRnActivity;
import com.cn.example.project.rn.RNRuntimeInstance;
import com.cn.example.project.rn.RNRuntimeInstanceFourth;
import com.cn.example.project.rn.android.TestAndroidMainActivity;

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

    public void goTestAndroidMainPage(View view){
        TestAndroidMainActivity.startActivity(this);
    }


    public void goThirdRnPage(View view) {
        MyThirdRnActivity.startActivity(this);
    }

    public void onClickClearReactCache(View view) {
        RNRuntimeInstance.getInstance().clearReact();
    }

    public void onClickReloadBundle(View view) {
        RNRuntimeInstance.getInstance().reLoadJsBundle();
    }

    public void onClickSyncReloadBundle(View view) {
        RNRuntimeInstance.getInstance().syncReLoadJsBundle();
    }

    public void onClickRnActivityFourth(View view) {
        MyFourthRnActivity.startActivity(this);
    }

    public void onClickClearReactCacheFourth(View view) {
        RNRuntimeInstanceFourth.getInstance().clearReact();
        RNRuntimeInstanceFourth.getInstance().setShouldLoadJsBundle(false);
    }

    public void goBigSceenOpenRnActivity(View view) {
        Intent intent = new Intent(this, MyBigScreenRnActivity.class);
        startActivity(intent);
    }
}
