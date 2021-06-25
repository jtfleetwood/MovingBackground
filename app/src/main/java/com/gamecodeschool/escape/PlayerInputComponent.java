package com.gamecodeschool.escape;

import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver {

    private MovementInfo movementInfo;

    PlayerInputComponent(GameEngine ger) {
        ger.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<RectF> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                if (controls.get(HUD.UP).contains(x, y) || controls.get(HUD.DOWN).contains(x, y)) {
                    movementInfo.stopVertical();
                }

                else if (controls.get(HUD.LD).contains(x, y) || controls.get(HUD.RD).contains(x, y) ||
                        controls.get(HUD.BDR).contains(x,y) || controls.get(HUD.BDL).contains(x, y)) {
                    movementInfo.stopDiagonal();
                }

                else if (controls.get(HUD.LEFT).contains(x, y) || controls.get(HUD.RIGHT).contains(x, y)) {
                    movementInfo.stopHorizontal();
                }

                break;

            case MotionEvent.ACTION_DOWN:
                if (controls.get(HUD.UP).contains(x, y)) {
                    movementInfo.headUp();
                }

                else if (controls.get(HUD.DOWN).contains(x, y)) {
                    movementInfo.headDown();
                }

                else if (controls.get(HUD.LEFT).contains(x, y)) {
                    movementInfo.headLeft();
                }

                else if (controls.get(HUD.RIGHT).contains(x, y)) {
                    movementInfo.headRight();
                }

                else if (controls.get(HUD.RD).contains(x, y)) {
                    movementInfo.headDR();
                }

                else if (controls.get(HUD.LD).contains(x, y)) {
                    movementInfo.headDL();
                }

                else if (controls.get(HUD.BDR).contains(x, y)) {
                    movementInfo.headingBDR();
                }

                else if (controls.get(HUD.BDL).contains(x, y)) {
                    movementInfo.headingBDL();
                }

                break;

        }
    }

    public void setMovementInfo(MovementInfo m) {
        movementInfo = m;
    }
}
