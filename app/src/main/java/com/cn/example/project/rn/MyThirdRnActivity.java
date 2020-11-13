package com.cn.example.project.rn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cn.example.project.R;
import com.cn.example.project.rn.nativemodule.react_native_video.react.ReactVideoPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * 对加载RN 做优化
 */
public class MyThirdRnActivity extends Activity  implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private FrameLayout mLayoutReact;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MyThirdRnActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_third_rn);

        initRnView();
    }

    private void initRnView() {
        mLayoutReact = findViewById(R.id.layout_react);

        RNRuntimeInstance.getInstance().init(this);
        RNRuntimeInstance.getInstance().setReactInstanceEventListener(new RNRuntimeInstance.ReactInstanceEventListener() {
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
        RNRuntimeInstance.getInstance().startReactApplication();

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        if(RNRuntimeInstance.getInstance().getReactInstanceManager() != null){
            RNRuntimeInstance.getInstance().onBackPressed();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        RNRuntimeInstance.getInstance().onHostPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RNRuntimeInstance.getInstance().onHostResume(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RNRuntimeInstance.getInstance().onDestroy(this);
    }
}
