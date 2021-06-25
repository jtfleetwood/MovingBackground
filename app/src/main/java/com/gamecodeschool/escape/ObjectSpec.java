package com.gamecodeschool.escape;

import android.graphics.PointF;

abstract class ObjectSpec {
    private String mTag;
    private String mBitmapName;
    private float mSpeed;
    private float mSizeScale;
    private String[] mComponents;

    ObjectSpec(String tag, float speed, String bitmapName, String[] components) {
        mTag = tag;
        mSpeed = speed;
        mBitmapName = bitmapName;
        mComponents = components;
    }


    String getTag() {
        return mTag;
    }

    String getBitMapName() {
        return mBitmapName;
    }

    float getSpeed() {
        return mSpeed;
    }

    float getSizeScale() {
        return mSizeScale;
    }

    String[] getComponents() {
        return mComponents;
    }
}
