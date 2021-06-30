package com.gamecodeschool.escape;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

// Largely same as scrolling shooter, however detecting for specific collisions rather than inefficiently comparing each.
public class PhysicsEngine {

    boolean update(long fps, ArrayList<GameObject> objects, GameState gs, long currentTime) {

        for (GameObject object : objects) {
            if (object.checkActive()) {
                object.update(fps, objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo());
            }
        }

        return detectCollisions(objects, gs, currentTime);

    }

    // Time passed in to award point if second has passed and no collision with NPC occurred.
    boolean detectCollisions(ArrayList<GameObject> objects, GameState gs, long currentTime) {
        // Checking if player collided with NPC.
        // Losing life and pausing if so, hence custom views provided upon each life lost.
        if (RectF.intersects(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().getCollider(),
                objects.get(GameObjectContainer.NPC_INDEX).getMovementInfo().getCollider())) {
            gs.loseLife();
            gs.pause();

            // Resetting player boost as player loses boost upon losing a life.
            if (objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().isBoosted()) {
                objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().resetBoost();
            }

            return true;
        }

        // If player "catches" a boost, then setting boost, and if already set, then nothing.
        else if(RectF.intersects(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().getCollider(),
                objects.get(GameObjectContainer.BOOST_INDEX).getMovementInfo().getCollider())) {

            if (!objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().isBoosted()) {
                    objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().setBoosted();
                }

            return false;

        }

        // Checking for Danger bubble collision as if the collision occurs, the player loses a life and loses boost consequently.
        else if(RectF.intersects(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().getCollider(),
                objects.get(GameObjectContainer.BUBBLE_INDEX).getMovementInfo().getCollider())) {
            gs.loseLife();
            gs.pause();

            if (objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().isBoosted()) {
                objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().resetBoost();
            }

            return true;
        }

        else {
            // Increasing score if second has passed relative to gameStart and currentFrameTime comparison.
            // When I say second passed, I'm referring to modulo usage comparing gameStartTime % 1000ms..
            gs.increaseScore(currentTime);
            return false;
        }
    }


    // detect collisions


}