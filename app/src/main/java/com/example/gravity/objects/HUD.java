package com.example.gravity.objects;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;

public class HUD {
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    CoreFW coreFW;

    private final int HEIGHT_HUD = 50;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer) {
        this.passedDistance         = passedDistance;
        this.currentSpeedPlayer     = currentSpeedPlayer;
        this.currentShieldsPlayer   = currentShieldsPlayer;
    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0,HEIGHT_HUD,graphicsFW.getWidthFrameBuffer(),HEIGHT_HUD,Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_passedDistance)+":"+passedDistance,
                10,30,Color.GREEN,25,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentSpeedPlayer)+":"+currentSpeedPlayer,
                350,30,Color.GREEN,25,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentShieldsPlayer)+":"+currentShieldsPlayer,
                700,30,Color.GREEN,25,null);
    }

    public int getHEIGHT_HUD() {
        return HEIGHT_HUD;
    }
}
