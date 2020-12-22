package com.cn.example.project.rn;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

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

public class RNRuntimeInstanceFourth {

    private Context mApplicationContext;

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private static volatile RNRuntimeInstanceFourth INSTANCE;

    private ReactInstanceEventListener mReactInstanceEventListener;

    private boolean mShouldLoadJsBundle = true;

    private RNRuntimeInstanceFourth(){};

    public static RNRuntimeInstanceFourth getInstance(){
        if(INSTANCE == null){
            synchronized (RNRuntimeInstanceFourth.class){
                if(INSTANCE == null){
                    INSTANCE = new RNRuntimeInstanceFourth();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Activity activity){
        mApplicationContext = activity.getApplicationContext();

        if(mReactRootView == null){
            mReactRootView = new ReactRootView(activity.getApplicationContext());

            mReactRootView.setEventListener(new ReactRootView.ReactRootViewEventListener() {
                @Override
                public void onAttachedToReactInstance(ReactRootView rootView) {
                    Log.i("lvjie", "RNRuntimeInstance onAttachedToReactInstance...");
                    if(mReactInstanceEventListener != null){
                        mReactInstanceEventListener.onAttachedToReactInstance(mReactRootView);
                    }
                }
            });
        }

        if(mReactInstanceManager == null){
            mReactInstanceManager = ReactInstanceManager.builder()
                    .setApplication(activity.getApplication())
//                .setBundleAssetName("index.android.bundle")
                    .setJSBundleLoader(new JSBundleLoader() {
                        @Override
                        public String loadScript(CatalystInstanceImpl instance) {
                            Log.i("lvjie", "RNRuntimeInstance start loadScript..."+System.currentTimeMillis());
                            loadJsBundle(instance);
                            Log.i("lvjie", "RNRuntimeInstance end loadScript..."+System.currentTimeMillis());
                            return "loadScript";
                        }
                    })
                    .setJSMainModulePath("index")       // 本地调试的时候才需要 Path to your app's main module on the packager server. This is used when  reloading JS during development.
                    .addPackage(new MainReactPackage())
                    .addPackage(new ReactNativeSdkPackage())
                    .addPackage(new ReactVideoPackage())
                    .setUseDeveloperSupport(false)
                    .setInitialLifecycleState(LifecycleState.RESUMED)
                    .setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
                        @Override
                        public void handleException(Exception e) {
                            // 捕捉RN异常
                            e.printStackTrace();
                            Log.i("lvjie", "RNRuntimeInstance "+e.toString());
                        }
                    })
                    .build();
        }

        mReactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
            @Override
            public void onReactContextInitialized(ReactContext context) {
                Log.i("lvjie", "RNRuntimeInstance onReactContextInitialized...");
                if(mReactInstanceEventListener != null){
                    mReactInstanceEventListener.onReactContextInitialized(context, mReactRootView);
                }
            }
        });

    }

    private void loadJsBundle(CatalystInstanceImpl instance){
        if(!mShouldLoadJsBundle){
            return;
        }
        JSBundleLoader.createAssetLoader(mApplicationContext, "assets://index.android.bundle", false).loadScript(instance);
    }

    public void reLoadJsBundle(){
        if(!mShouldLoadJsBundle){
            return;
        }
        CatalystInstanceImpl instance = (CatalystInstanceImpl) mReactInstanceManager.getCurrentReactContext().getCatalystInstance();
        JSBundleLoader.createAssetLoader(mApplicationContext, "assets://index.android.bundle", false).loadScript(instance);
    }

    public void startReactApplication(){
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);
    }

    public ReactRootView getReactRootView() {
        return mReactRootView;
    }

    public void setReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListener = reactInstanceEventListener;
    }

    public void setShouldLoadJsBundle(boolean shouldLoadJsBundle) {
        this.mShouldLoadJsBundle = shouldLoadJsBundle;
    }

    public void onBackPressed(){
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        }
    }

    public ReactInstanceManager getReactInstanceManager(){
        return mReactInstanceManager;
    }

    public void onHostPause(Activity activity){
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(activity);
        }
    }

    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl){
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(activity, defaultBackButtonImpl);
        }
    }

    public void onDestroy(Activity activity){
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(activity);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }



    public void clearReact(){

        if(mReactRootView != null){
            mReactRootView.unmountReactApplication();
        }

        mReactRootView = null;
//        if(mReactInstanceManager != null){
//            mReactInstanceManager.destroy();
//        }
//        mReactInstanceManager = null;
//        mReactInstanceEventListener = null;
//        mApplicationContext = null;
    }

    public interface ReactInstanceEventListener{
        void onReactContextInitialized(ReactContext context, ReactRootView reactRootView);
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }
}
