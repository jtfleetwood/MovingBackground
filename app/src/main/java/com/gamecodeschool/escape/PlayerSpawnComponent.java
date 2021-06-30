package com.gamecodeschool.escape;

public class PlayerSpawnComponent implements SpawnComponent{

    @Override
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {
        playerM.setLocation(playerM.getScreenSize().x / 2, playerM.getScreenSize().y / 2);
    }
}
