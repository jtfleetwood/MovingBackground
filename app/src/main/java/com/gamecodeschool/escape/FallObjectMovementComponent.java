package com.gamecodeschool.escape;

import android.graphics.PointF;
import android.graphics.RectF;

/*
    Very simple concept below as we are just spawning the object and letting it fall to the bottom of the screen, and
    once it reaches the screen, or collides with the player - we are setting it to inactive. For two main reasons: 1)
    Makes the player look like they are catching the falling object, and 2) Prevents multiple collisions as this will
    affect our setBoost() method within the movement info class.
 */
public class FallObjectMovementComponent implements MovementComponent{
    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {

        PointF speed = m.getSpeed();
        PointF location = m.getLocation();

        // Moving to bottom of screen
        location.y += speed.y / fps;

        m.updateCollider();

        // If reaches bottom of screen, or collides with player - set inactive.
        if (location.y > m.getScreenSize().y || RectF.intersects(m.getCollider(), playerM.getCollider())) {
            return false;
        }

        return true;
    }
}
