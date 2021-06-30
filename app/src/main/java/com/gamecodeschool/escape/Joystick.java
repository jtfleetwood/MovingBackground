package com.gamecodeschool.escape;

import android.graphics.PointF;

public class Joystick {
    private float mRadius;
    private PointF mCenter;


    Joystick(float x, float y, float r) {
        mRadius = r;
        mCenter = new PointF(x, y);
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

    double calcAngle(int x, int y) {
        double angleInRad = Math.atan2(y - mCenter.y, x - mCenter.x);

        return angleInRad;

    }


}
