package com.gamecodeschool.escape;

import android.content.Context;
import android.content.SharedPreferences;

public class GameState {
    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;
    private static volatile boolean mJustLostLife = false;
    private static volatile int numGames;

    private GameStarter gameStarter;
    private int mScore;
    private int mHighScore;
    private int mLives;
    private long mGameStartTime;

    private SharedPreferences.Editor mEditor;

    GameState (GameStarter gs, Context context) {
        // reference to the memory location of the respawn/de-spawn method in game engine.
        gameStarter = gs;
        SharedPreferences prefs;

        // Assigns access to a file - "HiScore", specifies private access.
        // If the file does not exist, then we will create a new one.
        // Local because we only need to get the file once. Upon start-up.
        prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        // Editor of above file.
        // Used everytime a player gets a new high score.
        mEditor = prefs.edit();

        //Looks for variable or 'label' in the above file, and if it is not found - assigns default 0 value.
        // Can retrieve a wide-array of variables.
        mHighScore = prefs.getInt("hi_score", 0);
    }

    private void endGame() {
        // Sets these to true, so any other part of the game that needs to know of these will know. (Loop/GameEngine)
        mGameOver = true;
        mPaused = true;

        // Simply comparing current score to high score.
        if (mScore > mHighScore) {
            mHighScore = mScore;

            // Writing to the file we created earlier, with a label and value (can retrieve later).
            // Can write a wide-array of variables.
            mEditor.putInt("hi_score", mHighScore);

            // Writes the change to the file.
            mEditor.commit();
        }
    }



    void startNewGame() {
        mScore = 0;
        mLives = 3;
        // Stopping drawing because of the object update that has to occur below.
        stopDrawing();

        // Triggers execution of deSpawnRespawn method for our GameEngine object.
        gameStarter.deSpawnReSpawn();

        resume();
        mGameStartTime = System.currentTimeMillis();

        startDrawing();

    }

    void loseLife() {
        mLives--;
        mJustLostLife = true;

        if (mLives == 0) {
            numGames++;
            pause();
            endGame();
        }
    }

    boolean wasLifeLost() {
        return mJustLostLife;
    }


    int getLives(){

        return mLives;

    }

    void increaseScore(long currentTime){

        if (((currentTime - mGameStartTime) % 1000) < 25) {
            mScore++;
        }

    }

    int getNumGames() {
        return numGames;
    }

    int getScore(){

        return mScore;

    }

    int getHighScore(){

        return mHighScore;

    }

    void pause(){

        mPaused = true;

    }


    void resume(){
        mGameOver = false;
        mPaused = false;
        mJustLostLife = false;

    }

    void stopEverything(){

        mPaused = true;

        mGameOver = true;

        mThreadRunning = false;

    }

    boolean getThreadRunning(){

        return mThreadRunning;

    }

    void startThread(){

        mThreadRunning = true;

    }

    private void stopDrawing(){

        mDrawing = false;

    }

    private void startDrawing(){

        mDrawing = true;

    }

    boolean getDrawing() {

        return mDrawing;

    }

    boolean getPaused(){

        return mPaused;

    }

    boolean getGameOver(){

        return mGameOver;

    }


}
