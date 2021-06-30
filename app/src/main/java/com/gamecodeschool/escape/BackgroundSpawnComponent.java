package com.gamecodeschool.escape;

public class BackgroundSpawnComponent implements SpawnComponent {
    @Override
    // Same concept as ScrollingShooter.
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {
        cObjectM.setLocation(0f, 0f);
    }
}
