package com.gamecodeschool.escape;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

public class PhysicsEngine {

    boolean update(long fps, ArrayList<GameObject> objects, GameState gs, long currentTime) {

        for (GameObject object : objects) {
            if (object.checkActive()) {
                object.update(fps, objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo());
            }
        }

        return detectCollisions(objects, gs, currentTime);

    }

    boolean detectCollisions(ArrayList<GameObject> objects, GameState gs, long currentTime) {
        if (RectF.intersects(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().getCollider(),
                objects.get(GameObjectContainer.NPC_INDEX).getMovementInfo().getCollider())) {
            gs.loseLife();
            gs.pause();

            if (objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().isBoosted()) {
                objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().resetBoost();
            }

            return true;
        }

        else if(RectF.intersects(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().getCollider(),
                objects.get(GameObjectContainer.BOOST_INDEX).getMovementInfo().getCollider())) {

            if (!objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().isBoosted()) {
                    objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo().setBoosted();
                }

            return false;

        }

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
            gs.increaseScore(currentTime);
            return false;
        }
    }


    // detect collisions


}