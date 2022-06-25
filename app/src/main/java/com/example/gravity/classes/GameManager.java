package com.example.gravity.classes;

import com.example.gravity.generators.GeneratorBackground;
import com.example.gravity.generators.GeneratorEnemy;
import com.example.gravity.generators.GeneratorGifts;
import com.example.gravity.objects.HUD;
import com.example.gravity.objects.MainPlayer;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CollisionDetectFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;

public class GameManager {

    private int mPassedDistance;

    public static boolean sGameOver;

    private MainPlayer mMainPlayer;
    private GeneratorBackground mGeneratorBackground;
    private GeneratorEnemy mGeneratorEnemy;
    private GeneratorGifts mGeneratorGifts;
    private HUD mHud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        init(coreFW, sceneWidth, sceneHeight);
    }

    private void init(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        mHud = new HUD(coreFW);
        int mMinScreenY = mHud.getHEIGHT_HUD();

        mMainPlayer = new MainPlayer(coreFW, sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorBackground = new GeneratorBackground(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, mMinScreenY);
        mGeneratorGifts = new GeneratorGifts(sceneWidth, sceneHeight, mMinScreenY);

        sGameOver = false;
    }

    public void update() {
        mMainPlayer.update();
        mGeneratorBackground.update(mMainPlayer.getSpeedPlayer());
        mGeneratorEnemy.update(mMainPlayer.getSpeedPlayer());
        mGeneratorGifts.update(mMainPlayer.getSpeedPlayer());

        mPassedDistance += mMainPlayer.getSpeedPlayer();
        int mCurrentSpeedPlayer = (int) mMainPlayer.getSpeedPlayer() * 60;
        int mCurrentShieldsPlayer = mMainPlayer.getShieldsPlayer();

        mHud.update(mPassedDistance, mCurrentSpeedPlayer, mCurrentShieldsPlayer);

        checkHit();
    }

    public void checkHit() {
        for (int i = 0; i < mGeneratorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetectFW.collisionDetect(mMainPlayer, mGeneratorEnemy.enemyArrayList.get(i))) {
                UtilResource.sHit.play(1);
                mMainPlayer.hitEnemy();
                mGeneratorEnemy.hitPlayer(mGeneratorEnemy.enemyArrayList.get(i));
            }
            if (CollisionDetectFW.collisionDetect(mMainPlayer, mGeneratorGifts.getProtector())) {
                hitPlayerWithProtector();
            }
        }
    }

    private void hitPlayerWithProtector() {
        mMainPlayer.hitProtector();
        mGeneratorGifts.hitProtectorWithPlayer();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mMainPlayer.drawing(graphicsFW);
        mGeneratorBackground.drawing(graphicsFW);
        mGeneratorEnemy.drawing(graphicsFW);
        mGeneratorGifts.drawing(graphicsFW);
        mHud.drawing(graphicsFW);
    }

    public int getPassedDistance() {
        return mPassedDistance;
    }

}
