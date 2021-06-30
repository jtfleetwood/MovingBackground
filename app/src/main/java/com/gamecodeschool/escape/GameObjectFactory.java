package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.PointF;

// Overall same concept as ScrollingShoooter.
public class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    GameObjectFactory(Context c, PointF screenSize, GameEngine gameEngine) {
        this.mContext = c;
        this.mScreenSize = screenSize;
        mGameEngineReference = gameEngine;
    }
    // Receiving a class that extends the ObjectSpec abstract class.
    GameObject create(ObjectSpec spec) {
        GameObject object = new GameObject();

        // Tells us how many components are in the object we want to create. Not all contain the same (alien vs. background).
        int numComponents = spec.getComponents().length;
        // Hiding newly created object off screen.
        final float HIDDEN = -2000f;

        object.setMTag(spec.getTag());

        // For our game, we have both vertical/horizontal movement suggesting a need for PointF data structure to hold speed components (x/y).
        PointF speed = new PointF();

        speed.x = mScreenSize.x / spec.getSpeed();
        speed.y = mScreenSize.y / spec.getSpeed();

        PointF objectSize = new PointF(mScreenSize.x / spec.getSizeScale().x, mScreenSize.y / spec.getSizeScale().y);

        PointF location = new PointF(HIDDEN, HIDDEN);
        object.setMovementInfo(new MovementInfo(speed, objectSize.x, objectSize.y, location, mScreenSize));

        // Basically, what's happening below - we are iterating through our spec components and adding them to the object.
        // (component by component)

        for (int i = 0; i < numComponents; i++) {
            switch(spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent(mGameEngineReference));


                    break;

                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(), mContext, spec, objectSize);

                    break;

                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());

                    break;


                case "PlayerSpawnComponent":

                    object.setSpawner(new PlayerSpawnComponent());

                    break;


                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(), mContext, spec, objectSize);

                    break;

                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());

                    break;

                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());

                    break;

                case "NPCMovementComponent":
                    object.setMovement(new NPCMovementComponent());

                    break;

                case "NPCSpawnComponent":
                    object.setSpawner(new NPCSpawnComponent());

                    break;

                case "FallObjectMovementComponent":
                    object.setMovement(new FallObjectMovementComponent());

                    break;

                case "FallObjectSpawnComponent":
                    object.setSpawner(new FallObjectSpawnComponent());

                    break;


                default:
                    // Debug print statement that can really help out with debugging when adding new components to Factory.
                    System.out.println(spec.getComponents()[i] + "Was not loaded properly in the object.\n");

                    break;
            }
        }

        return object;
    }
}
