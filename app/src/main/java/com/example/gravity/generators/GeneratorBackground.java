package com.example.gravity.generators;

import android.graphics.Color;

import com.example.gravity.objects.Star;
import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class GeneratorBackground {
    private final ArrayList<Star> mStarArrayList = new ArrayList<>();

    public GeneratorBackground(int sceneWidth, int sceneHeight, int minScreenY) {
        int starsSpeak = 100;

        for (int i = 0; i < starsSpeak; i++) {
            Star star = new Star(sceneWidth,sceneHeight,minScreenY);
            mStarArrayList.add(star);
        }
    }

    public void update (double speedPlayer) {
        for (int i = 0; i < mStarArrayList.size(); i++) {
            mStarArrayList.get(i).update(speedPlayer);
        }
    }

    public void drawing (GraphicsFW graphicsFW) {
        for (int i = 0; i < mStarArrayList.size(); i++) {
            graphicsFW.drawPixel(mStarArrayList.get(i).getX(), mStarArrayList.get(i).getY(), Color.WHITE);
        }
    }
}
