package com.gamecodeschool.escape;

import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver {

    private MovementInfo movementInfo;

    PlayerInputComponent(GameEngine ger) {
        ger.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, Pair<RectF, Joystick> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        Joystick localJS = controls.second;


        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:

                if (localJS.isCircleTouched(x, y)) {
                    movementInfo.resetMovementAvailability();

                }

                break;

            case MotionEvent.ACTION_DOWN:

                if (localJS.isCircleTouched(x, y)) {
                    movementInfo.setAngle(localJS.calcAngle(x, y));
                    movementInfo.setMovementAvailability();

                }

                break;

        }
    }

    public void setMovementInfo(MovementInfo m) {
        movementInfo = m;
    }
}
