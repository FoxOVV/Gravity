package com.example.gravity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.gravity.classes.LoaderAssets;
import com.example.gravity.scenes.MainMenuScene;
import com.example.gravity.utilites.SettingsGame;
import com.example.my_framework.CoreFW;
import com.example.my_framework.LoopFW;
import com.example.my_framework.SceneFW;

import java.util.Date;

public class Main extends CoreFW {

    public SceneFW getStartScene() {
        //Log.d("IS_MAIN","CoreFW:onCreate - "+sceneWidth+" Y:"+sceneHeight);

        SettingsGame.loadSettings(this);
        LoaderAssets loaderAssets = new LoaderAssets(this, this.getGraphicsFW());
        return new MainMenuScene(this);
    }
}