package com.example.my_framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;

public class LoopFW  extends SurfaceView implements Runnable{

    //Нужно обновлять 60 раз в секунду
    private final float FPS = 60;
    //Ярд наносекунд в 1 секунде
    private final float SECOND = 1000000000;
    //Каждые сколько наносекунд необходимо обновлять игру
    private final float UPDATE_TIME = SECOND/FPS;

    CoreFW coreFW;
    Bitmap frameBuffer;
    SurfaceHolder surfaceHolder;

    Thread gameThread = null;
    private boolean running = false;

    Canvas canvas;
    Rect rect;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer) {
        super(coreFW);
        this.coreFW = coreFW;
        this.frameBuffer = frameBuffer;
        this.surfaceHolder = getHolder();

        rect = new Rect();
        canvas = new Canvas();
    }

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;

        while(running) {
            float nowTime = System.nanoTime();
            float elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            delta += elapsedTime/UPDATE_TIME;

            if (delta>1) {
                updateGame();
                drawingGame();
                delta--;
            }
        }
    }

    private void updateGame() {
        coreFW.getCurrentScene().update();
    }

    private void drawingGame() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(frameBuffer,null,rect,null);
            coreFW.getCurrentScene().drawing();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void startGame() {
        if(running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame() {
        if (!running){
            return;
        }
        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
