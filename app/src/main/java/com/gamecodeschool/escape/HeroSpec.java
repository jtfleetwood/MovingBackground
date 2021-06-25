package com.gamecodeschool.escape;

import android.graphics.PointF;

public class HeroSpec extends ObjectSpec {
    private static final String tag = "JetpackHero";
    private static final String bitMapName = "character";
    private static final float speed = 5f;
    private static final PointF relativeScale = new PointF(15f, 15f);

    private static final String[] components = new String[] {
            "StdGraphicsComponent", "HeroMovementComponent", "HeroSpawnComponent", "HeroInputComponent"
    };

    HeroSpec() {
        super(tag, speed, bitMapName, components);
    }



}
