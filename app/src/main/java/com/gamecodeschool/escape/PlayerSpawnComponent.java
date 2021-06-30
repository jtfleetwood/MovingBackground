package com.gamecodeschool.escape;

// Same as ScrollingShooter...
public class PlayerSpawnComponent implements SpawnComponent{

    @Override
    // Player always spawns at middle of screen.
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {
        playerM.setLocation(playerM.getScreenSize().x / 2, playerM.getScreenSize().y / 2);
    }
}
