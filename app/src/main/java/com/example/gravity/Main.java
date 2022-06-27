package com.example.gravity;

import com.example.gravity.scenes.LoaderResourceScene;
import com.example.gravity.utilites.SettingsGame;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class Main extends CoreFW {

    public SceneFW getStartScene() {
        SettingsGame.loadSettings(this);

        //TODO Сделать отдельный поток для loaderAssets в AsyncTask, а не в UI поток
        /*LoaderAssets loaderAssets = new LoaderAssets(this, this.getGraphicsFW());
        return new MainMenuScene(this);*/
        return new LoaderResourceScene(this);
    }
}