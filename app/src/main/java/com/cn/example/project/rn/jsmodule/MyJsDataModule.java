package com.cn.example.project.rn.jsmodule;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;

public interface MyJsDataModule extends JavaScriptModule {

    void updateJsData(String appKey, WritableMap appParameters);

}
