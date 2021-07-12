package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

// GameObject class same as ScrollingShooter for most part.

public class GameObject {
    private MovementInfo movementInfo;
    private boolean isActive = false;
    private String mTag;
    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;
    private SpawnComponent spawnComponent;

    void setSpawner(SpawnComponent s) {
        spawnComponent = s;
    }

    void setGraphics(GraphicsComponent g, Context c, ObjectSpec spec, PointF objectSize) {
        graphicsComponent = g;
        g.initialize(c, spec, objectSize);
    }

    void setMovement(MovementComponent m) {
        movementComponent = m;
    }

    void setInput(InputComponent s) {
        s.setMovementInfo(movementInfo);
    }

    void setMTag(String tag) {
        mTag = tag;
    }

    void setMovementInfo(MovementInfo m) {
        movementInfo = m;
    }

    void draw(Canvas canvas, Paint paint, MovementInfo playerM) {
        graphicsComponent.draw(canvas, paint, movementInfo, playerM);
    }

    void update(long fps, MovementInfo playerM) {
        if (!(movementComponent.move(fps, movementInfo, playerM))) {
            isActive = false;
        }
    }

    boolean spawn(MovementInfo playerM) {
        if (!isActive) {
            spawnComponent.spawn(playerM, movementInfo);
            isActive = true;
            return true;
        }

        return false;
    }

    boolean checkActive() {
        return isActive;
    }

    void setInactive() {
        isActive = false;
    }

    MovementInfo getMovementInfo() {
        return movementInfo;
    }

}