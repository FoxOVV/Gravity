package com.example.my_framework;

import android.util.Log;

import java.util.Date;

public abstract class SceneFW {
    public GraphicsFW graphicsFW;
    public CoreFW coreFW;

    public int sceneWidth;
    public int sceneHeight;

    public SceneFW(CoreFW coreFW) {
        this.coreFW = coreFW;
        sceneWidth  = coreFW.getGraphicsFW().getWidthFrameBuffer();
        sceneHeight = coreFW.getGraphicsFW().getHeightFrameBuffer();
        graphicsFW  = coreFW.getGraphicsFW();
    }

    public abstract void update();
    public abstract void drawing();
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
