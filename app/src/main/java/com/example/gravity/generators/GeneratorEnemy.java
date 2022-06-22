package com.example.gravity.generators;

import com.example.gravity.objects.Enemy;
import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer) {
        if (enemyArrayList.size()<3) {
            int amountEnemy = 3-enemyArrayList.size();
            addEnemy(speedPlayer,amountEnemy);
        }

        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    public void addEnemy(double speedPlayer, int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            enemyArrayList.add(new Enemy(maxScreenX,maxScreenY,minScreenY,1));
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }

    public void hitPlayer(Enemy enemy) {
        enemyArrayList.remove(enemy);
    }
}
