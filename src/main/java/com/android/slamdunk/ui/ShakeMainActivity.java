package com.android.slamdunk.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

import com.android.slamdunk.R;
import com.android.slamdunk.utils.GameUtil;
import com.android.slamdunk.utils.LoadBitmapUtil;
import com.android.slamdunk.utils.ShakeDetector;
import com.android.slamdunk.utils.SoundUtil;

public class ShakeMainActivity extends Activity {

    private Bitmap mDesktopBitmap;
    private Bitmap mUpBitmap;
    private Bitmap mDownBitmap;
    private Bitmap mLeftBitmap;
    private Bitmap mRightBitmap;
    private LoadBitmapUtil mLoadBitmapUtil;
    private SoundUtil mSoundUtil;
    private GameUtil mGameUtil;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_shake_main);

        mDesktopBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slamdunk);
        mUpBitmap = Bitmap.createBitmap(mDesktopBitmap, 0, 0, mDesktopBitmap.getWidth(), mDesktopBitmap.getHeight() / 2);
        mDownBitmap = Bitmap.createBitmap(mDesktopBitmap, 0, mDesktopBitmap.getHeight() / 2, mDesktopBitmap.getWidth(), mDesktopBitmap.getHeight() / 2);
        mLeftBitmap = Bitmap.createBitmap(mDesktopBitmap, 0, 0, mDesktopBitmap.getWidth() / 2, mDesktopBitmap.getHeight());
        mRightBitmap = Bitmap.createBitmap(mDesktopBitmap, mDesktopBitmap.getWidth() / 2, 0, mDesktopBitmap.getWidth() / 2, mDesktopBitmap.getHeight());

        mLoadBitmapUtil = new LoadBitmapUtil(this);
        mSoundUtil = new SoundUtil(this);
        mGameUtil = new GameUtil(this);

        for (mShakeDetector = new ShakeDetector(this, 800);;mShakeDetector = new ShakeDetector(this, 1290)) {
            LayoutChanged(false);
        }
    }

    private void LayoutChanged(boolean changed) {
        if (!changed) {
            setContentView(R.layout.puzzle_landscape);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }
}
