package com.example.my_framework.utilites;

public class UtilTimerDelay {
    private double mStartTime;
    private final double SECOND = 1000000000;

    public void startTime() {
        mStartTime = System.nanoTime()/SECOND;
    }

    public boolean timerDelay(double second) {
        double mNowTime = System.nanoTime() / SECOND;
        double mElapsedTime = mNowTime - mStartTime;
        return mElapsedTime > second;
    }
}
