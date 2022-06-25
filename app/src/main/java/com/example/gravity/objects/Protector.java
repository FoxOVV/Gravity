package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilites.UtilRandomFW;

public class Protector extends ObjectFW {

    private AnimationFW mAnimProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenX, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenX, minScreenY);
        initAnimation();
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenX, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight();
        this.minScreenX = minScreenX;
        this.minScreenY = minScreenY;

        this.x = maxScreenX;
        this.y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.sSpriteProtector.get(0).getHeight()/4f;

        hitBox = new Rect(x,y,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());

        speed = UtilRandomFW.getGap(1,6);
    }

    private void initAnimation() {
        mAnimProtector = new AnimationFW(10, UtilResourse.sSpriteProtector.get(0),
                UtilResourse.sSpriteProtector.get(1),
                UtilResourse.sSpriteProtector.get(2),
                UtilResourse.sSpriteProtector.get(3));
    }

    public void update (double speedPlayer) {
        x -= speedPlayer;
        x -= speed;

        if (x<minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }

        mAnimProtector.runAnimation();

        hitBox = new Rect(x,y,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW) {
        mAnimProtector.drawingAnimation(graphicsFW, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
