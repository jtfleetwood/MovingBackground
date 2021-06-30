package com.gamecodeschool.escape;

import android.graphics.*;
import android.util.Pair;

import java.util.ArrayList;

// Concept same as ScrollingShooter, however will explain difference in components below..
public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;
    // Pause button that will appear top-right of screen.
    private RectF pause;

    /*
    Below is joystick like button that will appear at bottom-left of screen. With almost 8 total different directional
    controls being requested, 8 different buttons on the screen is just too much for a mobile interface IMO. Scrolling
    shooter's UI layout was confusing and not very intuitive at all, I may be wrong, but that's just my view. The joystick
    allows for WAY better user control over player, and provides a lot more view space for user rather than providing 8 buttons.

    See JoyStick class for more explanation on mechanics/set-up/
     */
    private Joystick test;

    // As I said earlier, this is a framework so I did not really want to wrap both buttons in another user-defined object.
    // Using the Pair object was actually a really easy data structure to work with when using two different data types. (RectF vs. Joystick)
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

        // Below we are assigning the x/y components of center of circle and then radius.
        // This object is basically a circle wrapped in a user-defined class.
        test =  new Joystick((float)(mScreenWidth / 8), (float)(mScreenHeight / 1.25), mScreenWidth / 15);

        controls = new Pair<RectF, Joystick>(pause, test);

    }

    void draw(Canvas c, Paint p, GameState gs) {
        p.setColor(Color.argb(255, 255, 0, 255));
        p.setTextSize(mTextFormatting);
        c.drawText("Hi: " + gs.getHighScore(), mTextFormatting, mTextFormatting, p);
        c.drawText("Score: " + gs.getScore(), mTextFormatting, mTextFormatting * 2, p);
        c.drawText("Lives: " + gs.getLives(), mTextFormatting, mTextFormatting * 3, p);

        // As I mentioned earlier in GameState class. We are using some Gamestate conditions to determine which UI Layout or general screen needs to be displayed.

        // If the first game has already happened, we know the user had to lose, so displaying looser screen.
        if(gs.getGameOver() && gs.getNumGames() >= 1) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 255, 0, 0));
            c.drawText("LOOOSER!", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        // If the above did not execute, we know this is the player's first time starting the game.
        else if (gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 0, 0, 255));
            c.drawText("PRESS PLAY", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        // If life was lost - indicated from PhysicsEngine, resetting/respawning and displaying a different screen layout.
        // All little efforts to improve engagement with the player.
        else if (gs.getPaused() && !gs.getGameOver() && gs.wasLifeLost()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 255, 0, 0));
            c.drawText("TRY AGAIN!", mScreenWidth / 4, mScreenHeight / 2, p);
        }

        // If the game paused, the game is not over, and a player life was not lost, we know the player paused the game..
        // So, providing custom view layout.
        else if (gs.getPaused() && !gs.getGameOver()) {
            p.setTextSize(mTextFormatting * 5);
            p.setColor(Color.argb(255, 0, 0, 255));
            c.drawText("TAKE A BREAK!", mScreenWidth / 4, mScreenHeight / 2, p);
        }


        drawControls(c, p);
    }

    private void drawControls(Canvas c, Paint p) {
        p.setColor(Color.argb(150, 255, 255, 255));

        // Drawing pause button.
        c.drawRect(pause.left, pause.top, pause.right, pause.bottom, p);

        // Drawing joystick.
        c.drawCircle(test.getLocation().x, test.getLocation().y, test.getRadius(), p);

        p.setColor(Color.argb(255, 255, 255, 255));
    }

    // Returning our pair object that holds both controls of different types UI vs. Player input for movement.
    Pair<RectF, Joystick> getControls() {
        return controls;
    }
}
