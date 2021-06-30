package com.gamecodeschool.escape;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
// Largely changed due to new functionality within playerInput component.
/*
 Before we were assigning different movement types with bool variables activated within input component, and then sending that
 information into our playerMovementcomponent class. Now, our joystick does all of that for us.. No need for bools. All we
 need to know now is whether the player is facing right/left and is available to move from input component.
 */
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
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    // Used to indicate whether joystick was pressed in input component.
    // Now that we are not designating any fixed movement (left/right) and just moving off angles, bool variables by movement basis won't apply.
    private boolean availableToMove = false;

    // Used for new boost object. Used so boosts are not applied after the player already has one..
    private boolean mBoosted = false;
    // Used to reset the player's speed after boost expires.
    private PointF initSpeed;
    // Used to store vector components for each movement upon touch based off passed in angle from joystick.
    private PointF mVComponents;
    // Used to calculate vector components in coordination with above.
    private PointF mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    // Used to keep joystick functionality out of movement component (separating responsibilities).
    private double mAngle;
    private static PointF mScreenSize;

    MovementInfo(PointF speed, float objectWidth, float objectHeight, PointF startingLocation, PointF screenSize) {
        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
        // Our initial speed is the speed we want to reset too after boost.
        initSpeed = new PointF(mScreenSize.x / HeroSpec.speed, mScreenSize.y / HeroSpec.speed);

        mVComponents = new PointF(0, 0);

        // Initializing x-clip only for the background object.
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

    // Boosting player speed. Occurring within Physics engine.
    void setBoosted() {
        mSpeed.x *= 2;
        mSpeed.y *= 2;

        mBoosted = true;
    }

    // Resetting player speed, after boost and life lost. Occurring within Physics engine.
    void resetBoost() {

        mSpeed.x = initSpeed.x;
        mSpeed.y = initSpeed.y;

        mBoosted = false;
    }

    // Resetting movement before assigning new vector components. Erases previous assignments, so ex: headingRight() is not always true after an instance of it being set.
    // Remember, will not no actual direction of movement until vector components are calculated in movement component. So, can't assign upon input as previously.
    void resetMovement() {
        mHeadingLeft = false;
        mHeadingRight = false;
    }

    // Let's us know to not double boost object.
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

    // Returns vector movement components.
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

    // Used to let our PlayerMovementcomponent know if joystick was just touched.
    void setMovementAvailability() {
        availableToMove = true;
    }

    // Used to let our playerMovementComponent know joystick was released and movement can stop.
    void resetMovementAvailability() {
        availableToMove = false;
    }

    // See above.
    boolean isAvailableToMove() {
        return availableToMove;
    }

    // setting vector movement components based off passed in angle from playerInputComponent.
    void setVComponents() {
        mVComponents.x = (float)Math.cos(mAngle) * mSpeed.x;
        mVComponents.y = (float)Math.sin(mAngle) * mSpeed.y;
    }

    // Passed in by Joystick object from playerinput component. Already discussed.
    void setAngle(double angle) {
        mAngle = angle;
    }

    RectF getCollider() {
        return mCollider;
    }


}