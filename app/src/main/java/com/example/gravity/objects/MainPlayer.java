package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.classes.GameManager;
import com.example.gravity.utilites.SettingsGame;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.utilites.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    private AnimationFW mAnimMainPlayer;
    private AnimationFW mAnimMainPlayerBoost;
    private AnimationFW mAnimMainPlayerShieldsOn;
    private AnimationFW mAnimMainPlayerShieldsOnBoost;
    private AnimationFW mAnimExplosionPlayer;
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

        radius = UtilResource.sSpritePlayer.get(0).getHeight()/2f;

        mTimerShieldHit = new UtilTimerDelay();
        mTimerGameOver = new UtilTimerDelay();
        mTimerShieldsOn = new UtilTimerDelay();

        this.mCoreFW = coreFW;
        this.maxScreenX = maxScreenX;
        //Точка отчета у картинок/спрайтов - это верхний левый угол. Минусуем высоту спрайта, чтобы картинка не провалилась под экран
        this.maxScreenY = maxScreenY - UtilResource.sSpritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
    }

    public void initAnimation() {
        mAnimMainPlayer = new AnimationFW(speed, UtilResource.sSpritePlayer.get(0),
                UtilResource.sSpritePlayer.get(1),
                UtilResource.sSpritePlayer.get(2),
                UtilResource.sSpritePlayer.get(3));

        mAnimMainPlayerBoost = new AnimationFW(speed, UtilResource.sSpritePlayerBoost.get(0),
                UtilResource.sSpritePlayerBoost.get(1),
                UtilResource.sSpritePlayerBoost.get(2),
                UtilResource.sSpritePlayerBoost.get(3));

        mAnimMainPlayerShieldsOn = new AnimationFW(speed, UtilResource.sSpritePlayerShieldsOn.get(0),
                UtilResource.sSpritePlayerShieldsOn.get(1),
                UtilResource.sSpritePlayerShieldsOn.get(2),
                UtilResource.sSpritePlayerShieldsOn.get(3));

        mAnimMainPlayerShieldsOnBoost = new AnimationFW(speed, UtilResource.sSpritePlayerShieldsOnBoost.get(0),
                UtilResource.sSpritePlayerShieldsOnBoost.get(1),
                UtilResource.sSpritePlayerShieldsOnBoost.get(2),
                UtilResource.sSpritePlayerShieldsOnBoost.get(3));

        mAnimExplosionPlayer = new AnimationFW(speed, UtilResource.sSpriteExplosionPlayer.get(0),
                UtilResource.sSpriteExplosionPlayer.get(1),
                UtilResource.sSpriteExplosionPlayer.get(2),
                UtilResource.sSpriteExplosionPlayer.get(3));
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
                UtilResource.sSpritePlayer.get(0).getWidth(),
                UtilResource.sSpritePlayer.get(0).getHeight());

        if (mIsGameOver) {
            mAnimExplosionPlayer.runAnimation();
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
                    graphicsFW.drawTexture(UtilResource.sShieldHitEnemy, x, y);
                    mHitEnemy = !mTimerShieldHit.timerDelay(1);
                }
            }
        } else {
            mAnimExplosionPlayer.drawingAnimation(graphicsFW,x,y);
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
                if (SettingsGame.sSoundOn) {
                    UtilResource.sExplode.play(1);
                }
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
