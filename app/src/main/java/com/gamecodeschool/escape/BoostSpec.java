package com.gamecodeschool.escape;

import android.graphics.PointF;

// Specification for our Boost Game Object that will affect player speed upon collision.
public class BoostSpec extends ObjectSpec {
    private static final String mTag = "Boost";
    private static final String mBitmapName = "krabbypatty";
    public static final float mSpeed = 3;
    // Wanted the object to be pretty small as this is a reward and should be challenging to attain by the player.
    private static final PointF mSizeScale =  new PointF(20f, 20f);
    // New components added for the object below.
    private static final String[] mComponents = new String[] {
            "StdGraphicsComponent", "FallObjectMovementComponent", "FallObjectSpawnComponent"
    };

    BoostSpec() {
        super(mTag, mSpeed, mBitmapName, mSizeScale, mComponents);
    }

}
