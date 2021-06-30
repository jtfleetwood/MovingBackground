package com.gamecodeschool.escape;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import androidx.core.util.ObjectsCompat;

import java.util.ArrayList;
import java.util.Random;

// Overall, same exact set-up as ScrollingShooter.
// Implementing GameEngineBroadcaster to send touch information to our input observers (UI/Player Input)
public class GameEngine extends SurfaceView implements GameEngineBroadcaster, GameStarter, Runnable {
    private Thread mThread = null;
    private long mFPS;
    private GameState mGameState;
    private ArrayList<InputObserver> inputObservers = new ArrayList();
    private Random random;
    UIController mUIController;
    HUD mHud;
    Renderer mRenderer;
    PhysicsEngine mPhysicsEngine;
    GameObjectContainer objectContainer;


    /*
    Note: Context = interactions with your overall operating environment (files, databases, etc.)
          Activity = interactions with the Android OS. The activity itself is what the user is seeing and interacting with.
     */
    // Passing in reference back to our main activity.
    public GameEngine(Context context, Point size) {
        super(context);
        mUIController = new UIController(this);
        mGameState = new GameState(this, context);
        mHud = new HUD(size);
        mRenderer = new Renderer(this);
        mPhysicsEngine = new PhysicsEngine();
        objectContainer = new GameObjectContainer(context, new PointF(size.x, size.y), this);

        // Used to randomly spawn falling objects. Did not want to continually initialize this variable within a method.
        // May not be truly random..
        random = new Random();

    }

    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }

    @Override
    public void run() {

        while (mGameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> objects = objectContainer.getGameObjects();

            if (!mGameState.getPaused()) {
                // Randomly spawning falling objects.
                randomSpawnBoost();
                // If collision occurred, respawn all objects and pause. Allows for better game flow, especially with chasing.
                // Tracking points within Physics engine too as we are comparing the game start time within GameStarter to the passed in current time.
                // Point system revolves around amount of seconds staying alive (not exact seconds - may be off by ~30 ms) - will explain.
                if (mPhysicsEngine.update(mFPS, objects, mGameState, frameStartTime)) {
                    deSpawnReSpawn();

                }
            }

            mRenderer.draw(objects, mGameState, mHud);

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;

                mFPS = MILLIS_IN_SECOND / timeThisFrame;

            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // Upon touch event, sending information to our input observers, so proper operations can follow.
        for (InputObserver o : inputObservers) {
            o.handleInput(motionEvent, mGameState, mHud.getControls());
        }


        return true;
    }

    public void stopThread() {

        mGameState.stopEverything();

        try {
            mThread.join();
        } catch(InterruptedException e) {
            Log.e("Exception", "stopThread()" + e.getMessage());
        }
    }

    public void startThread() {
        // Our class is a runnable object - implements Runnable interface.
        mGameState.startThread();
        mThread = new Thread(this);

        mThread.start();
    }

    @Override
    public void deSpawnReSpawn() {
        ArrayList<GameObject> objects = objectContainer.getGameObjects();

        for (GameObject o : objects) {
            o.setInactive();
        }

        // Only spawning Player, Background, and NPC upon new Round/Game start.
        objects.get(objectContainer.PLAYER_INDEX).spawn(objects.get(objectContainer.PLAYER_INDEX).getMovementInfo());


        objects.get(objectContainer.BACKGROUND_INDEX).spawn(objects.get(objectContainer.PLAYER_INDEX).getMovementInfo());
        objects.get(GameObjectContainer.NPC_INDEX).spawn(objects.get(objectContainer.PLAYER_INDEX).getMovementInfo());


    }

    // Randomly spawning falling objects.. The Danger Bubble/Boost object.
    public void randomSpawnBoost() {
        ArrayList<GameObject> objects = objectContainer.getGameObjects();

        // Assigning probability below..
        int SPAWN_BOOST = 5;
        int SPAWN_DBUBBLE = 25;
        int TOTAL_PROB = 100;
        int TEST_SPAWN = random.nextInt(TOTAL_PROB);

        // If randomly chosen to spawn, then spawn... Same follows for bubble object.
        if (TEST_SPAWN == SPAWN_BOOST) {
            objects.get(GameObjectContainer.BOOST_INDEX).spawn(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo());
        }

        else if (TEST_SPAWN == SPAWN_DBUBBLE) {
            objects.get(GameObjectContainer.BUBBLE_INDEX).spawn(objects.get(GameObjectContainer.PLAYER_INDEX).getMovementInfo());
        }

    }


}
