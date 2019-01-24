package com.cn.example.project;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import java.util.UUID;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BleManager {

    private static final int STOP_LESCAN = 100;

    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothGatt mBluetoothGatt;
    private boolean isScanning = false;

    private BleManager(Context context) {
        this.mContext = context.getApplicationContext();

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){
            return;
        }

        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);  //BluetoothManager只在android4.3以上有
        if (bluetoothManager == null) {
            Log.e("lvjie", "Unable to initialize BleManager.");
            return;
        }
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }


    /**
     * 既然获得了BluetoothAdapter对象，那么接下来就可以搜索ble设备了
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void startLeScan() {
        if (mBluetoothAdapter == null) {
            return;
        }

        if (isScanning) {
            return;
        }
        isScanning = true;

        mBluetoothAdapter.startLeScan(mLeScanCallback);   //此mLeScanCallback为回调函数

        mHandler.sendEmptyMessageDelayed(STOP_LESCAN, 10000);  //这个搜索10秒，如果搜索不到则停止搜索
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int arg1, byte[] arg2) {
            Log.i("lvjie", "onLeScan() DeviceName------>"+device.getName());  //在这里可通过device这个对象来获取到搜索到的ble设备名称和一些相关信息
            if(device.getName() == null){
                return;
            }
            if (device.getName().contains("Ble_Name")) {    //判断是否搜索到你需要的ble设备
                Log.i("lvjie", "onLeScan() DeviceAddress------>"+device.getAddress());
                mBluetoothDevice = device;   //获取到周边设备
//                stopLeScan();   //1、当找到对应的设备后，立即停止扫描；2、不要循环搜索设备，为每次搜索设置适合的时间限制。避免设备不在可用范围的时候持续不停扫描，消耗电量。

                connect();  //连接
            }
        }
    };

    private Handler mHandler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case STOP_LESCAN:

                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                    broadcastUpdate(Config.ACTION_GATT_DISCONNECTED);
                    isScanning = false;
                    Log.i("lvjie", "Scan time is up");
                    break;
            }
        }
    };

    public boolean connect() {
        if (mBluetoothDevice == null) {
            Log.i("lvjie", "BluetoothDevice is null.");
            return false;
        }

        //两个设备通过BLE通信，首先需要建立GATT连接。这里我们讲的是Android设备作为client端，连接GATT Server

        mBluetoothGatt = mBluetoothDevice.connectGatt(mContext, false, mGattCallback);  //mGattCallback为回调接口

        if (mBluetoothGatt != null) {

            if (mBluetoothGatt.connect()) {
                Log.d("lvjie", "Connect succeed.");
                return true;
            } else {
                Log.d("lvjie", "Connect fail.");
                return false;
            }
        } else {
            Log.d("lvjie", "BluetoothGatt null.");
            return false;
        }
    }

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices(); //执行到这里其实蓝牙已经连接成功了

                Log.d("lvjie", "Connected to GATT server.");
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                if(mBluetoothDevice != null){
                    Log.d("lvjie", "重新连接");
                    connect();
                }else{
                    Log.d("lvjie", "Disconnected from GATT server.");
                }
            }
        }

        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d("lvjie",  "onServicesDiscovered");
                getBatteryLevel();  //获取电量
            } else {
                Log.d("lvjie",  "onServicesDiscovered status------>" + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//            Log.d("lvjie",  "onCharacteristicRead------>" + Utils.bytesToHexString(characteristic.getValue()));

            //判断UUID是否相等
//            if (Values.UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS.equals(characteristic.getUuid().toString())) {
//            }


        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//            Log.d("lvjie",  "onCharacteristicChanged------>" + Utils.bytesToHexString(characteristic.getValue()));

            //判断UUID是否相等
//            if (Values.UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS.equals(characteristic.getUuid().toString())) {
//            }
        }

        //接受Characteristic被写的通知,收到蓝牙模块的数据后会触发onCharacteristicWrite
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//            TLog.d(TAG,"status = " + status);
//            TLog.d(TAG, "onCharacteristicWrite------>" + Utils.bytesToHexString(characteristic.getValue()));
        }
    };


    public void getBatteryLevel() {
//        BluetoothGattCharacteristic batteryLevelGattC = getCharcteristic(
//                Values.UUID_KEY_BATTERY_LEVEL_SERVICE, Values.UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS);
//        if (batteryLevelGattC != null) {
//            readCharacteristic(batteryLevelGattC);
//            setCharacteristicNotification(batteryLevelGattC, true); //设置当指定characteristic值变化时，发出通知。
//        }
    }

    //  a.获取服务
    public BluetoothGattService getService(UUID uuid) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.d("lvjie", "BluetoothAdapter not initialized");
            return null;
        }
        return mBluetoothGatt.getService(uuid);
    }

    //b.获取特征
    private BluetoothGattCharacteristic getCharcteristic(String serviceUUID, String characteristicUUID) {

        //得到服务对象
        BluetoothGattService service = getService(UUID.fromString(serviceUUID));  //调用上面获取服务的方法

        if (service == null) {
            Log.d("lvjie", "Can not find 'BluetoothGattService'");
            return null;
        }

        //得到此服务结点下Characteristic对象
        final BluetoothGattCharacteristic gattCharacteristic = service.getCharacteristic(UUID.fromString(characteristicUUID));
        if (gattCharacteristic != null) {
            return gattCharacteristic;
        } else {
            Log.d("lvjie", "Can not find 'BluetoothGattCharacteristic'");
            return null;
        }
    }

    //获取数据
    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.d("lvjie", "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }

    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.d("lvjie",  "BluetoothAdapter not initialized");
            return false;
        }
        return mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
    }

//    public void write(byte[] data) {   //一般都是传byte
//        //得到可写入的characteristic Utils.isAIRPLANE(mContext) &&
//        if(!mBleManager.isEnabled()){
//            TLog.e(TAG, "writeCharacteristic 开启飞行模式");
//            //closeBluetoothGatt();
//            isGattConnected = false;
//            broadcastUpdate(Config.ACTION_GATT_DISCONNECTED);
//            return;
//        }
//        BluetoothGattCharacteristic writeCharacteristic = getCharcteristic(Values.UUID_KEY_SERVICE, Values.UUID_KEY_WRITE);  //这个UUID都是根据协议号的UUID
//        if (writeCharacteristic == null) {
//            TLog.e(TAG, "Write failed. GattCharacteristic is null.");
//            return;
//        }
//        writeCharacteristic.setValue(data); //为characteristic赋值
//        writeCharacteristicWrite(writeCharacteristic);
//
//    }

    public void writeCharacteristicWrite(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e("lvjie", "BluetoothAdapter not initialized");
            return;
        }
        Log.e("lvjie",  "BluetoothAdapter 写入数据");
        boolean isBoolean = false;
        isBoolean = mBluetoothGatt.writeCharacteristic(characteristic);
        Log.e("lvjie",  "BluetoothAdapter_writeCharacteristic = " +isBoolean);  //如果isBoolean返回的是true则写入成功
    }

}
