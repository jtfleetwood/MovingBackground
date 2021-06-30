package com.gamecodeschool.escape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

// Same exact implementation as ScrollingShooter.
public class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    Renderer(SurfaceView sh) {
        mSurfaceHolder = sh.getHolder();

        mPaint = new Paint();
    }

    void draw(ArrayList<GameObject> objects, GameState gs, HUD hud) {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            mCanvas.drawColor(Color.argb(255, 0, 0,0));

            if (gs.getDrawing()) {
                for (GameObject object : objects) {
                    if (object.checkActive()) {
                        object.draw(mCanvas, mPaint);
                    }
                }
            }

            if (gs.getGameOver()) {
                objects.get(GameObjectContainer.BACKGROUND_INDEX).draw(mCanvas, mPaint);
            }


            hud.draw(mCanvas, mPaint, gs);
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
