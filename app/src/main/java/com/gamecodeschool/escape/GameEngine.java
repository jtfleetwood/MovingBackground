package com.gamecodeschool.escape;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements GameEngineBroadcaster, Runnable {
    private ArrayList<InputObserver> mObservers = new ArrayList();

    public void addObserver(InputObserver o) {
        mObservers.add(o);
    }
}
