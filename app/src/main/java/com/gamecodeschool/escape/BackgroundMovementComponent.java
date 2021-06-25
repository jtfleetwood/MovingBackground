package com.gamecodeschool.escape;

public class BackgroundMovementComponent implements MovementComponent {

    @Override
    public void move(long fps, MovementInfo m, MovementInfo playerM) {
        int currentXClip = m.getXClip();

        if (playerM.headingDR()){
            currentXClip -= m.getSpeed() / fps;
            m.setXClip(currentXClip);
        }

        else if (playerM.headingDL()) {
            currentXClip += m.getSpeed() / fps;
            m.setXClip(currentXClip);
        }

        else if (playerM.headingLeft()) {
            currentXClip += m.getSpeed() / fps;
            m.setXClip(currentXClip);
        }

        else if (playerM.headingRight()) {
            currentXClip -= m.getSpeed() / fps;
            m.setXClip(currentXClip);
        }

    }
}
