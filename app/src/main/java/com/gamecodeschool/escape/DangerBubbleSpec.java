package com.gamecodeschool.escape;

import android.graphics.PointF;

public class TrapSpec {
    private static final String mTag = "DangerBubble";
    private static final String mBitmapName = "dirtybubble";
    public static final float mSpeed = 3;
    private static final PointF mSizeScale =  new PointF(20f, 20f);
    private static final String[] mComponents = new String[]{
            "StdGraphicsComponent", "BoostMovementComponent", "BoostSpawnComponent"

    };
        TrapSpec() {
            super(mTag, mSpeed, mBitmapName, mSizeScale, mComponents);
        }
};
