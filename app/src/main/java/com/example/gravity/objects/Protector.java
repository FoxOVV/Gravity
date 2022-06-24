package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.Main;
import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilites.UtilRandomFW;
import com.example.my_framework.utilites.UtilTimerDelay;

public class Protector extends ObjectFW {

    AnimationFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spriteEnemy.get(0).getHeight();
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        this.x = maxScreenX;
        this.y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.spriteProtector.get(0).getHeight()/4;

        hitBox = new Rect(x,y,
                UtilResourse.spriteProtector.get(0).getWidth(),
                UtilResourse.spriteProtector.get(0).getHeight());

        speed = UtilRandomFW.getGap(1,6);
        animProtector = new AnimationFW(10, UtilResourse.spriteProtector.get(0),
                UtilResourse.spriteProtector.get(1),
                UtilResourse.spriteProtector.get(2),
                UtilResourse.spriteProtector.get(3));
    }

    public void update (double speedPlayer) {
        x -= speedPlayer;
        x -= speed;

        if (x<minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }

        animProtector.runAnimation();

        hitBox = new Rect(x,y,
                UtilResourse.spriteProtector.get(0).getWidth(),
                UtilResourse.spriteProtector.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        animProtector.drawingAnimation(graphicsFW, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
