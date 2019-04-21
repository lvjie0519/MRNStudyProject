package com.cn.example.project.rn.fixbug.imagview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageView;

import javax.annotation.Nullable;

public class FixReactImageView  extends ReactImageView {

    public FixReactImageView(Context context, AbstractDraweeControllerBuilder draweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object callerContext) {
        super(context, draweeControllerBuilder, globalImageLoadListener, callerContext);
    }

    boolean mFiltered = false;

    @Override
    protected void onDraw(Canvas canvas) {

        if(mFiltered){
            getDrawable().setFilterBitmap(false);
            mFiltered = false;
        }
        super.onDraw(canvas);
    }

}
