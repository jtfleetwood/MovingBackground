package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.*;

public class StdGraphicsComponent implements GraphicsComponent {
    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;

    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().getIdentifier(s.getBitMapName(), "drawable", c.getPackageName());

        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        // Scaling the bitmap object to the correct size for the game object.
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)objectSize.x, (int)objectSize.y, false);

        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);

        // Now we can show any game object instance in a left or right-facing state.
        mBitmapReversed = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, MovementInfo m) {
        if (m.getFacingRight()) {
            canvas.drawBitmap(mBitmap, m.getLocation().x, m.getLocation().y, paint);
        }

        else {
            canvas.drawBitmap(mBitmapReversed, m.getLocation().x, m.getLocation().y, paint);
        }
    }
}
