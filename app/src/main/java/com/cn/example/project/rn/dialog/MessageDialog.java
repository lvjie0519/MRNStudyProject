package com.cn.example.project.rn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cn.example.project.R;

public class MessageDialog extends Dialog{

    private View mRootView;
    private TextView mTvTtile;
    private TextView mTvMessage;
    private Button mBtnCancel;
    private Button mBtnOk;

    private DialogOnClick mDialogOnClick;

    public MessageDialog(Context context) {
        this(context, R.style.DialogStyle);
    }

    public MessageDialog(Context context, int themeResId) {
        super(context, themeResId);

        initDialog();
        initView();
    }

    private void initDialog(){

        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);// 显示在底部
        dialogWindow.setWindowAnimations(R.style.DialogAnimation); // 添加动画
        this.mRootView = getLayoutInflater().inflate(R.layout.dialog_message, null);
        this.setContentView(this.mRootView);

        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (this.getWindow().getWindowManager().getDefaultDisplay().getWidth()*0.8);
        this.getWindow().setAttributes(lp);
    }

    private void initView(){
        mTvTtile = mRootView.findViewById(R.id.tv_title);
        mTvMessage = mRootView.findViewById(R.id.tv_message);
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

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(mDialogOnClick != null){
                    mDialogOnClick.onDialogDismiss();
                }
            }
        });
    }

    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTvTtile.setText(title);
        }
    }

    public void setMessage(String msg){
        if(!TextUtils.isEmpty(msg)){
            mTvMessage.setText(msg);
        }
    }

    public void setDialogOnClick(DialogOnClick mDialogOnClick) {
        this.mDialogOnClick = mDialogOnClick;
    }

    public interface DialogOnClick{
        void onCancelClick();
        void onConfirmClick();
        void onDialogDismiss();
    }


}
