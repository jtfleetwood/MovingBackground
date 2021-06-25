package com.gamecodeschool.escape;

import android.graphics.PointF;
import android.graphics.RectF;

public class MovementInfo {
    // represents x position where two bitmaps that represent background meet.
    private int mXClip;
    // decides which of two bitmaps representing background gets drawn first.
    private boolean mReversedFirst = false;
    // used for collision detection..
    private RectF mCollider;
    // used to move/determine where to draw an object.
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private boolean mHeadingDR = false;
    private boolean mHeadingDL = false;
    private boolean mHeadingBDR = false;
    private boolean mHeadingBDL = false;

    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;

    MovementInfo(float speed, float objectWidth, float objectHeight, PointF startingLocation, PointF screenSize) {
        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;

        if (objectHeight == mScreenSize.y){
            mXClip = 0;
        }
    }

    boolean getReversedFirst() {
        return mReversedFirst;
    }

    void flipReversedFirst() {
        mReversedFirst = !mReversedFirst;
    }

    int getXClip() {
        return mXClip;
    }

    void setXClip(int newXClip) {
        mXClip = newXClip;
    }

    PointF getScreenSize() {
        return mScreenSize;
    }

    void headUp() {
        mHeadingUp = true;
        mHeadingDown = false;
    }

    void headDown() {
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void headRight() {
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headLeft() {
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    boolean headingUp() {
        return mHeadingUp;
    }

    boolean headingDown() {
        return mHeadingDown;
    }

    boolean headingRight() {
        return mHeadingRight;
    }

    boolean headingLeft() {
        return mHeadingLeft;
    }

    boolean headingDR() {
        return mHeadingDR;

    }

    boolean headingDL() {
        return mHeadingDL;
    }

    boolean headingBDR() {
        return mHeadingBDR;
    }

    boolean headingBDL() {
        return mHeadingBDL;
    }

    void headDR() {
        mHeadingDR = true;
        mHeadingBDL = false;
        mHeadingBDR = false;
        mHeadingDL = false;
        mHeadingUp = false;
        mHeadingDown = false;
        mHeadingRight = false;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headDL() {
        mHeadingDR = true;
        mHeadingBDL = false;
        mHeadingBDR = false;
        mHeadingDL = false;
        mHeadingUp = false;
        mHeadingDown = false;
        mHeadingRight = false;
        mHeadingLeft = false;
        mFacingRight = false;
    }

    void headBDR() {
        mHeadingDR = false;
        mHeadingBDL = false;
        mHeadingBDR = true;
        mHeadingDL = false;
        mHeadingUp = false;
        mHeadingDown = false;
        mHeadingRight = false;
        mHeadingLeft = false;
        mFacingRight = true;

    }

    void headBDL() {
        mHeadingDR = false;
        mHeadingBDL = true;
        mHeadingBDR = false;
        mHeadingDL = false;
        mHeadingUp = false;
        mHeadingDown = false;
        mHeadingRight = false;
        mHeadingLeft = false;
        mFacingRight = false;
    }

    // Using / 10 to pull borders in..
    // Using location to update position of object.
    void updateCollider() {
        mCollider.top = mLocation.y + (mObjectHeight / 10);
        mCollider.left = mLocation.x + (mObjectWidth / 10);
        mCollider.bottom = (mCollider.top + mObjectHeight) - (mObjectHeight / 10);
        mCollider.right = (mCollider.left + mObjectWidth) - (mObjectWidth / 10);
    }

    float getObjectHeight() {
        return mObjectHeight;
    }

    void stopVertical(){
        mHeadingDown = false;
        mHeadingUp = false;
    }

    void stopDiagonal() {
        mHeadingDR = false;
        mHeadingDL = false;
        mHeadingBDR = false;
        mHeadingBDL = false;
    }

    void stopHorizontal() {
        mHeadingLeft = false;
        mHeadingRight = false;
    }

    float getSpeed(){
        return mSpeed;
    }

    void setLocation(float horizontal, float vertical) {
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }

    PointF getLocation() {
        return mLocation;
    }

    PointF getSize() {
        return new PointF((int)mObjectWidth, (int)mObjectHeight);
    }

    void flip() {
        mFacingRight = !mFacingRight;
    }

    boolean getFacingRight() {
        return mFacingRight;
    }

    RectF getCollider() {
        return mCollider;
    }

    PointF getFiringLocation(float laserLength) {
        PointF mFiringLocation = new PointF();

        if (mFacingRight) {
            mFiringLocation.x = mLocation.x + (mObjectWidth / 8f);
        }

        else {
            mFiringLocation.x = mLocation.x + (mObjectWidth / 8f) - (laserLength);
        }

        mFiringLocation.y = mLocation.y + (mObjectHeight / 1.28f);

        return mFiringLocation;
    }

}