package com.gamecodeschool.escape;

import android.graphics.PointF;

public class BoostMovementComponent implements MovementComponent{
    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {

        float speed = m.getSpeed();
        PointF location = m.getLocation();

        location.y += speed / fps;

        m.updateCollider();

        if (location.y > m.getScreenSize().y) {
            return false;
        }

        return true;
    }
}
