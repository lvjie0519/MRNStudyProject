package com.cn.example.project.rn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cn.example.project.BuildConfig;
import com.cn.example.project.R;
import com.cn.example.project.rn.nativemodule.react_native_video.react.ReactVideoPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DevLoadingViewController;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

public class MySecondRnActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private FrameLayout mLayoutReact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_second_rn);

        initRnView();
    }

    private void initRnView(){
        mLayoutReact = findViewById(R.id.layout_react);
        mReactRootView = new ReactRootView(this.getApplicationContext());
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
                .setJSBundleLoader(new JSBundleLoader() {
                    @Override
                    public String loadScript(CatalystInstanceImpl instance) {
                        Log.i("lvjie", "start loadScript..."+System.currentTimeMillis());
                        JSBundleLoader.createAssetLoader(MySecondRnActivity.this,
                                "assets://index.android.bundle", false).loadScript(instance);
                        Log.i("lvjie", "end loadScript..."+System.currentTimeMillis());
                        return "loadScript";
                    }
                })
                .setJSMainModulePath("index")       // 本地调试的时候才需要 Path to your app's main module on the packager server. This is used when  reloading JS during development.
                .addPackage(new MainReactPackage())
                .addPackage(new ReactNativeSdkPackage())
                .addPackage(new ReactVideoPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
                    @Override
                    public void handleException(Exception e) {
                        // 捕捉RN异常
                        e.printStackTrace();
                        Log.i("lvjie", e.toString());
                    }
                })
                .build();

        mReactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
            @Override
            public void onReactContextInitialized(ReactContext context) {
                Log.i("lvjie", "onReactContextInitialized...");
                if (mReactRootView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) mReactRootView.getParent()).removeView(mReactRootView);
                }

                mLayoutReact.addView(mReactRootView, 0);
            }
        });

        // 注意这里的MyReactNativeApp必须对应“index.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);

    }

    public void onClickRnLoadTestBtn(View view){
        mReactInstanceManager.getDevSupportManager().handleReloadJS();
    }


    public void onClickLoadJsBundle(View view) {
        // 提前加载js bundle
//        ReactContext reactContext = mReactInstanceManager.getCurrentReactContext();
//        if(reactContext != null){
//            JSBundleLoader.createAssetLoader(reactContext, "assets://index.android.bundle", false).loadScript((CatalystInstanceImpl)reactContext.getCatalystInstance());
//        }

    }


    public void onClickShowRnView(View view) {
        // 显示RN 界面
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);
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
