package com.example.gravity.classes;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.gravity.utilites.UtilResourse;

import java.util.ArrayList;

public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpritePlayerShieldsOn(graphicsFW);
        loadSpriteEnemy(graphicsFW);
        loadGifts(graphicsFW);
        loadOther(graphicsFW);
        loadAudio(coreFW);
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.sTextureAtlas2 = graphicsFW.newTexture("texture_atlas2.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResourse.sSpritePlayer = new ArrayList<>();
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,0, 64, 69));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,66,0, 64, 69));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,133,0, 64, 69));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,199,0, 64, 69));

        UtilResourse.sSpriteExplosinPlayer = new ArrayList<>();
        UtilResourse.sSpriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,190, 45, 67));
        UtilResourse.sSpriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,50,190, 62, 67));
        UtilResourse.sSpriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,115,190, 60, 67));
        UtilResourse.sSpriteExplosinPlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,178,190, 58, 67));

        UtilResourse.sSpritePlayerBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,72, 64, 69));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,66,72, 64, 69));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,133,72, 64, 69));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,199,72, 64, 69));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {
        UtilResourse.sSpritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,264, 64, 69));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,66,264, 64, 69));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,133,264, 64, 69));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,199,264, 64, 69));

        UtilResourse.sSpritePlayerShieldsOnBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,335, 64, 69));
        UtilResourse.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,66,335, 64, 69));
        UtilResourse.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,133,335, 64, 69));
        UtilResourse.sSpritePlayerShieldsOnBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,199,335, 64, 69));
    }
    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteEnemy = new ArrayList<>();
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,0,144, 44, 41));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,45,142, 42, 44));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,89,142, 43, 43));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,134,143, 43, 42));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteProtector = new ArrayList<>();
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,336,0, 39, 50));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,378,5, 28, 40));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,336,0, 39, 50));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,378,5, 28, 40));
    }

    public void loadOther(GraphicsFW graphicsFW) {
        UtilResourse.sShieldHitEnemy = graphicsFW.newSprite(UtilResourse.sTextureAtlas2,266,0,64,69);
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.sGameMusic = coreFW.getAudioFW().newMusic("background_music.mp3");

        UtilResourse.sHit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.sExplode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.sTouch = coreFW.getAudioFW().newSound("touch.ogg");
    }
}
