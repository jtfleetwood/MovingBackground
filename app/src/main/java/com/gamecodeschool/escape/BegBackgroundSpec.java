package com.gamecodeschool.escape;

import android.graphics.PointF;

public class BegBackgroundSpec extends ObjectSpec {
    private static final String tag = "BeginningBackground";
    private static final String bitmapName = "background";
    private static final float speed = 2f;
    private static final PointF relativeScale = new PointF(1f, 1f);
    private static final String[] components = {
            "BackgroundGraphicsComponent", "BackgroundMovementComponent", "BackgroundSpawnComponent"
    };

    BegBackgroundSpec() {
        super(tag, speed, bitmapName, components);
    }

}
