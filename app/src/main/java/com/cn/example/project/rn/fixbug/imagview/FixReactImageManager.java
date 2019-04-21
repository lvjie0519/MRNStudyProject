package com.cn.example.project.rn.fixbug.imagview;

import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.image.ReactImageView;

public class FixReactImageManager extends ReactImageManager {


    @Override
    public ReactImageView createViewInstance(ThemedReactContext context) {
        return new FixReactImageView(context, getDraweeControllerBuilder(), null, getCallerContext());
    }

    @Override
    public void setResizeMode(ReactImageView hostView, @Nullable String resizeMode) {
        Log.i("lvjie", "FixReactImageManager-->setResizeMode()...");
        if(hostView instanceof  FixReactImageView && "stretch".equalsIgnoreCase(resizeMode)) {
            ((FixReactImageView) hostView).mFiltered = true;
        }
        super.setResizeMode(hostView, resizeMode);
    }


//    public void setSource(ReactImageView view, @Nullable ReadableArray sources) {
//        if(sources != null && sources.size() > 0) {
//            if(sources.getType(0) == ReadableType.Map){
//                ReadableMap readableMap = sources.getMap(0);
//                Map<String, Object> map = null;
//                if(readableMap.hasKey("uri")){
//                    String uri = readableMap.getString("uri");
//                    if(uri != null && uri.startsWith("/")){
//                        map = readableMap.toHashMap();
//                        map.put("uri", "file://" + uri);
//                    }
//                }else if(readableMap.hasKey("local")){
//                    String local = readableMap.getString("local");
//                    map = readableMap.toHashMap();
//                    map.remove("local");
//                    map.put("uri", "file://" + new File(runtimeInfo.getPluginFilesPath(), local).getAbsolutePath());
//                }
//                if(map != null){
//                    ArrayList<Object> list = sources.toArrayList();
//                    list.remove(0);
//                    list.add(0, map);
//                    sources = Arguments.makeNativeArray(list);
//                }
//            }
//        }
//        view.setSource(sources);
//    }

}
