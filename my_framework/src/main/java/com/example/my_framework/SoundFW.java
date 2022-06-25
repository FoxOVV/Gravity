package com.example.my_framework;

import android.media.SoundPool;

public class SoundFW {
    private int mSound;
    private SoundPool mSoundPool;

    public SoundFW(int sound, SoundPool soundPool) {
        init(sound, soundPool);
    }

    private void init(int sound, SoundPool soundPool) {
        this.mSound = sound;
        this.mSoundPool = soundPool;
    }

    public void play(float volume) {
        mSoundPool.play(mSound,volume,volume,0,0,1);
    }

    public void stop() {
        mSoundPool.stop(mSound);
    }

    public void dispose() {
        mSoundPool.unload(mSound);
    }
}
