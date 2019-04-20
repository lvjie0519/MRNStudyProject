package com.pilloxa.dfu;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by livy on 16/6/15.
 */
public class ServiceManager {
    static final String TAG = "ServiceManager";
    HashMap<Class<? extends Service>, Service> mServiceHasMap = new HashMap<Class<? extends Service>, Service>();

    private static ServiceManager __INSTANCE__ = null;

    private Context mContext;
    private Class<? extends Service> serviceClass;

    private ServiceManager() {
    }

    public synchronized static ServiceManager instance() {
        if (__INSTANCE__ == null) {
            __INSTANCE__ = new ServiceManager();
        }
        return __INSTANCE__;
    }

    boolean checkoutMainLoop() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void setParameter(Context context, Class<? extends Service> serviceClass) {
        this.mContext = context;
        this.serviceClass = serviceClass;
    }

    /**
     * 启动Service工具类,在UI线程中调用，必须调用stopService释放Service资源
     *
     * @param context
     * @param serviceClass
     * @param bundle
     */
    public void startService(Application application, Context context, Class<? extends Service> serviceClass, Bundle bundle) {
        if (!checkoutMainLoop())
            return;
        if (context == null || serviceClass == null) {
            return;
        }

        Log.d(TAG, "startService:" + serviceClass.getSimpleName());

        Service service = mServiceHasMap.get(serviceClass);
        if (service == null) {
            try {
                Log.d(TAG, "oncreateService:" + serviceClass.getSimpleName());
                service = serviceClass.newInstance();

                Method method = Service.class.getDeclaredMethod("attach", Context.class, Class.forName("android.app.ActivityThread")
                        , String.class,
                        IBinder.class, Application.class, Object.class);
                method.invoke(service, context, null, serviceClass.getSimpleName(), null, application, null);

                service.onCreate();
                Intent intent = new Intent();
                if (bundle != null)
                    intent.putExtras(bundle);
                Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
                service.onStartCommand(intent, 0, 0);

                mServiceHasMap.put(serviceClass, service);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent();
            if (bundle != null)
                intent.putExtras(bundle);
            Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
            service.onStartCommand(intent, 0, 0);
        }
    }

    /**
     * 释放Service资源类
     *
     * @param className
     */
    public void stopService(Class<? extends Service> className) {
        if (!checkoutMainLoop())
            return;
        if (className == null) {

            Set<Class<? extends Service>> keys = mServiceHasMap.keySet();
            for (Class<? extends Service> key : keys) {
                Service service = mServiceHasMap.get(key);
                if (service != null) {
                    Log.d(TAG, "onDestroy:" + service.getClass().getSimpleName());
                    service.onDestroy();
                }
            }
            mServiceHasMap.clear();
        } else {
            Service service = mServiceHasMap.get(className);
            if (service != null) {
                Log.d(TAG, "onDestroy:" + service.getClass().getSimpleName());
                service.onDestroy();
            }
            mServiceHasMap.remove(className);
        }
    }

    /**
     * 启动Service工具类,在UI线程中调用，必须调用stopService释放Service资源
     *
     * @param application
     * @param intent
     */
    public void startService(Application application, Intent intent) {
        if (!checkoutMainLoop())
            return;
        if (mContext == null || serviceClass == null) {
            Log.d(TAG,"为空");
            return;
        }

        Log.d(TAG, "startService:" + serviceClass.getSimpleName());

        Service service = mServiceHasMap.get(serviceClass);
        if (service == null) {
            try {
                Log.d(TAG, "oncreateService:" + serviceClass.getSimpleName());
                service = serviceClass.newInstance();

                Method method = Service.class.getDeclaredMethod("attach", Context.class, Class.forName("android.app.ActivityThread")
                        , String.class,
                        IBinder.class, Application.class, Object.class);
                method.invoke(service, mContext, null, serviceClass.getSimpleName(), null, application, null);

                service.onCreate();
                Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
                service.onStartCommand(intent, 0, 0);

                mServiceHasMap.put(serviceClass, service);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
            service.onStartCommand(intent, 0, 0);
        }
    }

    public void startService(Application application, Context context, Class<? extends Service> serviceClass, Intent intent) {
        if (!checkoutMainLoop())
            return;
        if (context == null || serviceClass == null) {
            return;
        }

        Log.d(TAG, "startService:" + serviceClass.getSimpleName());

        Service service = mServiceHasMap.get(serviceClass);
        if (service == null) {
            try {
                Log.d(TAG, "oncreateService:" + serviceClass.getSimpleName());
                service = serviceClass.newInstance();

                Method method = Service.class.getDeclaredMethod("attach", Context.class, Class.forName("android.app.ActivityThread")
                        , String.class,
                        IBinder.class, Application.class, Object.class);
                method.invoke(service, context, null, serviceClass.getSimpleName(), null, application, null);

                service.onCreate();
                Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
                service.onStartCommand(intent, 0, 0);

                mServiceHasMap.put(serviceClass, service);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "onStartCommand:" + serviceClass.getSimpleName());
            service.onStartCommand(intent, 0, 0);
        }
    }
}
