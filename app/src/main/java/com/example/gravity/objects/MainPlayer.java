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

    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animMainPlayerShieldsOn;
    AnimationFW animMainPlayerShieldsOnBoost;
    AnimationFW animExplosinPlayer;
    CoreFW coreFW;

    UtilTimerDelay timerShieldHit;
    UtilTimerDelay timerGameOver;
    UtilTimerDelay timerShieldsOn;

    boolean hitEnemy;
    boolean isGameOver;
    static boolean shieldsOn;

    private int shieldsPlayer;
    boolean boosting;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        isGameOver = false;
        shieldsOn = false;

        radius = UtilResourse.spritePlayer.get(0).getHeight()/2;

        timerShieldHit  = new UtilTimerDelay();
        timerGameOver   = new UtilTimerDelay();
        timerShieldsOn  = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        //Точка отчета у картинок/спрайтов - это верхний левый угол. Минусуем высоту спрайта, чтобы картинка не провалилась под экран
        this.maxScreenY = maxScreenY-UtilResourse.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;

        initAnimation();
    }

    public void initAnimation() {
        animMainPlayer = new AnimationFW(speed, UtilResourse.spritePlayer.get(0),
                UtilResourse.spritePlayer.get(1),
                UtilResourse.spritePlayer.get(2),
                UtilResourse.spritePlayer.get(3));

        animMainPlayerBoost = new AnimationFW(speed, UtilResourse.spritePlayerBoost.get(0),
                UtilResourse.spritePlayerBoost.get(1),
                UtilResourse.spritePlayerBoost.get(2),
                UtilResourse.spritePlayerBoost.get(3));

        animMainPlayerShieldsOn = new AnimationFW(speed, UtilResourse.spritePlayerShieldsOn.get(0),
                UtilResourse.spritePlayerShieldsOn.get(1),
                UtilResourse.spritePlayerShieldsOn.get(2),
                UtilResourse.spritePlayerShieldsOn.get(3));

        animMainPlayerShieldsOnBoost = new AnimationFW(speed, UtilResourse.spritePlayerShieldsOnBoost.get(0),
                UtilResourse.spritePlayerShieldsOnBoost.get(1),
                UtilResourse.spritePlayerShieldsOnBoost.get(2),
                UtilResourse.spritePlayerShieldsOnBoost.get(3));

        animExplosinPlayer = new AnimationFW(speed, UtilResourse.spriteExplosinPlayer.get(0),
                UtilResourse.spriteExplosinPlayer.get(1),
                UtilResourse.spriteExplosinPlayer.get(2),
                UtilResourse.spriteExplosinPlayer.get(3));
    }

    public void update() {
        if (coreFW.getTouchListenerFW().getTouchDown(0,maxScreenY,maxScreenX,maxScreenY)) {
            startBoosting();
        }
        if (coreFW.getTouchListenerFW().getTouchUp(0,maxScreenY,maxScreenX,maxScreenY)) {
            stopBoosting();
        }

        if (boosting) {
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

        if (timerShieldsOn.timerDelay(5)) {
            shieldsOn = false;
        }

        if (boosting) {
            if (shieldsOn) {
                animMainPlayerShieldsOnBoost.runAnimation();
            } else {
                animMainPlayerBoost.runAnimation();
            }
        } else {
            if (shieldsOn) {
                animMainPlayerShieldsOn.runAnimation();
            } else {
                animMainPlayer.runAnimation();
            }
        }

        hitBox = new Rect(x,y,
                UtilResourse.spritePlayer.get(0).getWidth(),
                UtilResourse.spritePlayer.get(0).getHeight());

        if (isGameOver) {
            animExplosinPlayer.runAnimation();
        }
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    if (shieldsOn) {
                        animMainPlayerShieldsOnBoost.drawingAnimation(graphicsFW, x, y);
                    } else {
                        animMainPlayerBoost.drawingAnimation(graphicsFW, x, y);
                    }
                } else {
                    if (shieldsOn) {
                        animMainPlayerShieldsOn.drawingAnimation(graphicsFW, x, y);
                    } else {
                        animMainPlayer.drawingAnimation(graphicsFW, x, y);
                    }
                }
            } else {
                if (!shieldsOn) {
                    graphicsFW.drawTexture(UtilResourse.shieldHitEnemy, x, y);
                    if (timerShieldHit.timerDelay(1)) {
                        hitEnemy = false;
                    }
                }
            }
        } else {
            animExplosinPlayer.drawingAnimation(graphicsFW,x,y);
            if (timerGameOver.timerDelay(0.5)) {
                GameManager.gameOver = true;
            }
        }
    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {
        return shieldsPlayer;
    }

    public void hitEnemy() {
        if (!shieldsOn) {
            shieldsPlayer--;
            hitEnemy = true;
            timerShieldHit.startTime();
            if (shieldsPlayer <= 0) {
                UtilResourse.explode.play(1);
                isGameOver = true;
                timerGameOver.startTime();
            }
        }
    }

    public static boolean isShieldsOn() {
        return shieldsOn;
    }

    public void hitProtector() {
        shieldsOn = true;
        timerShieldsOn.startTime();
    }
}
