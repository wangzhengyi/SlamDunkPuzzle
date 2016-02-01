package com.android.slamdunk.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.android.slamdunk.R;

import java.util.HashMap;

/**
 * Created by wzy on 16-1-31.
 */
public class SoundUtil {
    public static final int MUSIC_HUA = 65537;
    public static final int MUSIC_SHAKE = 65538;
    public static final int MUSIC_BEGIN = 65539;
    public static final int MUSIC_WIN = 65540;

    public static Activity mActivity;
    private AudioManager mAudioManager;

    private int current;
    private HashMap<Integer, Integer> mHashMap = new HashMap<>();
    private SoundPool mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

    public SoundUtil(Context context) {
        mHashMap.put(MUSIC_HUA, mSoundPool.load(context, R.raw.musichua, 1));
        mHashMap.put(MUSIC_SHAKE, mSoundPool.load(context, R.raw.musicshake, 1));
        mHashMap.put(MUSIC_BEGIN, mSoundPool.load(context, R.raw.musicbegin, 1));
        mHashMap.put(MUSIC_WIN, mSoundPool.load(context, R.raw.win, 1));

        ((Activity)context).setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
    }

    public void playSound(int soundID, int loop) {
        float f = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        f = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) / f;
        mSoundPool.play(mHashMap.get(soundID), f, f, 1, loop, 1.0F);
    }

    public void release() {
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, current, 0);
        mSoundPool.release();
    }
}
