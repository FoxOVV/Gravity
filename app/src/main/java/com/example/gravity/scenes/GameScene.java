package com.example.gravity.scenes;

import android.graphics.Color;

import com.example.gravity.R;
import com.example.gravity.classes.GameManager;
import com.example.gravity.generators.GeneratorBackground;
import com.example.gravity.utilites.SettingsGame;
import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

public class GameScene extends SceneFW {

    enum GameState {
        READY, RUNNING, PAUSE, GAMEOVER;
    }

    GameState gameState;
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
        UtilResourse.gameMusic.play(true, 0.3f);
    }

    @Override
    public void update() {
        if (gameState==GameState.READY) {
            updateStateReady();
        }
        if (gameState==GameState.RUNNING) {
            updateStateRunning();
        }
        if (gameState==GameState.PAUSE) {
            updateStatePause();
        }
        if (gameState==GameState.GAMEOVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);

        if (gameState==GameState.READY) {
            drawingStateReady();
        }
        if (gameState==GameState.RUNNING) {
            drawingStateRunning();
        }
        if (gameState==GameState.PAUSE) {
            drawingStatePause();
        }
        if (gameState==GameState.GAMEOVER) {
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
            gameState = GameState.RUNNING;
        }
    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        gameManager.drawing(coreFW,graphicsFW);
    }
    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver) {
            gameState = GameState.GAMEOVER;
        }
    }

    private void drawingStatePause() {

    }
    private void updateStatePause() {

    }

    private void drawingStateGameOver() {
        SettingsGame.addDistance(gameManager.getPassedDistance());
        SettingsGame.saveSettings(coreFW);

        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver)
                , 250, 300,Color.GREEN, 60,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart)
                , 250, 360,Color.GREEN, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit)
                , 250, 420,Color.GREEN, 30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance)+": "+gameManager.getPassedDistance()
                , 250, 200,Color.GREEN, 30,null);
    }
    private void updateStateGameOver() {
        UtilResourse.gameMusic.stop();
        if (coreFW.getTouchListenerFW().getTouchUp(250,360,220,35)) {
            coreFW.setScene(new GameScene(coreFW));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(250,420,220,35)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void pause() {
        UtilResourse.gameMusic.stop();
    }

    @Override
    public void resume() {
        UtilResourse.gameMusic.play(true, 0.3f);
    }

    @Override
    public void dispose() {
        UtilResourse.gameMusic.stop();

        UtilResourse.hit.stop();
        UtilResourse.explode.stop();
        UtilResourse.touch.stop();
    }
}
