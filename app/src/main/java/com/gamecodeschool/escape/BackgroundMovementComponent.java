package com.gamecodeschool.escape;

public class BackgroundMovementComponent implements MovementComponent {

    // Same exact concept as ScrollingShooter.

    @Override
    public boolean move(long fps, MovementInfo m, MovementInfo playerM) {
        playerM.getLocation();

        return true;
    }
}
