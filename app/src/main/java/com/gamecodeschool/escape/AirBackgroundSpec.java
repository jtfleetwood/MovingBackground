package com.gamecodeschool.escape;

import android.graphics.PointF;

public class AirBackgroundSpec extends ObjectSpec {
    private static final String tag = "AirBackground";
    private static final String bitmapName = "spaceBackground";
    private static final float speed = 2f;
    private static final PointF relativeScale = new PointF(1f, 1f);
    private static final String[] components = {
            "BackgroundGraphicsComponent", "BackgroundMovementComponent", "BackgroundSpawnComponent"
    };

    AirBackgroundSpec() {
        super(tag, speed, bitmapName, components);
    }
}
