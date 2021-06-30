package com.gamecodeschool.escape;

public class BackgroundMovementComponent implements MovementComponent {

    // Same exact concept as ScrollingShooter.

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {
        int currentXClip = m.getXClip();

        if (playerM.headingLeft()) {
            currentXClip += m.getSpeed().x / fps;
            m.setXClip(currentXClip);
        }

        else if (playerM.headingRight()) {
            currentXClip -= m.getSpeed().x / fps;
            m.setXClip(currentXClip);
        }

        if (currentXClip >= m.getSize().x) {
            m.setXClip(0);
            m.flipReversedFirst();
        }

        else if (currentXClip <= 0) {
            m.setXClip((int)m.getSize().x);
            m.flipReversedFirst();
        }

        return true;
    }
}
