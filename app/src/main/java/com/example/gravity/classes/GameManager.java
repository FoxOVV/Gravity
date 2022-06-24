package com.example.gravity.classes;

import com.example.gravity.Main;
import com.example.gravity.generators.GeneratorBackground;
import com.example.gravity.generators.GeneratorEnemy;
import com.example.gravity.generators.GeneratorGifts;
import com.example.gravity.objects.HUD;
import com.example.gravity.objects.MainPlayer;
import com.example.gravity.utilites.UtilResourse;
import com.example.my_framework.CollisionDetectFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    MainPlayer mainPlayer;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    GeneratorGifts generatorGifts;
    HUD hud;

    public int getPassedDistance() {
        return passedDistance;
    }

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = hud.getHEIGHT_HUD();

        mainPlayer = new MainPlayer(coreFW,maxScreenX,maxScreenY,minScreenY);
        generatorBackground = new GeneratorBackground(maxScreenX, maxScreenY,minScreenY);
        generatorEnemy = new GeneratorEnemy(maxScreenX, maxScreenY, minScreenY);
        generatorGifts = new GeneratorGifts(maxScreenX, maxScreenY, minScreenY);

        gameOver = false;
    }

    public void update() {
        mainPlayer.update();
        generatorBackground.update(mainPlayer.getSpeedPlayer());
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
        generatorGifts.update(mainPlayer.getSpeedPlayer());

        passedDistance += mainPlayer.getSpeedPlayer();
        currentSpeedPlayer = (int) mainPlayer.getSpeedPlayer()*60;
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance,currentSpeedPlayer,currentShieldsPlayer);

        checkHit();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        generatorGifts.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }

    public void checkHit() {
        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetectFW.collisionDetect(mainPlayer,generatorEnemy.enemyArrayList.get(i))) {
                UtilResourse.hit.play(1);
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
            if (CollisionDetectFW.collisionDetect(mainPlayer,generatorGifts.getProtector())) {
                hitPlayerWithProtector();
            }
        }
    }

    private void hitPlayerWithProtector() {
        mainPlayer.hitProtector();
        generatorGifts.hitProtectorWithPlayer();
    }
}
