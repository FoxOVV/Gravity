package com.example.my_framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class GraphicsFW {

    private AssetManager mAssetManagerGame;
    private Canvas mCanvasGame;
    private Paint mPaintGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        init(assetManagerGame, frameBufferGame);
    }

    private void init(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.mAssetManagerGame = assetManagerGame;
        this.mCanvasGame = new Canvas(frameBufferGame);
        this.mPaintGame = new Paint();
    }

    //Очищаем, устанавливая всю сцену в 1 цвет
    public void clearScene(int colorRGB) {
        mCanvasGame.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel (int x, int y, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawPoint(x,y, mPaintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawLine(startX, startY, stopX, stopY, mPaintGame);
    }

    public void drawText(String text, int x, int y, int color, int sizeText, Typeface font) {
        mPaintGame.setColor(color);
        mPaintGame.setTextSize(sizeText);
        mPaintGame.setTypeface(font);
        mCanvasGame.drawText(text,x,y, mPaintGame);
    }

    public void drawTexture (Bitmap textureGame, int x, int y) {
        mCanvasGame.drawBitmap(textureGame,x,y,null);
    }

    public int getWidthFrameBuffer() {
        return mCanvasGame.getWidth();
    }

    public int getHeightFrameBuffer() {
        return mCanvasGame.getHeight();
    }

    public Bitmap newTexture(String fileName) {
        Bitmap mTextureGame;
        try {
            InputStream inputStream = mAssetManagerGame.open(fileName);
            mTextureGame = BitmapFactory.decodeStream(inputStream);
            if (mTextureGame ==null) {
                throw new RuntimeException("Не возможно загрузить файл " + fileName);
            }
            inputStream.close();
        } catch(IOException e) {
            throw new RuntimeException("Не возможно загрузить файл " + fileName);
        }
        return mTextureGame;
    }

    public Bitmap newSprite (Bitmap textureAtlas, int x, int y, int widthSprite, int heightSprite) {
        return Bitmap.createBitmap(textureAtlas,x,y,widthSprite,heightSprite);
    }
}