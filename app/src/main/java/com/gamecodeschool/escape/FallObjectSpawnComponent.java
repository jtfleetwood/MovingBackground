package com.gamecodeschool.escape;

import java.util.Random;

public class BoostSpawnComponent implements SpawnComponent{

    @Override
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {
        Random random = new Random();
        float xPos = 0;

        float yPos = 0;

        xPos = random.nextFloat() * cObjectM.getScreenSize().x - 125;
        yPos = 0;

        cObjectM.setLocation(xPos, yPos);


    }
}


