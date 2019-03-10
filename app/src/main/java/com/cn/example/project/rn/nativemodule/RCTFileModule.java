package com.cn.example.project.rn.nativemodule;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;

import com.cn.example.project.rn.screenshot.ScreenshotUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;

public class RCTFileModule extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS = "RNFile";

    public RCTFileModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @ReactMethod
    public void screenShot(final String fileName){
        final View rootView = getCurrentActivity().getWindow().getDecorView();

        rootView.post(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = ScreenshotUtils.getScreenshot(rootView);
                String filePath = getCurrentActivity().getExternalCacheDir().getAbsolutePath()+File.separator+"mypic"+File.separator+fileName;
                Log.i("lvjie", "filePath="+filePath);
                ScreenshotUtils.saveBitmap(bitmap, filePath);
            }
        });
    }

}
