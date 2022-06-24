package com.example.gravity.generators;

import com.example.gravity.objects.MainPlayer;
import com.example.gravity.objects.Protector;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilites.UtilTimerDelay;

public class GeneratorGifts {
    Protector protector;
    UtilTimerDelay timerProtector;

    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;


    public GeneratorGifts(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        protector = new Protector(maxScreenX,maxScreenY,minScreenX,minScreenY);

        timerProtector = new UtilTimerDelay();
        timerProtector.startTime();
    }

    public void update(double speedPlayer) {
        if (timerProtector.timerDelay(8)&&(!MainPlayer.isShieldsOn())) {
            protector.update(speedPlayer);
            if (protector.getX()<minScreenX) {
                protector = null;
                protector = new Protector(maxScreenX,maxScreenY,minScreenX,minScreenY);
                timerProtector.startTime();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        protector.drawing(graphicsFW);
    }

    public Protector getProtector() {
        return protector;
    }

    public void hitProtectorWithPlayer() {
        protector = null;
        protector = new Protector(maxScreenX,maxScreenY,minScreenX,minScreenY);
        timerProtector.startTime();
    }
}
