package com.gamecodeschool.escape;

import android.graphics.PointF;

// Specification for our Background game object.
// No changes vs. Scrolling Shooter.
public class BackgroundSpec extends ObjectSpec {
    private static final String tag = "BeginningBackground";
    private static final String bitmapName = "bikinibottom";
    private static final float speed = 2f;
    private static final PointF relativeScale = new PointF(1f, 1f);
    private static final String[] components = {
            "BackgroundGraphicsComponent", "BackgroundMovementComponent", "BackgroundSpawnComponent"
    };

    BackgroundSpec() {
        super(tag, speed, bitmapName, relativeScale, components);
    }

}
