package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class CoreFW extends AppCompatActivity {
    //Экран устройства
    private Display display;
    private Point sizeDisplay;

    //Фрейм
    private Bitmap frameBuffer;
    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;

    //Коэфициента отношения размера фрейма к размерам экрана
    private float sceneWidth;
    private float sceneHeight;

    //Другие классы фреймворка
    private LoopFW loopFW;
    private SceneFW sceneFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;

    //Статусы работы фрейма
    private boolean stateOnPause;
    private boolean stateOnResume;

    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "settings";

    private AudioFW audioFW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(SETTINGS,MODE_PRIVATE);

        //Экран не тухнет и не уходит в блокировку пока жива эта активность
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sizeDisplay = new Point();

        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        frameBuffer = Bitmap.createBitmap((int)FRAME_BUFFER_WIDTH,(int)FRAME_BUFFER_HEIGHT,Bitmap.Config.ARGB_8888);

        sceneWidth  = FRAME_BUFFER_WIDTH/sizeDisplay.x;
        sceneHeight = FRAME_BUFFER_HEIGHT/sizeDisplay.y;

        audioFW = new AudioFW(this);

        loopFW      = new LoopFW(this, frameBuffer);
        graphicsFW  = new GraphicsFW(getAssets(), frameBuffer);

        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();

        stateOnPause = false;
        stateOnResume = false;

        setContentView(loopFW);
    }

    public CoreFW() {
    }

    public AudioFW getAudioFW() {
        return audioFW;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateOnPause = true;

        sceneFW.pause();
        loopFW.stopGame();

        if (isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW () {
        return graphicsFW;
    }

    public void setScene(SceneFW sceneFW) {
        if (sceneFW==null) {
            throw new IllegalArgumentException("Не возможно загрузить сцену");
        }

        this.sceneFW.pause();
        this.sceneFW.dispose();

        sceneFW.resume();
        sceneFW.update();

        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        return sceneFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return touchListenerFW;
    }

    public SharedPreferences getSharedPreferences() { return sharedPreferences; }
}