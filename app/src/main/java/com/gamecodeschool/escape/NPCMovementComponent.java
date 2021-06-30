package com.gamecodeschool.escape;

import android.graphics.PointF;

public class NPCMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {

        PointF location = m.getLocation();
        PointF speed = m.getSpeed();

        if (playerM.getLocation().x > location.x) {
            location.x += speed.x / fps;

            if (Math.abs(playerM.getLocation().x - location.x) > m.getObjectWidth()) {
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
