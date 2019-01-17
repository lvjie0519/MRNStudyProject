package com.cn.example.project.rn.viewmanager.hostview;

import android.content.Context;
import android.view.View;

import com.cn.example.project.rn.dialog.MessageDialog;

public class MessageDialogHostView extends View {

    private MessageDialog mMessageDialog;
    private String mTitile;
    private String mMessage;
    private boolean mVisible;

    private MessageDialog.DialogOnClick mOnClick;

    public MessageDialogHostView(Context context) {
        super(context);

    }

    public void setTitile(String mTitile) {
        this.mTitile = mTitile;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public void setVisible(boolean mVisible) {
        this.mVisible = mVisible;
    }

    public void setOnClick(MessageDialog.DialogOnClick mOnClick) {
        this.mOnClick = mOnClick;
    }

    public void showOrUpdateDialog(){

        if(!mVisible){
            // 可以稍作一个优化，当RN不需要显示dialog，则不做dialog的初始化或让dialog  dismiss
            if(mMessageDialog != null && mMessageDialog.isShowing()){
                mMessageDialog.dismiss();
            }
            return;
        }

        if(mMessageDialog == null){
            mMessageDialog = new MessageDialog(this.getContext());
        }
        mMessageDialog.setDialogOnClick(mOnClick);
        mMessageDialog.setMessage(mMessage);
        mMessageDialog.setTitle(mTitile);
        if(!mMessageDialog.isShowing()){
            mMessageDialog.show();
        }

    }

    public void onDropInstance(){
        if(mMessageDialog != null && mMessageDialog.isShowing()){
            mMessageDialog.dismiss();
        }
        mMessageDialog = null;
    }



}
