package com.gamecodeschool.escape;

import android.graphics.PointF;

// Simple implementation.
public class NPCSpawnComponent implements SpawnComponent {
    @Override

    // We are giving the player a break, and spawning the NPC to top/right of screen to give player time to adjust upon start :)
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {

        cObjectM.setLocation(cObjectM.getScreenSize().x - cObjectM.getObjectWidth(),
                0 + cObjectM.getObjectHeight());

    }
}
