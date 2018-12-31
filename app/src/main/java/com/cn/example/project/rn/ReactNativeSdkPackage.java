package com.cn.example.project.rn;

import com.cn.example.project.rn.nativemodule.LogModule;
import com.cn.example.project.rn.nativemodule.ParamsTestModule;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class ReactNativeSdkPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new LogModule(reactContext));
        modules.add(new ParamsTestModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

        // 注意此处不能返回null
        List<ViewManager> viewManagers = new ArrayList<>();

        return viewManagers;
    }
}
