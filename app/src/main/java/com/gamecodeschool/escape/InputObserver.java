package com.gamecodeschool.escape;

import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

public interface InputObserver {
    void handleInput(MotionEvent event, GameState gs, ArrayList<RectF> controls);
}
