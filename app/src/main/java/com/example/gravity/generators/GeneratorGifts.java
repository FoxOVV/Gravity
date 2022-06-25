package com.example.gravity.generators;

import com.example.gravity.objects.MainPlayer;
import com.example.gravity.objects.Protector;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilites.UtilTimerDelay;

public class GeneratorGifts {
    private Protector mProtector;
    private UtilTimerDelay mTimerProtector;

    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenX;
    private int mMinScreenY;


    public GeneratorGifts(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenX = 0;
        this.mMinScreenY = minScreenY;

        mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenX, mMinScreenY);

        mTimerProtector = new UtilTimerDelay();
        mTimerProtector.startTime();
    }

    public void update(double speedPlayer) {
        if (mTimerProtector.timerDelay(8)&&(!MainPlayer.isShieldsOn())) {
            mProtector.update(speedPlayer);
            if (mProtector.getX()< mMinScreenX) {
                mProtector = null;
                mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenX, mMinScreenY);
                mTimerProtector.startTime();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        mProtector.drawing(graphicsFW);
    }

    public void hitProtectorWithPlayer() {
        mProtector = null;
        mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenX, mMinScreenY);
        mTimerProtector.startTime();
    }

    public Protector getProtector() {
        return mProtector;
    }

}
