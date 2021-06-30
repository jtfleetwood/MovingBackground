package com.gamecodeschool.escape;

import android.graphics.PointF;


public class PlayerMovementComponent implements MovementComponent {

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {
        float screenHeight = m.getScreenSize().y;
        PointF velocity;
        // Resetting movement upon each animation - if finger was lifted. Want to reset info on heading right/left.
        // This does not affect held down actions as the velocity components will be the same until a move is made. (Only calculated when available to move and not outside of that)
        m.resetMovement();

        PointF location = m.getLocation();

        // If circle was pressed and is still held down.
        if (m.isAvailableToMove()) {
            // Setting vector components based off angle passed in inputComponent by Joystick.
            // Just multiplying x/y speed by cos/sin of theta to find those components.
            m.setVComponents();
            velocity = m.getVComponents();
            // Once, components found - very simple implementation below.
            location.x += velocity.x / fps;
            location.y += velocity.y / fps;

            // Manually setting the below bool variables so our background movement component moves only when player does..
            if (velocity.x > 0) {
                m.headRight();
            }

            else if (velocity. x < 0) {
                m.headLeft();
            }

        }

        // Keeping player constrained to screen size..

        if (location.y > (screenHeight - m.getObjectHeight())) {
            location.y = screenHeight - m.getObjectHeight();
        }

        else if (location.y < 0) {
            location.y = 0;
        }

        if (location.x > (m.getScreenSize().x - 220)) {
            location.x = m.getScreenSize().x - 220;
        }

        else if (location.x < 0) {
            location.x = 0;
        }

        m.updateCollider();

        return true;
    }


}
