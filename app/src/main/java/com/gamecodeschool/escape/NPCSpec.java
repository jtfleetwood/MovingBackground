package com.gamecodeschool.escape;

import android.graphics.PointF;

public class NPCSpec extends ObjectSpec{
    private static final String tag = "NPC";
    private static final String bitMapName = "flyingdutchman";
    private static final float speed = 15;
    private static final PointF relativeScale = new PointF(8f, 8f);

    private static final String[] components = new String[] {
            "StdGraphicsComponent", "NPCMovementComponent", "NPCSpawnComponent"
    };

    NPCSpec() {
        super(tag, speed, bitMapName, relativeScale, components);
    }



}
