package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.utilites.SettingsGame;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class TopDistance extends SceneFW {

    private final String[] mNumbers = new String[5];

    public TopDistance(CoreFW coreFW) {
        super(coreFW);

        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            this.mNumbers[i] = " " + (i+1) + "." + SettingsGame.distance[i];
        }
    }

    @Override
    public void update() {
        if (coreFW.isIsPressedKeyBack()) {
            coreFW.setIsPressedKeyBack(false);
            coreFW.setScene(new MainMenuScene(coreFW));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_distance_topDistance), 120, 200, Color.GREEN, 40, null);
        graphicsFW.drawText(String.valueOf(mNumbers[0]), 120, 250, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(mNumbers[1]), 120, 290, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(mNumbers[2]), 120, 330, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(mNumbers[3]), 120, 370, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(mNumbers[4]), 120, 410, Color.GREEN, 35, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
