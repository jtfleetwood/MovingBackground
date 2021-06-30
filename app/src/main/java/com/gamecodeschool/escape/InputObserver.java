package com.gamecodeschool.escape;

import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;

import java.util.ArrayList;

public interface InputObserver {
    void handleInput(MotionEvent event, GameState gs, Pair<RectF, Joystick> controls);
}
