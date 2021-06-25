package com.gamecodeschool.escape;

import android.graphics.*;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;
    private ArrayList<RectF> controls;

    static int UP = 0;
    static int DOWN = 1;
    static int LEFT = 2;
    static int RIGHT = 3;
    static int RD = 4;
    static int LD = 5;
    static int BDR = 6;
    static int BDL = 7;
    static int PAUSE = 8;

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

        RectF up = new RectF((float)(mScreenWidth / 1.85), mScreenHeight - (buttonPadding * 2) - (buttonHeight * 3),
                mScreenWidth + buttonWidth, mScreenHeight - buttonPadding - (buttonHeight * 2));

        RectF down = new RectF(up.left, up.bottom + buttonPadding, up.right,
                up.bottom + buttonPadding + buttonHeight);

        RectF left = new RectF(up.left - buttonWidth - buttonPadding, up.top + (buttonHeight / 2),
                up.left - (buttonPadding), up.top + (buttonHeight / 2) + buttonHeight);

        RectF right = new RectF(up.right + buttonWidth + buttonPadding, left.top,
                up.right + buttonPadding + buttonWidth, left.top + buttonHeight);

        RectF RDiagonal = new RectF(right.right, up.top - buttonHeight, right.right + buttonWidth, up.top);

        RectF LDiagonal = new RectF(left.left - buttonWidth, RDiagonal.top, left.left, RDiagonal.bottom);

        RectF BLDiagonal = new RectF(LDiagonal.left, down.bottom, LDiagonal.right, down.bottom + buttonHeight);

        RectF BRDiagonal = new RectF(RDiagonal.left, down.bottom, RDiagonal.right, down.bottom + buttonHeight);

        RectF pause = new RectF(mScreenWidth - buttonPadding - buttonWidth, mScreenHeight - buttonPadding,
                mScreenHeight - buttonPadding, mScreenHeight - buttonPadding - buttonHeight);

        controls = new ArrayList<>();

        controls.add(UP, up);
        controls.add(DOWN, down);
        controls.add(LEFT, left);
        controls.add(RIGHT, right);
        controls.add(RD, RDiagonal);
        controls.add(LD, LDiagonal);
        controls.add(BDR, BRDiagonal);
        controls.add(BDL, BLDiagonal);
        controls.add(PAUSE, pause);


    }

    void draw(Canvas c, Paint p, GameState gs) {

        if (gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PRESS PLAY", mScreenWidth / 4, mScreenHeight / 2, p);

        }

        if (gs.getPaused() && !gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PAUSED", mScreenWidth / 3, mScreenHeight / 2, p);
        }
    }

    private void drawControls(Canvas c, Paint p) {
        p.setColor(Color.argb(100, 255, 255, 255));

        for (RectF r : controls) {
            c.drawRect(r.left, r.top, r.right, r.bottom, p);
        }

        p.setColor(Color.argb(255, 255, 255, 255));
    }

    ArrayList<RectF> getControls() {
        return controls;
    }
}
