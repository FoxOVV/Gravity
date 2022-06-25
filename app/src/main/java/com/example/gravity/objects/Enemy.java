package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilites.UtilRandomFW;

public class Enemy extends ObjectFW {

    private AnimationFW mAnimEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnemy(enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight();
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        this.x = maxScreenX;
        this.y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.sSpriteEnemy.get(0).getHeight() / 4f;
    }

    private void initTypeEnemy(int enemyType) {
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(1,6);
                mAnimEnemy = new AnimationFW(10, UtilResourse.sSpriteEnemy.get(0),
                        UtilResourse.sSpriteEnemy.get(1),
                        UtilResourse.sSpriteEnemy.get(2),
                        UtilResourse.sSpriteEnemy.get(3));
                break;
            case 2:
                speed = UtilRandomFW.getGap(4,9);
                break;
        }
    }

    public void update (double speedPlayer) {
        x -= speedPlayer;
        x -= speed;
        if (x<minScreenX) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }

        mAnimEnemy.runAnimation();

        hitBox = new Rect(x,y,
                UtilResourse.sSpriteEnemy.get(0).getWidth(),
                UtilResourse.sSpriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        mAnimEnemy.drawingAnimation(graphicsFW, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
