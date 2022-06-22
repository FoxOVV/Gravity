package com.example.gravity.objects;

import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilites.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        this.speed = UtilRandomFW.getCasualNumber(3);

        this.x = UtilRandomFW.getCasualNumber(maxScreenX);
        this.y = UtilRandomFW.getGap(minScreenY,maxScreenY);
    }

    public void update (double speedPlayer) {
        x -= speedPlayer;
        x -= speed;
        if (x<0) {
            x = UtilRandomFW.getCasualNumber(maxScreenX);
            y = UtilRandomFW.getGap(minScreenY,maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
