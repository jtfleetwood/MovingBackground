package com.gamecodeschool.escape;

import android.graphics.PointF;
import android.graphics.RectF;

public class FallObjectMovementComponent implements MovementComponent{
    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {

        PointF speed = m.getSpeed();
        PointF location = m.getLocation();

        location.y += speed.y / fps;

        m.updateCollider();

        if (location.y > m.getScreenSize().y || RectF.intersects(m.getCollider(), playerM.getCollider())) {
            return false;
        }

        return true;
    }
}
