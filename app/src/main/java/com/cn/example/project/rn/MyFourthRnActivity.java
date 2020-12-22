package com.cn.example.project.rn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cn.example.project.R;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * 用来验证， ReactInstanceManager 重建，但加载js bundle只进行一次加载， 看是否允许
 */
public class MyFourthRnActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private FrameLayout mLayoutReact;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MyFourthRnActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fourth_rn);

        initRnView();
    }

    private void initRnView() {
        mLayoutReact = findViewById(R.id.layout_react);

        RNRuntimeInstanceFourth.getInstance().init(this);
        RNRuntimeInstanceFourth.getInstance().setReactInstanceEventListener(new RNRuntimeInstanceFourth.ReactInstanceEventListener() {
            @Override
            public void onReactContextInitialized(ReactContext context, ReactRootView reactRootView) {
                Log.i("lvjie", "MyThirdRnActivity onReactContextInitialized...");

            }

            @Override
            public void onAttachedToReactInstance(ReactRootView reactRootView) {
                Log.i("lvjie", "MyThirdRnActivity onAttachedToReactInstance...");
                if (reactRootView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) reactRootView.getParent()).removeView(reactRootView);
                }

                mLayoutReact.addView(reactRootView, 0);
            }
        });
        RNRuntimeInstanceFourth.getInstance().startReactApplication();

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        if(RNRuntimeInstanceFourth.getInstance().getReactInstanceManager() != null){
            RNRuntimeInstanceFourth.getInstance().onBackPressed();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        RNRuntimeInstanceFourth.getInstance().onHostPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RNRuntimeInstanceFourth.getInstance().onHostResume(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RNRuntimeInstanceFourth.getInstance().onDestroy(this);
    }




}
