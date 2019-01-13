package com.cn.example.project.rn.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cn.example.project.R;

public class MessageDialog extends DialogFragment {

    private View mRootView;
    private Button mBtnCancel;
    private Button mBtnOk;

    private DialogOnClick mDialogOnClick;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.dialog_message, container, false);
        initDialog();
        initView();

        return mRootView;
    }

    private void initDialog(){

        Dialog dialog = getDialog();
        if(dialog != null){
            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);// 显示在底部
            dialogWindow.setWindowAnimations(R.style.DialogAnimation); // 添加动画

            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);       // 点击其他区域，dialog消失

//            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.width = this.mScreenWidth; //设置宽度
//            this.getWindow().setAttributes(lp);
        }


    }

    private void initView(){
        mBtnCancel = mRootView.findViewById(R.id.btn_cancel);
        mBtnOk = mRootView.findViewById(R.id.btn_ok);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialogOnClick != null){
                    mDialogOnClick.onConfirmClick();
                }
                dismiss();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialogOnClick != null){
                    mDialogOnClick.onCancelClick();
                }
                dismiss();
            }
        });
    }

    public void setDialogOnClick(DialogOnClick mDialogOnClick) {
        this.mDialogOnClick = mDialogOnClick;
    }

    public interface DialogOnClick{
        void onCancelClick();
        void onConfirmClick();
    }


}
