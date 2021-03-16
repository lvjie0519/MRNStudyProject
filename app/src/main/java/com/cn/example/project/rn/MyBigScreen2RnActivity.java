package com.cn.example.project.rn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.cn.example.project.BuildConfig;
import com.cn.example.project.rn.ReactNativeSdkPackage;
import com.cn.example.project.rn.fixbug.FixBugMainReactPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class MyBigScreen2RnActivity extends FragmentActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private FrameLayout mLayoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutContent = new FrameLayout(this);
        setContentView(mLayoutContent);

        initRnView();
    }

    private void initRnView(){
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new FixBugMainReactPackage())
                .addPackage(new ReactNativeSdkPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        // 注意这里的MyReactNativeApp必须对应“index.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);

//        setContentView(getLayoutContentView(mReactRootView));
        updateLayoutContentView(mReactRootView);
    }

    public FrameLayout updateLayoutContentView(View reactView) {
        ViewGroup content = findViewById(Window.ID_ANDROID_CONTENT);
        content.setBackgroundColor(Color.parseColor("#88000000"));
        ViewGroup.LayoutParams contentParams = mLayoutContent.getLayoutParams();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        contentParams.width = (int) (metrics.widthPixels*0.8f);
        contentParams.height = (int) (metrics.heightPixels*0.8f);
        if (contentParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) contentParams).gravity = Gravity.CENTER;
        }
        mLayoutContent.setLayoutParams(contentParams);
        mLayoutContent.addView(reactView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) );
        return mLayoutContent;
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }
}
