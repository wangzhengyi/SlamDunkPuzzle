package com.android.slamdunk.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.EventListener;

/**
 * Created by wzy on 16-1-31.
 */
public class LoadBitmapUtil {

    private Bitmap mBitmap;
    private final Context mContext;
    private SharedPreferences mShread;

    public LoadBitmapUtil(Context context) {
        this.mContext = context;
        this.mShread = context.getSharedPreferences("Loadbitmap",
                Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
    }

    public void loadBitmap(final ImageView paramImageView, final Dialog paramDialog,
                           final int paramInt1, final int paramInt2, OnFinishListener listener) {

    }

    public static interface OnFinishListener extends EventListener {
        public abstract void onFinish(boolean paramBoolean);
    }
}
