package com.gamecodeschool.escape;

import android.graphics.PointF;

public class Joystick {
    private float mRadius;
    private PointF mCenter;
    private double angleInRad;
    private boolean isCircleTouched;
    private PointF vComponent;


    Circle(float x, float y, float r) {
        mRadius = r;
        mCenter = new PointF(x, y);
        vComponent = new PointF();
    }

    PointF getLocation() {
        return mCenter;
    }

    float getRadius() {
        return mRadius;
    }

    boolean isCircleTouched(int x, int y) {
        double delta_x = mCenter.x - x;
        double delta_y = mCenter.y - y;
        double totalDisplacement = Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2));

        if (totalDisplacement > mRadius) {
            return false;
        }

        else {
            return true;
        }

    }

    boolean getCircleTouched() {
        return isCircleTouched;
    }

    void calcAngle(int x, int y) {
        angleInRad = Math.atan2(y - mCenter.y, x - mCenter.x);

    }

    double getAngle() {
        return angleInRad;
    }

    PointF getVelocity(float speed) {
        vComponent.x = (float)Math.cos(angleInRad) * speed;
        vComponent.y = (float)Math.sin(angleInRad) * speed;

        return vComponent;
    }

    void setTouchLift() {
        isCircleTouched = false;
    }

    void setTouchDown() {
        isCircleTouched = true;
    }



}
