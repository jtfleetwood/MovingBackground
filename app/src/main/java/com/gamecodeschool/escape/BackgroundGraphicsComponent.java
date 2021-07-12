package com.gamecodeschool.escape;

import android.content.Context;
import android.graphics.*;

// Overall, same idea as BackgroundGraphics component within ScrollingShooter.

public class BackgroundGraphicsComponent implements GraphicsComponent{
    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;
    private PointF mPlayerPadding;

    @Override
    public void initialize(Context c, ObjectSpec s, PointF objectSize) {
        int resID = c.getResources().getIdentifier(s.getBitMapName(), "drawable", c.getPackageName());
        mBitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)objectSize.x, (int)objectSize.y, false);

        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        mPlayerPadding = new PointF(objectSize.x / 8, objectSize.y / 8);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, MovementInfo m, MovementInfo playerMovement) {

        int xClip = m.getXClip();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int startY = 0;
        int endY = (int)m.getScreenSize().y + 20;
        PointF playerLocation = playerMovement.getLocation();
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;


        if ((playerLocation.x - mPlayerPadding.x) <= 0) {
            left = 0;
            right = (int)(left + (mPlayerPadding.x * 2));
        }

        else if ((playerLocation.x + mPlayerPadding.x) >= width) {
            right = width;
            left = (int)(right - (mPlayerPadding.x * 2));
        }

        else {
            right = (int)(playerLocation.x + mPlayerPadding.x);
            left = (int)(playerLocation.x - mPlayerPadding.x);
        }

        if ((playerLocation.y + mPlayerPadding.y) > width) {
            bottom = 0;
            top = (int)(bottom - (mPlayerPadding.y * 2));
        }

        else if ((playerLocation.y - mPlayerPadding.y) <= 0) {
            top = 0;
            bottom = (int)(top + (mPlayerPadding.y * 2));
        }

        else {
            top = (int)(playerLocation.y - mPlayerPadding.y);
            bottom = (int)(playerLocation.y + mPlayerPadding.y);
        }


        Rect fromRect1 = new Rect(left, top, right, bottom);
        Rect toRect1 = new Rect(0, 0, width, height);
        canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);
        System.out.println(top - bottom);



    }
}
