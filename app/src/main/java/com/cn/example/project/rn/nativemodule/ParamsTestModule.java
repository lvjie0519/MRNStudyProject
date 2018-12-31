package com.cn.example.project.rn.nativemodule;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * 主要用来测试与js之间参数传递相关
 */
public class ParamsTestModule extends ReactContextBaseJavaModule {


    public ParamsTestModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNParams";
    }


    @Nullable
    @Override
    public Map<String, Object> getConstants() {

        Log.i("lvjie", "ParamsTestModule--getConstants()...");
        // 定义一些可以被 JavaScript 同步访问到的预定义的值
        Map<String, Object> constants = new HashMap<>();
        constants.put("paramsA", "paramsA value");
        constants.put("paramsB", "paramsB value");

        return constants;
    }

    /**
     * 通常如果需要将函数执行的结果返回给js，并不是直接通过return来完成，而是通过callback回调来完成
     * 当然结果返回并不仅仅只有callback回调一种方式，还有promise
     * @param a
     * @param b
     * @param callback
     */
    @ReactMethod
    public void operateAdd(int a, int b, Callback callback){
        int result = a+b;
        callback.invoke(result);
    }

    /**
     * 通过promise来实现结果返回
     * @param a
     * @param b
     * @param promise
     */
    @ReactMethod
    public void operateDivide(int a, int b, Promise promise){
        if(b == 0){
            promise.reject("can not divide 0...", new Throwable("can not divide 0！"));
        }
        double result = a/b;
        promise.resolve(result);
    }

    /**
     * 通过事件向js发送数据
     * @param a
     * @param b
     */
    @ReactMethod
    public void sendParamsByEvent(int a, int b){
        String data = "a: "+a+"  b: "+b;
        // 关键是这行
        getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("eventSendParams", data);
    }


}
