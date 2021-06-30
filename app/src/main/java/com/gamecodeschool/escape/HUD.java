package com.gamecodeschool.escape;

import android.graphics.*;
import android.util.Pair;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;
    private RectF pause;
    private Joystick test;
    private Pair<RectF, Joystick> controls;


    HUD (Point size) {
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls() {
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;


        pause = new RectF(mScreenWidth - buttonPadding - buttonWidth, 0 + buttonPadding,
                mScreenWidth - buttonPadding, 0 + buttonPadding + buttonHeight);

        test =  new Joystick((float)(mScreenWidth / 8), (float)(mScreenHeight / 1.25), mScreenWidth / 15);

        controls = new Pair<RectF, Joystick>(pause, test);

    }

    void draw(Canvas c, Paint p, GameState gs) {
        p.setColor(Color.argb(255, 255, 0, 255));
        p.setTextSize(mTextFormatting);
        c.drawText("Hi: " + gs.getHighScore(), mTextFormatting, mTextFormatting, p);
        c.drawText("Score: " + gs.getScore(), mTextFormatting, mTextFormatting * 2, p);
        c.drawText("Lives: " + gs.getLives(), mTextFormatting, mTextFormatting * 3, p);


        if(gs.getGameOver() && gs.getNumGames() >= 1) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 255, 0, 0));
            c.drawText("LOOOSER!", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        else if (gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 0, 0, 255));
            c.drawText("PRESS PLAY", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        else if (gs.getPaused() && !gs.getGameOver() && gs.wasLifeLost()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 255, 0, 0));
            c.drawText("TRY AGAIN!", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        else if (gs.getPaused() && !gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 0, 0, 255));
            c.drawText("TAKE A BREAK!", mScreenWidth / 4, mScreenHeight / 2, p);
        }


        drawControls(c, p);
    }

    private void drawControls(Canvas c, Paint p) {
        p.setColor(Color.argb(150, 255, 255, 255));


        c.drawRect(pause.left, pause.top, pause.right, pause.bottom, p);

        c.drawCircle(test.getLocation().x, test.getLocation().y, test.getRadius(), p);

        p.setColor(Color.argb(255, 255, 255, 255));
    }


    Pair<RectF, Joystick> getControls() {
        return controls;
    }
}
