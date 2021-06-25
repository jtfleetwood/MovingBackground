package com.gamecodeschool.escape;

import android.graphics.PointF;

public class PlayerMovementComponent implements MovementComponent {

    public void move(long fps, MovementInfo m, MovementInfo playerM) {
        float screenHeight = m.getScreenSize().y;
        float angle;

        PointF location = m.getLocation();

        float speed = m.getSpeed();

        float height = m.getObjectHeight();

        if (m.headingDown()) {
            location.y += speed / fps;
        }

        else if (m.headingUp()) {
            location.y -= speed / fps;
        }

        else if (m.headingLeft()) {
            location.x -= speed / fps;
        }

        else if (m.headingRight()) {
            location.x += speed / fps;
        }

        else if (m.headingDR()) {
            angle = (3.14f / 6f);
            location.x += (float)Math.cos(angle) * speed;
            location.y -= (float)Math.sin(angle) * speed;
        }

        else if (m.headingDL()) {
            angle = (5f * 3.14f) / 6f;
            location.x -= (float)Math.cos(angle) * speed;
            location.y -= (float)Math.sin(angle) * speed;
        }

        else if (m.headingBDR()) {
            angle = (11f * 3.14f) / 6f;
            location.x += (float)Math.cos(angle) * speed;
            location.y += (float)Math.sin(angle) * speed;
        }

        else if (m.headingBDL()) {
            angle = (7f * 3.14f) / 6f;

            location.x -= (float)Math.cos(angle) * speed;
            location.y += (float)Math.sin(angle) * speed;
        }

        if (location.y > screenHeight - height) {
            location.y = screenHeight - height;
        }

        else if (location.y < 0) {
            location.y = 0;
        }

        m.updateCollider();
    }
}
