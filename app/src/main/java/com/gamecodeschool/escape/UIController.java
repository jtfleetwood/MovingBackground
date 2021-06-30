package com.gamecodeschool.escape;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;

import java.util.ArrayList;

// Same implementation as scrolling shooter.
public class UIController implements InputObserver {

    // Is an observer because we are listening for touches from our "Touch" broadcaster.
    public UIController(GameEngineBroadcaster b) {
        b.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, Pair<RectF, Joystick> controls) {

        // Below, getActionIndex() returns position in array of events that performed the action (event).
        // Ex: Person could be moving finger, and this is an event but not a touch.
        int i = event.getActionIndex();
        // Allows us to get coordinates on the specific action that we want to locate.
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() & MotionEvent.ACTION_MASK;


        // Action pointer up is for multi-touch functionality.
        if (eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if (controls.first.contains(x,y)) {
                if (!gs.getPaused()) {
                    gs.pause();
                }

                else if (gs.getGameOver()) {
                    gs.startNewGame();
                }

                else if (gs.getPaused() && !gs.getGameOver()) {
                    gs.resume();
                }
            }
        }

    }




}