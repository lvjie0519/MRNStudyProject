package com.cn.example.project.rn.fixbug;

import android.util.Log;
import android.util.Pair;

import com.cn.example.project.rn.fixbug.imagview.FixReactImageManager;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.image.ReactImageManager;

import java.util.ArrayList;
import java.util.List;

public class FixBugMainReactPackage extends MainReactPackage {


    @Override
    public List<ModuleSpec> getNativeModules(ReactApplicationContext context) {

        List<ModuleSpec> moduleSpecList = super.getNativeModules(context);

        return moduleSpecList;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {

        //replace managers
        List<Pair<ViewManager, ViewManager>> replacedManagers = new ArrayList<>();
        List<ViewManager> managers = super.createViewManagers(reactContext);
        for(ViewManager manager:managers){
            if(manager instanceof ReactImageManager){
                replacedManagers.add(new Pair<ViewManager, ViewManager>(manager, new FixReactImageManager()));
                continue;
            }

        }

        //replace
        for(Pair<ViewManager, ViewManager> pair: replacedManagers){
            Log.i("lvjie", "view manager is replace...");
            managers.remove(pair.first);
            managers.add(pair.second);
        }

        return managers;
    }

}
