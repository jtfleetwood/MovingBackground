package com.gamecodeschool.escape;

import android.graphics.PointF;


public class PlayerMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {
        float screenHeight = m.getScreenSize().y;
        PointF velocity;
        m.resetMovement();

        PointF location = m.getLocation();
        System.out.println(m.getSpeed().x);

        if (m.isAvailableToMove()) {
            m.setVComponents();
            velocity = m.getVComponents();
            location.x += velocity.x / fps;
            location.y += velocity.y / fps;

            if (velocity.x > 0) {
                m.headRight();
            }

            else if (velocity. x < 0) {
                m.headLeft();
            }

        }

        if (location.y > (screenHeight - m.getObjectHeight())) {
            location.y = screenHeight - m.getObjectHeight();
        }

        else if (location.y < 0) {
            location.y = 0;
        }

        if (location.x > (m.getScreenSize().x - 165)) {
            location.x = m.getScreenSize().x - 165;
        }

        else if (location.x < 0) {
            location.x = 0;
        }

        m.updateCollider();

        return true;
    }


}
