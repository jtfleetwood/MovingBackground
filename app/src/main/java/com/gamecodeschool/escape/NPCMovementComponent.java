package com.gamecodeschool.escape;

import android.graphics.PointF;

// Very, very simple implementation of AI tracking.
public class NPCMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {
        /*
            Below we are simply comparing x/y values for the NPC vs. Player. If the player is to the right of the NPC, then
            move the NPC right. This follows for all directions..
         */
        PointF location = m.getLocation();
        PointF speed = m.getSpeed();

        if (playerM.getLocation().x > location.x) {
            location.x += speed.x / fps;

            // Minor adjustment made below to make sure NPC is not flipping constantly within close range of player when approaching.
            if (Math.abs(playerM.getLocation().x - location.x) > (m.getObjectWidth() / 2)) {
                m.setFacingRight();
            }

        }

        else if (playerM.getLocation().x < location.x){
            location.x -= speed.x / fps;

            if (Math.abs(playerM.getLocation().x - location.x) > (m.getObjectWidth() / 2)) {
                m.setFacingLeft();
            }


        }

        if (playerM.getLocation().y > location.y) {
            location.y += speed.y / fps;
        }

        else {
            location.y -= speed.y / fps;
        }

        m.updateCollider();

        return true;
    }


}
