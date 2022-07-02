package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.utilites.SettingsGame;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class SettingsScene extends SceneFW {

    public SettingsScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if (coreFW.isIsPressedKeyBack()) {
            coreFW.setIsPressedKeyBack(false);
            coreFW.setScene(new MainMenuScene(coreFW));
        }

        if ( coreFW.getTouchListenerFW().getTouchUp(400,250,150,30)) {
            SettingsGame.sSoundOn = !SettingsGame.sSoundOn;
            SettingsGame.saveSettings(coreFW);
        }

        if ( coreFW.getTouchListenerFW().getTouchUp(400,300,150,30)) {
            SettingsGame.sMusicOn = !SettingsGame.sMusicOn;
            SettingsGame.saveSettings(coreFW);
        }
    }

    @Override
    public void drawing() {
        coreFW.getGraphicsFW().clearScene(Color.BLACK);
        coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.txt_mainMenu_settings),250,200,Color.BLUE,50, UtilResource.mainMenuFont);

        coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_sound),250,250,Color.BLUE,30,UtilResource.mainMenuFont);
        coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_music),250,300,Color.BLUE,30,UtilResource.mainMenuFont);

        if (SettingsGame.sSoundOn) {
            coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_on),400,250,Color.GREEN,30,UtilResource.mainMenuFont);
        } else {
            coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_off),400,250,Color.RED,30,UtilResource.mainMenuFont);
        }

        if (SettingsGame.sMusicOn) {
            coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_on),400,300,Color.GREEN,30,UtilResource.mainMenuFont);
        } else {
            coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.setting_off),400,300,Color.RED,30,UtilResource.mainMenuFont);
        }
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
