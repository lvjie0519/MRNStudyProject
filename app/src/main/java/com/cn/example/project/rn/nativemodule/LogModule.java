package com.cn.example.project.rn.nativemodule;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class LogModule extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS = "RNLog";

    public LogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void i(String tag, String msg){
        Log.i(tag, msg);
    }

    @ReactMethod
    public void v(String tag, String msg){
        Log.v(tag, msg);
    }

    @ReactMethod
    public void d(String tag, String msg){
        Log.d(tag, msg);
    }

    @ReactMethod
    public void w(String tag, String msg){
        Log.w(tag, msg);
    }

    @ReactMethod
    public void e(String tag, String msg){
        Log.e(tag, msg);
    }

}
