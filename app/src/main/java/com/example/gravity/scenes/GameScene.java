package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.classes.GameManager;
import com.example.gravity.utilites.SettingsGame;
import com.example.gravity.utilites.UtilResource;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class GameScene extends SceneFW {

    private GameState mGameState;
    private GameManager mGameManager;

    enum GameState {
        READY, RUNNING, PAUSE, GAME_OVER
    }

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        init(coreFW);
        if (SettingsGame.sMusicOn) {
            UtilResource.sGameMusic.play(true, 0.3f);
        }
    }

    private void init(CoreFW coreFW) {
        mGameState = GameState.READY;
        mGameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
    }

    @Override
    public void update() {
        if (mGameState ==GameState.READY) {
            updateStateReady();
        }
        if (mGameState ==GameState.RUNNING) {
            updateStateRunning();
        }
        if (mGameState ==GameState.PAUSE) {
            updateStatePause();
        }
        if (mGameState ==GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);

        if (mGameState ==GameState.READY) {
            drawingStateReady();
        }
        if (mGameState ==GameState.RUNNING) {
            drawingStateRunning();
        }
        if (mGameState ==GameState.PAUSE) {
            drawingStatePause();
        }
        if (mGameState ==GameState.GAME_OVER) {
            drawingStateGameOver();
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }
    private void updateStateReady() {
        if (coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight, sceneWidth, sceneHeight)) {
            mGameState = GameState.RUNNING;
        }
    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        mGameManager.drawing(graphicsFW);
        if (SettingsGame.sMusicOn) {
            UtilResource.sGameMusic.play(true, 0.3f);
        }
    }
    private void updateStateRunning() {
        mGameManager.update();
        if (GameManager.sGameOver) {
            mGameState = GameState.GAME_OVER;
        }
        if (coreFW.isIsPressedKeyBack()) {
            mGameState = GameState.PAUSE;
            coreFW.setIsPressedKeyBack(false);
            if (SettingsGame.sMusicOn) {
                UtilResource.sGameMusic.stop();
            }
        }
    }

    private void drawingStatePause() {
        coreFW.getGraphicsFW().drawText("Пауза",200,300,Color.GREEN,50,null);
    }
    private void updateStatePause() {
        if (coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)) {
            mGameState = GameState.RUNNING;
        }

        if (coreFW.isIsPressedKeyBack()) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    private void drawingStateGameOver() {
        SettingsGame.addDistance(mGameManager.getPassedDistance());
        SettingsGame.saveSettings(coreFW);

        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver)
                , 250, 300,Color.GREEN, 60,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart)
                , 250, 360,Color.GREEN, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit)
                , 250, 420,Color.GREEN, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance)+": "+ mGameManager.getPassedDistance()
                , 250, 200,Color.GREEN, 30,null);
    }
    private void updateStateGameOver() {
        UtilResource.sGameMusic.stop();
        if (coreFW.getTouchListenerFW().getTouchUp(250,360,220,35)) {
            coreFW.setScene(new GameScene(coreFW));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(250,420,220,35)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void pause() {
        UtilResource.sGameMusic.stop();
    }

    @Override
    public void resume() {
        if (SettingsGame.sMusicOn) {
            UtilResource.sGameMusic.play(true, 0.3f);
        }
    }

    @Override
    public void dispose() {
        if (SettingsGame.sMusicOn) {
            UtilResource.sGameMusic.stop();

            UtilResource.sHit.stop();
            UtilResource.sExplode.stop();
            UtilResource.sTouch.stop();
        }
    }
}
