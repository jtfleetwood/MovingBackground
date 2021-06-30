package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public interface GraphicsComponent {
    void initialize(Context c, ObjectSpec s, PointF objectSize);

    void draw(Canvas canvas, Paint paint, MovementInfo m);
}
