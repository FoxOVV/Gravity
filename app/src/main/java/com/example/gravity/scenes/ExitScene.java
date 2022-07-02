package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.utilites.SettingsGame;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class ExitScene extends SceneFW {
    public ExitScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if (coreFW.getTouchListenerFW().getTouchUp(300,250,100,35)) {
            coreFW.finish();
        }
        if (coreFW.getTouchListenerFW().getTouchUp(400,250,100,35)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText("Вы точно хотите выйти?", 150, 200, Color.BLUE, 45, UtilResource.mainMenuFont);
        graphicsFW.drawText("ДА", 300, 250, Color.BLUE, 35, UtilResource.mainMenuFont);
        graphicsFW.drawText("НЕТ", 400, 250, Color.BLUE, 35, UtilResource.mainMenuFont);
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
