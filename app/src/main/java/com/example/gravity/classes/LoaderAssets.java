package com.example.gravity.classes;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.gravity.utilites.UtilResourse;

import java.util.ArrayList;

public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpritePlayerBoost(graphicsFW);

        loadSpritePlayerShieldsOn(graphicsFW);

        loadSpriteEnemy(graphicsFW);
        loadOther(graphicsFW);

        loadGifts(graphicsFW);

        loadAudio(coreFW);
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
        UtilResourse.textureAtlas2 = graphicsFW.newTexture("texture_atlas2.png");
    }

    public void loadOther(GraphicsFW graphicsFW) {
        UtilResourse.shieldHitEnemy = graphicsFW.newSprite(UtilResourse.textureAtlas2,266,0,64,69);
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        /*UtilResourse.spritePlayer = new ArrayList<>();
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,0,0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,64,0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,128,0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,192,0, 64, 64));*/

        UtilResourse.spritePlayer = new ArrayList<>();
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,0, 64, 69));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,66,0, 64, 69));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,133,0, 64, 69));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,199,0, 64, 69));

        /*UtilResourse.spriteExplosinPlayer = new ArrayList<>();
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,256,256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,320,256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,384,256, 64, 64));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,448,256, 64, 64));*/

        UtilResourse.spriteExplosinPlayer = new ArrayList<>();
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,190, 45, 67));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,50,190, 62, 67));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,115,190, 60, 67));
        UtilResourse.spriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,178,190, 58, 67));
    }

    private void loadSpritePlayerBoost(GraphicsFW graphicsFW) {
        /*UtilResourse.spritePlayerBoost = new ArrayList<>();
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,0,64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,64,64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,128,64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,192,64, 64, 64));*/

        UtilResourse.spritePlayerBoost = new ArrayList<>();
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,72, 64, 69));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,66,72, 64, 69));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,133,72, 64, 69));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,199,72, 64, 69));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        UtilResourse.spritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,264, 64, 69));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,66,264, 64, 69));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,133,264, 64, 69));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,199,264, 64, 69));

        UtilResourse.spritePlayerShieldsOnBoost = new ArrayList<>();
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,335, 64, 69));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,66,335, 64, 69));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,133,335, 64, 69));
        UtilResourse.spritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,199,335, 64, 69));
    }
    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        /*UtilResourse.spriteEnemy = new ArrayList<>();
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,256,0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,320,0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,384,0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,448,0, 64, 64));*/

        UtilResourse.spriteEnemy = new ArrayList<>();
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,0,144, 44, 41));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,45,142, 42, 44));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,89,142, 43, 43));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,134,143, 43, 42));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.spriteProtector = new ArrayList<>();
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,336,0, 39, 50));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,378,5, 28, 40));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,336,0, 39, 50));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas2,378,5, 28, 40));
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.gameMusic = coreFW.getAudioFW().newMusic("background_music.mp3");

        UtilResourse.hit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.explode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.touch = coreFW.getAudioFW().newSound("touch.ogg");
    }
}
