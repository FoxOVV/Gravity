package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    //Другие классы фреймворка
    private LoopFW mLoopFW;
    private SceneFW mSceneFW;
    private GraphicsFW mGraphicsFW;
    private TouchListenerFW mTouchListenerFW;

    private SharedPreferences mSharedPreferences;

    private AudioFW mAudioFW;

    private boolean mIsPressedKeyBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Экран не тухнет и не уходит в блокировку пока жива эта активность
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        init();

        setContentView(mLoopFW);
    }

    private void init() {
        String SETTINGS = "settings";
        mSharedPreferences = getSharedPreferences(SETTINGS,MODE_PRIVATE);

        Point mSizeDisplay = new Point();

        //Экран устройства
        Display mDisplay = getWindowManager().getDefaultDisplay();
        mDisplay.getSize(mSizeDisplay);

        //Фрейм
        float FRAME_BUFFER_WIDTH = 800;
        float FRAME_BUFFER_HEIGHT = 600;
        Bitmap mFrameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);

        //Коэфициента отношения размера фрейма к размерам экрана
        float mSceneWidth = FRAME_BUFFER_WIDTH / mSizeDisplay.x;
        float mSceneHeight = FRAME_BUFFER_HEIGHT / mSizeDisplay.y;

        mAudioFW = new AudioFW(this);

        mLoopFW = new LoopFW(this, mFrameBuffer);
        mGraphicsFW = new GraphicsFW(getAssets(), mFrameBuffer);

        mTouchListenerFW = new TouchListenerFW(mLoopFW, mSceneWidth, mSceneHeight);

        mSceneFW = getStartScene();
        mIsPressedKeyBack = false;
    }

    public boolean isIsPressedKeyBack() {
        return mIsPressedKeyBack;
    }

    public void setIsPressedKeyBack(boolean mIsPressedKeyBack) {
        this.mIsPressedKeyBack = mIsPressedKeyBack;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            mIsPressedKeyBack = true;
            return true;
        }
        return false;
    }

    public CoreFW() {
    }

    public AudioFW getAudioFW() {
        return mAudioFW;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSceneFW.resume();
        mLoopFW.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSceneFW.pause();
        mLoopFW.stopGame();

        if (isFinishing()) {
            mSceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW () {
        return mGraphicsFW;
    }

    public void setScene(SceneFW sceneFW) {
        if (sceneFW==null) {
            throw new IllegalArgumentException("Не возможно загрузить сцену");
        }

        this.mSceneFW.pause();
        this.mSceneFW.dispose();

        sceneFW.resume();
        sceneFW.update();

        this.mSceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return mSceneFW;
    }

    public SceneFW getStartScene() {
        return mSceneFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return mTouchListenerFW;
    }

    public SharedPreferences getSharedPreferences() { return mSharedPreferences; }
}