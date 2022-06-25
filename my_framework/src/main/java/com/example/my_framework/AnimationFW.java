package com.example.my_framework;

import android.graphics.Bitmap;

// Этот класс можно было реализовать в GraphicsFW;
public class AnimationFW {

    private double mSpeedAnimation;
    private int mDelayIndex;
    private int mCurrentFrame;
    private int mFramesCount;

    private Bitmap mSprite;
    private Bitmap mSprite1;
    private Bitmap mSprite2;
    private Bitmap mSprite3;
    private Bitmap mSprite4;

    public AnimationFW(double speedAnimation, Bitmap sprite1, Bitmap sprite2, Bitmap sprite3, Bitmap sprite4) {
        init(speedAnimation, sprite1, sprite2, sprite3, sprite4);
    }

    private void init(double speedAnimation, Bitmap sprite1, Bitmap sprite2, Bitmap sprite3, Bitmap sprite4) {
        mSprite = sprite1;

        this.mSpeedAnimation = speedAnimation;
        this.mSprite1 = sprite1;
        this.mSprite2 = sprite2;
        this.mSprite3 = sprite3;
        this.mSprite4 = sprite4;

        mFramesCount = 3;
        mDelayIndex = 0;
    }

    public void runAnimation() {
        mDelayIndex++;
        if (mDelayIndex > mSpeedAnimation) {
            mDelayIndex = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        if (mCurrentFrame == 0) {
            mSprite = mSprite1;
        }
        if (mCurrentFrame == 1) {
            mSprite = mSprite2;
        }
        if (mCurrentFrame == 2) {
            mSprite = mSprite3;
        }
        if (mCurrentFrame == 3) {
            mSprite = mSprite4;
        }

        mCurrentFrame++;
        if (mCurrentFrame > mFramesCount) {
            mCurrentFrame = 0;
        }
    }

    public void drawingAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(mSprite,x,y);
    }
}
