package com.gamecodeschool.escape;

import android.graphics.PointF;

public class BoostSpec extends ObjectSpec {
    private static final String mTag = "Boost";
    private static final String mBitmapName = "krabbypatty";
    public static final float mSpeed = 3;
    private static final PointF mSizeScale =  new PointF(20f, 20f);
    private static final String[] mComponents = new String[] {
            "StdGraphicsComponent", "FallObjectMovementComponent", "FallObjectSpawnComponent"
    };

    BoostSpec() {
        super(mTag, mSpeed, mBitmapName, mSizeScale, mComponents);
    }

}
