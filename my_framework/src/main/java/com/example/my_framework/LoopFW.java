package com.example.my_framework;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class LoopFW extends SurfaceView implements Runnable{

    //Нужно обновлять 60 раз в секунду
    private final float FPS = 60;
    //Ярд наносекунд в 1 секунде
    private final float SECOND = 1000000000;
    //Каждые сколько наносекунд необходимо обновлять игру
    protected final float UPDATE_TIME = SECOND/FPS;

    private CoreFW mCoreFW;
    private Bitmap mFrameBuffer;
    private SurfaceHolder mSurfaceHolder;

    private Thread mGameThread = null;
    private boolean mRunning = false;

    private Canvas mCanvas;
    private Rect mRect;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer) {
        super(coreFW);
        init(coreFW, frameBuffer);
    }

    private void init(CoreFW coreFW, Bitmap frameBuffer) {
        this.mCoreFW = coreFW;
        this.mFrameBuffer = frameBuffer;
        this.mSurfaceHolder = getHolder();

        mRect = new Rect();
        mCanvas = new Canvas();
    }

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;

        while(mRunning) {
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
        mCoreFW.getCurrentScene().update();
    }

    private void drawingGame() {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.getClipBounds(mRect);
            mCanvas.drawBitmap(mFrameBuffer,null, mRect,null);
            mCoreFW.getCurrentScene().drawing();
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void startGame() {
        if(mRunning) {
            return;
        }
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public void stopGame() {
        if (!mRunning){
            return;
        }
        mRunning = false;

        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
