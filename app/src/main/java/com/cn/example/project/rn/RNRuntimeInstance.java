package com.cn.example.project.rn;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.cn.example.project.BuildConfig;
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

public class RNRuntimeInstance {

    private Context mApplicationContext;
    private boolean mIsInit = false;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private static volatile RNRuntimeInstance INSTANCE;

    private ReactInstanceEventListener mReactInstanceEventListener;

    private RNRuntimeInstance(){};

    public static RNRuntimeInstance getInstance(){
        if(INSTANCE == null){
            synchronized (RNRuntimeInstance.class){
                if(INSTANCE == null){
                    INSTANCE = new RNRuntimeInstance();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Activity activity){
        mApplicationContext = activity.getApplicationContext();
        if(!mIsInit){
            mIsInit = true;
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
    }

    private void loadJsBundle(CatalystInstanceImpl instance){
        JSBundleLoader.createAssetLoader(mApplicationContext, "assets://index.android.bundle", false).loadScript(instance);
    }

    public void reLoadJsBundle(){
        long time = System.currentTimeMillis();
        CatalystInstanceImpl instance = (CatalystInstanceImpl) mReactInstanceManager.getCurrentReactContext().getCatalystInstance();
        JSBundleLoader.createAssetLoader(mApplicationContext, "assets://index.android.bundle", false).loadScript(instance);
        Log.i("lvjie", "time cost "+(System.currentTimeMillis()-time));
    }

    public void syncReLoadJsBundle(){
        long time = System.currentTimeMillis();
        CatalystInstanceImpl instance = (CatalystInstanceImpl) mReactInstanceManager.getCurrentReactContext().getCatalystInstance();
        JSBundleLoader.createAssetLoader(mApplicationContext, "assets://index.android.bundle", true).loadScript(instance);
        Log.i("lvjie", "sync time cost "+(System.currentTimeMillis()-time));
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

        mIsInit = false;
        if(mReactRootView != null){
            mReactRootView.unmountReactApplication();
        }

        mReactRootView = null;
        if(mReactInstanceManager != null){
            mReactInstanceManager.destroy();
        }
        mReactInstanceManager = null;
        mReactInstanceEventListener = null;
        mApplicationContext = null;
    }

    public interface ReactInstanceEventListener{
        void onReactContextInitialized(ReactContext context, ReactRootView reactRootView);
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }
}
