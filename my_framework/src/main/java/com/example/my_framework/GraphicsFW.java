package com.example.my_framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class GraphicsFW {

    private AssetManager assetManagerGame;
    private Bitmap frameBufferGame;
    private Canvas canvasGame;
    private Paint paintGame;
    private Bitmap textureGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame   = assetManagerGame;
        this.frameBufferGame    = frameBufferGame;
        this.canvasGame         = new Canvas(frameBufferGame);
        this.paintGame          = new Paint();
    }

    //Очищаем, устанавливая всю сцену в 1 цвет
    public void clearScene(int colorRGB) {
        canvasGame.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel (int x, int y, int color) {
        paintGame.setColor(color);
        canvasGame.drawPoint(x,y,paintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        paintGame.setColor(color);
        canvasGame.drawLine(startX, startY, stopX, stopY, paintGame);
    }

    public void drawText(String text, int x, int y, int color, int sizeText, Typeface font) {
        paintGame.setColor(color);
        paintGame.setTextSize(sizeText);
        paintGame.setTypeface(font);
        canvasGame.drawText(text,x,y,paintGame);
    }

    public void drawTexture (Bitmap textureGame, int x, int y) {
        canvasGame.drawBitmap(textureGame,x,y,null);
    }

    public int getWidthFrameBuffer() {
        return canvasGame.getWidth();
    }

    public int getHeightFrameBuffer() {
        return canvasGame.getHeight();
    }

    public Bitmap newTexture(String fileName) {
        try {
            InputStream inputStream = assetManagerGame.open(fileName);
            textureGame = BitmapFactory.decodeStream(inputStream);
            if (textureGame==null) {
                throw new RuntimeException("Не возможно загрузить файл " + fileName);
            }
            inputStream.close();
        } catch(IOException e) {
            throw new RuntimeException("Не возможно загрузить файл " + fileName);
        }
        return textureGame;
    }

    public Bitmap newSprite (Bitmap textureAtlas, int x, int y, int widthSprite, int heightSprite) {
        Bitmap newSprite = Bitmap.createBitmap(textureAtlas,x,y,widthSprite,heightSprite);
        return newSprite;
    }
}