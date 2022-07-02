package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.interfaces.TaskCompleteListener;
import com.example.gravity.tasks.LoaderTask;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class LoaderResourceScene extends SceneFW implements TaskCompleteListener {

    private static int mProgressLoader = 0;

    public LoaderResourceScene(CoreFW coreFW) {
        super(coreFW);
        LoaderTask loaderTask = new LoaderTask(coreFW, this);
        loaderTask.execute();
    }

    @Override
    public void update() {
    }

    @Override
    public void drawing() {
        coreFW.getGraphicsFW().clearScene(Color.BLACK);
        coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.loading),100,100,Color.BLUE,50, UtilResource.mainMenuFont);
        coreFW.getGraphicsFW().drawLine(0,500,mProgressLoader,500,Color.BLUE);
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

    @Override
    public void onComplete() {
        coreFW.setScene(new MainMenuScene(coreFW));
    }

    public static void setProgressLoader(int mProgressLoader) {
        LoaderResourceScene.mProgressLoader = mProgressLoader;
    }
}
