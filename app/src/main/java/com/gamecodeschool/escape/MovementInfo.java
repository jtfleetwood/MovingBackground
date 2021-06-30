package com.gamecodeschool.escape;

import android.graphics.Point;
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
    private boolean availableToMove = false;
    private boolean mBoosted = false;
    private PointF initSpeed;
    private PointF mVComponents;
    private PointF mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private double mAngle;
    private static PointF mScreenSize;

    MovementInfo(PointF speed, float objectWidth, float objectHeight, PointF startingLocation, PointF screenSize) {
        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
        initSpeed = new PointF(mScreenSize.x / HeroSpec.speed, mScreenSize.y / HeroSpec.speed);

        mVComponents = new PointF(0, 0);

        if (objectHeight == mScreenSize.y){
            mXClip = 100;
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

    void setBoosted() {
        mSpeed.x *= 2;
        mSpeed.y *= 2;

        mBoosted = true;
    }

    void resetBoost() {

        mSpeed.x = initSpeed.x;
        mSpeed.y = initSpeed.y;

        mBoosted = false;
    }

    void resetMovement() {
        mHeadingLeft = false;
        mHeadingRight = false;
    }

    boolean isBoosted() {
        return mBoosted;
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


    boolean headingRight() {
        return mHeadingRight;
    }

    boolean headingLeft() {
        return mHeadingLeft;
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

    float getObjectWidth() {
        return mObjectWidth;
    }

    PointF getVComponents() {
        return mVComponents;
    }


    PointF getSpeed(){
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

    boolean getFacingRight() {
        return mFacingRight;
    }

    void setFacingRight() {
        mFacingRight = true;
    }

    void setFacingLeft() {
        mFacingRight = false;
    }

    void setMovementAvailability() {
        availableToMove = true;
    }

    void resetMovementAvailability() {
        availableToMove = false;
    }

    boolean isAvailableToMove() {
        return availableToMove;
    }

    void setVComponents() {
        mVComponents.x = (float)Math.cos(mAngle) * mSpeed.x;
        mVComponents.y = (float)Math.sin(mAngle) * mSpeed.y;
    }

    void setAngle(double angle) {
        mAngle = angle;
    }

    RectF getCollider() {
        return mCollider;
    }


}