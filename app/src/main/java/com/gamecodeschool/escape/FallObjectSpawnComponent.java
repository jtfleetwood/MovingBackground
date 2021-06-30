package com.gamecodeschool.escape;

import java.util.Random;

/*
 Very simple set up as the spawnComponent consists of one method that randomly places the falling object along the x-axis,
 at the top of the screen.
 */
public class FallObjectSpawnComponent implements SpawnComponent{

    @Override
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {
        Random random = new Random();
        float xPos = 0;

        float yPos = 0;

        // Had to play around with the edges of the screen below as we do not want the falling objects spawning partially off screen.
        xPos = random.nextFloat() * (cObjectM.getScreenSize().x - 220) + 125;

        // Setting location to randomly assigned position along top of screen.
        cObjectM.setLocation(xPos, yPos);


    }
}


