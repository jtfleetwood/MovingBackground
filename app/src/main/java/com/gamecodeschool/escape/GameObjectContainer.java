package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

public class GameObjectContainer {
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int NPC_INDEX = 2;
    public static final int BOOST_INDEX = 3;
    public static final int BUBBLE_INDEX = 4;
    private ArrayList<GameObject> objects;

    public GameObjectContainer(Context c, PointF screenSize, GameEngine ge) {
        objects = new ArrayList<>();

        GameObjectFactory factory = new GameObjectFactory(c, screenSize, ge);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory) {
        objects.clear();

        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));
        objects.add(PLAYER_INDEX, factory.create(new HeroSpec()));
        objects.add(NPC_INDEX, factory.create(new NPCSpec()));
        objects.add(BOOST_INDEX, factory.create(new BoostSpec()));
        objects.add(BUBBLE_INDEX, factory.create(new DangerBubbleSpec()));


        return objects;
    }

    ArrayList<GameObject> getGameObjects() {
        return objects;
    }

}
