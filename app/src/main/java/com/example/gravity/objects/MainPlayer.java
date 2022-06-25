package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.classes.GameManager;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.utilites.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    private AnimationFW mAnimMainPlayer;
    private AnimationFW mAnimMainPlayerBoost;
    private AnimationFW mAnimMainPlayerShieldsOn;
    private AnimationFW mAnimMainPlayerShieldsOnBoost;
    private AnimationFW mAnimExplosinPlayer;
    private CoreFW mCoreFW;

    private UtilTimerDelay mTimerShieldHit;
    private UtilTimerDelay mTimerGameOver;
    private UtilTimerDelay mTimerShieldsOn;

    private boolean mHitEnemy;
    private boolean mIsGameOver;
    static boolean sShieldsOn;

    private int mShieldsPlayer;
    private boolean mBoosting;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        init(coreFW, maxScreenX, maxScreenY, minScreenY);
        initAnimation();
    }

    private void init(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;

        mShieldsPlayer = 3;
        mBoosting = false;
        mHitEnemy = false;
        mIsGameOver = false;
        sShieldsOn = false;

        radius = UtilResourse.sSpritePlayer.get(0).getHeight()/2f;

        mTimerShieldHit = new UtilTimerDelay();
        mTimerGameOver = new UtilTimerDelay();
        mTimerShieldsOn = new UtilTimerDelay();

        this.mCoreFW = coreFW;
        this.maxScreenX = maxScreenX;
        //Точка отчета у картинок/спрайтов - это верхний левый угол. Минусуем высоту спрайта, чтобы картинка не провалилась под экран
        this.maxScreenY = maxScreenY -UtilResourse.sSpritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
    }

    public void initAnimation() {
        mAnimMainPlayer = new AnimationFW(speed, UtilResourse.sSpritePlayer.get(0),
                UtilResourse.sSpritePlayer.get(1),
                UtilResourse.sSpritePlayer.get(2),
                UtilResourse.sSpritePlayer.get(3));

        mAnimMainPlayerBoost = new AnimationFW(speed, UtilResourse.sSpritePlayerBoost.get(0),
                UtilResourse.sSpritePlayerBoost.get(1),
                UtilResourse.sSpritePlayerBoost.get(2),
                UtilResourse.sSpritePlayerBoost.get(3));

        mAnimMainPlayerShieldsOn = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsOn.get(0),
                UtilResourse.sSpritePlayerShieldsOn.get(1),
                UtilResourse.sSpritePlayerShieldsOn.get(2),
                UtilResourse.sSpritePlayerShieldsOn.get(3));

        mAnimMainPlayerShieldsOnBoost = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsOnBoost.get(0),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(1),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(2),
                UtilResourse.sSpritePlayerShieldsOnBoost.get(3));

        mAnimExplosinPlayer = new AnimationFW(speed, UtilResourse.sSpriteExplosinPlayer.get(0),
                UtilResourse.sSpriteExplosinPlayer.get(1),
                UtilResourse.sSpriteExplosinPlayer.get(2),
                UtilResourse.sSpriteExplosinPlayer.get(3));
    }

    public void update() {
        if (mCoreFW.getTouchListenerFW().getTouchDown(0,maxScreenY,maxScreenX,maxScreenY)) {
            startBoosting();
        }

        if (mCoreFW.getTouchListenerFW().getTouchUp(0,maxScreenY,maxScreenX,maxScreenY)) {
            stopBoosting();
        }

        if (mTimerShieldsOn.timerDelay(5)) {
            sShieldsOn = false;
        }

        updateBoosting();

        hitBox = new Rect(x,y,
                UtilResourse.sSpritePlayer.get(0).getWidth(),
                UtilResourse.sSpritePlayer.get(0).getHeight());

        if (mIsGameOver) {
            mAnimExplosinPlayer.runAnimation();
        }
    }

    private void updateBoosting() {
        if (mBoosting) {
            speed += 0.3;
        } else {
            speed -= 3;
        }

        if (speed>MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed<MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed+GRAVITY;
        if (y<minScreenY) {
            y = minScreenY;
        }
        if (y>maxScreenY) {
            y = maxScreenY;
        }

        if (mBoosting) {
            if (sShieldsOn) {
                mAnimMainPlayerShieldsOnBoost.runAnimation();
            } else {
                mAnimMainPlayerBoost.runAnimation();
            }
        } else {
            if (sShieldsOn) {
                mAnimMainPlayerShieldsOn.runAnimation();
            } else {
                mAnimMainPlayer.runAnimation();
            }
        }
    }

    private void stopBoosting() {
        mBoosting = false;
    }

    private void startBoosting() {
        mBoosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!mIsGameOver) {
            if (!mHitEnemy) {
                if (mBoosting) {
                    if (sShieldsOn) {
                        mAnimMainPlayerShieldsOnBoost.drawingAnimation(graphicsFW, x, y);
                    } else {
                        mAnimMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                    }
                } else {
                    if (sShieldsOn) {
                        mAnimMainPlayerShieldsOn.drawingAnimation(graphicsFW, x, y);
                    } else {
                        mAnimMainPlayer.drawingAnimation(graphicsFW, x, y);
                    }
                }
            } else {
                if (!sShieldsOn) {
                    graphicsFW.drawTexture(UtilResourse.sShieldHitEnemy, x, y);
                    mHitEnemy = !mTimerShieldHit.timerDelay(1);
                }
            }
        } else {
            mAnimExplosinPlayer.drawingAnimation(graphicsFW,x,y);
            if (mTimerGameOver.timerDelay(0.5)) {
                GameManager.sGameOver = true;
            }
        }
    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {
        return mShieldsPlayer;
    }

    public void hitEnemy() {
        if (!sShieldsOn) {
            mShieldsPlayer--;
            mHitEnemy = true;
            mTimerShieldHit.startTime();
            if (mShieldsPlayer <= 0) {
                UtilResourse.sExplode.play(1);
                mIsGameOver = true;
                mTimerGameOver.startTime();
            }
        }
    }

    public static boolean isShieldsOn() {
        return sShieldsOn;
    }

    public void hitProtector() {
        sShieldsOn = true;
        mTimerShieldsOn.startTime();
    }
}
