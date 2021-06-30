package com.gamecodeschool.escape;

import android.graphics.PointF;

public class HeroSpec extends ObjectSpec {
    private static final String tag = "JetpackHero";
    private static final String bitMapName = "flyingspongebob";
    public static final float speed = 5;
    private static final PointF relativeScale = new PointF(8f, 8f);

    private static final String[] components = new String[] {
            "StdGraphicsComponent", "PlayerMovementComponent", "PlayerSpawnComponent", "PlayerInputComponent"
    };

    HeroSpec() {
        super(tag, speed, bitMapName, relativeScale, components);
    }



}
