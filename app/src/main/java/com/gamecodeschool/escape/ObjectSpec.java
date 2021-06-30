package com.gamecodeschool.escape;

import android.graphics.PointF;

abstract class ObjectSpec {
    private String mTag;
    private String mBitmapName;
    private float mSpeed;
    private PointF mSizeScale;
    private String[] mComponents;

    ObjectSpec(String tag, float speed, String bitmapName, PointF relativeScale, String[] components) {
        mTag = tag;
        mSpeed = speed;
        mBitmapName = bitmapName;
        mComponents = components;
        mSizeScale = relativeScale;
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

    PointF getSizeScale() {
        return mSizeScale;
    }

    String[] getComponents() {
        return mComponents;
    }
}
