package com.example.gravity.utilites;

import android.graphics.Bitmap;
import android.graphics.Typeface;

import com.example.my_framework.MusicFW;
import com.example.my_framework.SoundFW;

import java.util.ArrayList;

public class UtilResource {
    public static Bitmap sTextureAtlas2;
    public static ArrayList<Bitmap> sSpritePlayer;
    public static ArrayList<Bitmap> sSpritePlayerBoost;
    public static ArrayList<Bitmap> sSpritePlayerShieldsOn;
    public static ArrayList<Bitmap> sSpritePlayerShieldsOnBoost;

    public static ArrayList<Bitmap> sSpriteProtector;

    public static ArrayList<Bitmap> sSpriteEnemy;
    public static Bitmap sShieldHitEnemy;
    public static ArrayList<Bitmap> sSpriteExplosionPlayer;

    public static MusicFW sGameMusic;

    public static SoundFW sHit;
    public static SoundFW sExplode;
    public static SoundFW sTouch;
    
    public static Typeface mainMenuFont;
}
