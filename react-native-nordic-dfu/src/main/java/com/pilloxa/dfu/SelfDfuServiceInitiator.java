package com.pilloxa.dfu;
//package com.pilloxa.dfu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.dfu.DfuServiceController;
import no.nordicsemi.android.dfu.DfuServiceInitiator;

public class SelfDfuServiceInitiator extends DfuServiceInitiator{


    public SelfDfuServiceInitiator(String deviceAddress) {
        super(deviceAddress);
    }

    @Override
    public DfuServiceController start(final Context context, final Class<? extends DfuBaseService> service) {

        Log.i("lvjie", "SelfDfuServiceInitiator-->start()...");

        if (getFileType() == -1) {
            throw new UnsupportedOperationException("You must specify the firmware file before starting the service");
        } else {
            final Intent intent = new Intent(context, service);
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", getDeviceAddress());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME", getDeviceName());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION", isDisableNotification());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE", isStartAsForegroundService());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE", getMimeType());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE", getFileType());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI", getFileUri());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH", getFilePath());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID", getFileResId());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI", getInitFileUri());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH", getInitFilePath());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID", getInitFileResId());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND", isKeepBond());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND", isRestoreBond());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU", isForceDfu());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME", isDisableResume());
            if (getMtu() > 0) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_MTU", getMtu());
            }

            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU", getCurrentMtu());
            intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU", isEnableUnsafeExperimentalButtonlessDfu());
            if (getPacketReceiptNotificationsEnabled() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED", getPacketReceiptNotificationsEnabled());
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE", getNumberOfPackets());
            }

            if (getLegacyDfuUuids() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU", getLegacyDfuUuids());
            }

            if (getSecureDfuUuids() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU", getSecureDfuUuids());
            }

            if (getExperimentalButtonlessDfuUuids() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU", getExperimentalButtonlessDfuUuids());
            }

            if (getButtonlessDfuWithoutBondSharingUuids() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING", getButtonlessDfuWithoutBondSharingUuids());
            }

            if (getButtonlessDfuWithBondSharingUuids() != null) {
                intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING", getButtonlessDfuWithBondSharingUuids());
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isStartAsForegroundService()) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }

//            intent.setPackage()

//            if(context instanceof Activity){
//                final Activity activity = (Activity) context;
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("lvjie", "service run...");
//                        ServiceManager.instance().setParameter(context, service);
//                        ServiceManager.instance().startService(activity.getApplication(), intent);
//                    }
//                });
//            }
            return new DfuServiceController(context);
        }
    }
}


//public class SelfDfuServiceInitiator{
//
//}
