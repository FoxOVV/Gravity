package com.example.my_framework;

public abstract class SceneFW {
    protected final GraphicsFW graphicsFW;
    protected final CoreFW coreFW;

    protected final int sceneWidth;
    protected final int sceneHeight;

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
