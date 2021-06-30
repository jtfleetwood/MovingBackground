package com.gamecodeschool.escape;

import android.graphics.PointF;

// Identical object to the Boost game object, however has different affect on player upon collision. (player loses life)
public class DangerBubbleSpec extends ObjectSpec{
    private static final String mTag = "DangerBubble";
    private static final String mBitmapName = "dirtybubble";
    public static final float mSpeed = 3;
    private static final PointF mSizeScale =  new PointF(20f, 20f);
    private static final String[] mComponents = new String[]{
            "StdGraphicsComponent", "FallObjectMovementComponent", "FallObjectSpawnComponent"

    };
        DangerBubbleSpec() {
            super(mTag, mSpeed, mBitmapName, mSizeScale, mComponents);
        }
};
