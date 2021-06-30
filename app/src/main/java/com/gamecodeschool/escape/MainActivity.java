package com.gamecodeschool.escape;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

// Same as ScrollingShooter..
public class MainActivity extends Activity {
    GameEngine mGameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Display display = getWindowManager().getDefaultDisplay();
        // Not sure why I had to include this as this was from StackOverflow.. I was still getting a title/status bar with the above method call.
        // Think it has to deal with anti-cheat software on my PC interfering with emulator.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point size = new Point();

        display.getSize(size);

        mGameEngine = new GameEngine(this, size);

        setContentView(mGameEngine);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mGameEngine.startThread();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mGameEngine.stopThread();
    }
}