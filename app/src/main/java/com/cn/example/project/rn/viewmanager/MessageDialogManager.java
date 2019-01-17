package com.cn.example.project.rn.viewmanager;

import android.util.Log;

import com.cn.example.project.rn.dialog.MessageDialog;
import com.cn.example.project.rn.viewmanager.hostview.MessageDialogHostView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import javax.annotation.Nullable;

public class MessageDialogManager extends SimpleViewManager<MessageDialogHostView> {

    @Override
    public String getName() {
        return "RCTMessageDialog";
    }

    @Override
    protected MessageDialogHostView createViewInstance(ThemedReactContext reactContext) {
        return new MessageDialogHostView(reactContext);
    }

    @ReactProp(name="title")
    public void setTitile(MessageDialogHostView hostView, String titile){
        Log.i("lvjie", "setTitile()...");
        hostView.setTitile(titile);
    }

    @ReactProp(name="message")
    public void setMessage(MessageDialogHostView hostView, String message){
        hostView.setMessage(message);
    }


    @ReactProp(name="visible")
    public void setVisible(MessageDialogHostView hostView, boolean visible){
        hostView.setVisible(visible);
    }

    /**
     * https://www.jianshu.com/p/8b8ebfaca343
     * @param reactContext
     * @param view
     */
    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, final MessageDialogHostView view) {
        Log.i("lvjie", "MessageDialogManager-->addEventEmitters()...");
        view.setOnClick(new MessageDialog.DialogOnClick() {
            @Override
            public void onCancelClick() {
                WritableMap data = Arguments.createMap();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(), "onCancel", data);
            }

            @Override
            public void onConfirmClick() {
                WritableMap data = Arguments.createMap();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(), "onConfirm", data);
            }

            @Override
            public void onDialogDismiss() {
                WritableMap data = Arguments.createMap();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(), "onDismiss", data);
            }
        });


    }

    /**
     * getExportedCustomDirectEventTypeConstants()方法暴露给ReactNative端
     * @return
     */
    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Log.i("lvjie", "MessageDialogManager-->getExportedCustomDirectEventTypeConstants()...");
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        return builder
                .put("onConfirm", MapBuilder.of("registrationName", "onConfirm"))
                .put("onCancel", MapBuilder.of("registrationName", "onCancel"))
                .put("onDismiss", MapBuilder.of("registrationName", "onDismiss"))
                .build();
    }



    @Override
    protected void onAfterUpdateTransaction(MessageDialogHostView view) {
        Log.i("lvjie", "MessageDialogManager-->onAfterUpdateTransaction()...");
        view.showOrUpdateDialog();
    }

    @Override
    public void onDropViewInstance(MessageDialogHostView view) {
        view.onDropInstance();
        super.onDropViewInstance(view);
    }
}
