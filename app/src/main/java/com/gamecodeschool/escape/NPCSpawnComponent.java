package com.gamecodeschool.escape;

import android.graphics.PointF;

public class NPCSpawnComponent implements SpawnComponent {
    @Override
    public void spawn(MovementInfo playerM, MovementInfo cObjectM) {

        cObjectM.setLocation(cObjectM.getScreenSize().x - cObjectM.getObjectWidth(),
                0 + cObjectM.getObjectHeight());

    }
}
