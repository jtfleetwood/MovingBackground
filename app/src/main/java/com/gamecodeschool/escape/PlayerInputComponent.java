package com.gamecodeschool.escape;

import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;

import java.util.ArrayList;

// Way simpler implementation of PlayerInputComponent rather than ScrollingShooter in terms of solely tracking movement.
// Wanted to include shooting ability, but just for simplicity of framework - I did not include. Very simple implementation to shoot either way..
public class PlayerInputComponent implements InputComponent, InputObserver {

    private MovementInfo movementInfo;

    // Is an input observer so need to be informed about screen touches from our Broadcaster.
    PlayerInputComponent(GameEngine ger) {
        ger.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, Pair<RectF, Joystick> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        // Passing in information regarding our joystick object as it controls movement.
        // Simple syntax in working with Pair.
        Joystick localJS = controls.second;

        // Checking for touches of joystick.
        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                // If finger was lifted from circle, not moving player anymore.
                if (localJS.isCircleTouched(x, y)) {
                    movementInfo.resetMovementAvailability();

                }

                break;

            case MotionEvent.ACTION_DOWN:
                // If finger pressed circle, need to move the player object.
                if (localJS.isCircleTouched(x, y)) {
                    // Calculating angle from center to touch point using our calcAngle method, and passing into our movementInfo object so vector components can be later assigned.
                    movementInfo.setAngle(localJS.calcAngle(x, y));
                    // Letting our movementInfo know that the object is available to move. This was previously done by many different bool variables in previous ScrollingShooter configuration.
                    movementInfo.setMovementAvailability();

                }

                break;

        }
    }

    public void setMovementInfo(MovementInfo m) {
        movementInfo = m;
    }
}
