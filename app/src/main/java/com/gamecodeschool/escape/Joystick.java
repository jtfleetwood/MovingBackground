package com.gamecodeschool.escape;

import android.graphics.PointF;

/*
    Joystick class is essentially a wrapped circle object. We let the HUD class have responsibility for drawing, probably
    should not have done that - but again, this is a framework and not everything has to be perfect.
 */
public class Joystick {
    // Radius of joystick
    private float mRadius;
    // Center location of joystick (circle)
    private PointF mCenter;

    // Initializing joystick size (radius) and center location.
    Joystick(float x, float y, float r) {
        mRadius = r;
        mCenter = new PointF(x, y);
    }

    // Used to calculate needed angles for playerMovement component and movementInfo.
    PointF getLocation() {
        return mCenter;
    }

    float getRadius() {
        return mRadius;
    }

    /*
        Simply comparing touch point to center of circle using total displacement. If total displacement is greater than
        radius, then we know circle was not touched.
     */
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

    /*
        Below, calculating angle by converting rectangular coordinates to polar and computing theta (angle) by finding
        arctan of y/x. This angle will allow us to compute different components of player velocity that mimic player touches
        relative to center of circle.
     */
    double calcAngle(int x, int y) {
        double angleInRad = Math.atan2(y - mCenter.y, x - mCenter.x);

        return angleInRad;

    }


}
