package com.example.gravity.tasks;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.gravity.interfaces.TaskCompleteListener;
import com.example.gravity.scenes.LoaderResourceScene;
import com.example.gravity.utilites.SettingsGame;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class LoaderTask extends AsyncTask<Void,Integer,Void> {
    @SuppressLint("StaticFieldLeak")
    private final CoreFW mCoreFW;
    private final TaskCompleteListener mTaskCompleteListener;

    public LoaderTask(CoreFW coreFW, TaskCompleteListener taskCompleteListener) {
        mCoreFW = coreFW;
        mTaskCompleteListener = taskCompleteListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loaderAssets();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mTaskCompleteListener.onComplete();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LoaderResourceScene.setProgressLoader(values[0]);
    }

    private void loaderAssets() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadTexture(mCoreFW.getGraphicsFW());
        publishProgress(200);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadSpritePlayer(mCoreFW.getGraphicsFW());
        publishProgress(400);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadSpritePlayerShieldsOn(mCoreFW.getGraphicsFW());
        publishProgress(500);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadSpriteEnemy(mCoreFW.getGraphicsFW());
        publishProgress(600);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadGifts(mCoreFW.getGraphicsFW());
        publishProgress(700);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadOther(mCoreFW.getGraphicsFW());
        publishProgress(750);
        publishProgress(800);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadAudio(mCoreFW);
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResource.sTextureAtlas2 = graphicsFW.newTexture("texture_atlas2.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResource.sSpritePlayer = new ArrayList<>();
        UtilResource.sSpritePlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,0, 64, 69));
        UtilResource.sSpritePlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,66,0, 64, 69));
        UtilResource.sSpritePlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,133,0, 64, 69));
        UtilResource.sSpritePlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,199,0, 64, 69));

        UtilResource.sSpriteExplosionPlayer = new ArrayList<>();
        UtilResource.sSpriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,190, 45, 67));
        UtilResource.sSpriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,50,190, 62, 67));
        UtilResource.sSpriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,115,190, 60, 67));
        UtilResource.sSpriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,178,190, 58, 67));

        UtilResource.sSpritePlayerBoost = new ArrayList<>();
        UtilResource.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,72, 64, 69));
        UtilResource.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,66,72, 64, 69));
        UtilResource.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,133,72, 64, 69));
        UtilResource.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,199,72, 64, 69));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        UtilResource.sSpritePlayerShieldsOn = new ArrayList<>();
        UtilResource.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,264, 64, 69));
        UtilResource.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,66,264, 64, 69));
        UtilResource.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,133,264, 64, 69));
        UtilResource.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,199,264, 64, 69));

        UtilResource.sSpritePlayerShieldsOnBoost = new ArrayList<>();
        UtilResource.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,335, 64, 69));
        UtilResource.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,66,335, 64, 69));
        UtilResource.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,133,335, 64, 69));
        UtilResource.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,199,335, 64, 69));
    }
    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        UtilResource.sSpriteEnemy = new ArrayList<>();
        UtilResource.sSpriteEnemy.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,0,144, 44, 41));
        UtilResource.sSpriteEnemy.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,45,142, 42, 44));
        UtilResource.sSpriteEnemy.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,89,142, 43, 43));
        UtilResource.sSpriteEnemy.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,134,143, 43, 42));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResource.sSpriteProtector = new ArrayList<>();
        UtilResource.sSpriteProtector.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,336,0, 39, 50));
        UtilResource.sSpriteProtector.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,378,5, 28, 40));
        UtilResource.sSpriteProtector.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,336,0, 39, 50));
        UtilResource.sSpriteProtector.add(graphicsFW.newSprite(UtilResource.sTextureAtlas2,378,5, 28, 40));
    }

    public void loadOther(GraphicsFW graphicsFW) {
        UtilResource.sShieldHitEnemy = graphicsFW.newSprite(UtilResource.sTextureAtlas2,266,0,64,69);
        SettingsGame.loadSettings(mCoreFW);
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResource.sGameMusic = coreFW.getAudioFW().newMusic("background_music.mp3");

        UtilResource.sHit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResource.sExplode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResource.sTouch = coreFW.getAudioFW().newSound("touch.ogg");
    }
}
