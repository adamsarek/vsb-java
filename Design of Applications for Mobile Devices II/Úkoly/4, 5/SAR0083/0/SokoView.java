package com.vsb.kru13.sokoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{
    private class Level{
        // Level size
        private int width;
        private int height;

        // Level sprites
        private int sW;
        private int sH;
        private Bitmap[] bmp = new Bitmap[]{
            BitmapFactory.decodeResource(getResources(), R.drawable.empty),
            BitmapFactory.decodeResource(getResources(), R.drawable.wall),
            BitmapFactory.decodeResource(getResources(), R.drawable.box),
            BitmapFactory.decodeResource(getResources(), R.drawable.goal),
            BitmapFactory.decodeResource(getResources(), R.drawable.hero),
            BitmapFactory.decodeResource(getResources(), R.drawable.boxok)
        };

        // Level map
        private final int lX = 10;
        private final int lY = 10;
        private int[] level = {
            1,1,1,1,1,1,1,1,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,4,0,1,0,
            1,0,1,3,2,3,2,0,1,0,
            1,0,2,3,3,2,1,0,1,0,
            1,0,0,0,0,0,0,0,1,0,
            1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0
        };

        // Goals
        private ArrayList<Integer> goals = new ArrayList<>();

        // Hero position
        private int heroX;
        private int heroY;

        // Level touch
        private float tX;
        private float tY;

        // Level state (0 = loading, 1 = started, 2 = ended
        private int state = 0;

        public Level() {
            for(int i = 0; i < level.length; i++) {
                // Get goals from map
                if(level[i] == 3) {
                    goals.add(i);
                }

                // Get hero initial position from map
                if(level[i] == 4) {
                    heroY = i / lX;
                    heroX = i - heroY * lX;
                }
            }

        }

        public void init() {
            state = 1;
        }

        public boolean touch(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    tX = event.getX();
                    tY = event.getY();
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    float tXDiff = event.getX() - tX;
                    float tYDiff = event.getY() - tY;
                    float tXDist = Math.abs(tXDiff);
                    float tYDist = Math.abs(tYDiff);

                    if (tXDist > tYDist) {
                        int dX = tXDiff < 0 ? -1 : 1;

                        if (canMove(dX, 0)) {
                            move(dX, 0);
                        }
                    } else if (tXDist < tYDist) {
                        int dY = tYDiff < 0 ? -1 : 1;

                        if (canMove(0, dY)) {
                            move(0, dY);
                        }
                    }
                    return true;
                }
            }

            return false;
        }

        private boolean canMove(int dX, int dY) {
            if(state != 1) {
                return false;
            }
            // Moving to the wall
            if(level[(heroY + dY) * lX + heroX + dX] == 1) {
                return false;
            }
            // Moving to the box
            else if(level[(heroY + dY) * lX + heroX + dX] == 2 || level[(heroY + dY) * lX + heroX + dX] == 5) {
                // Behind the box is wall
                if(level[(heroY + dY * 2) * lX + heroX + dX * 2] == 1) {
                    return false;
                }
                // Behind the box is box
                else if(level[(heroY + dY * 2) * lX + heroX + dX * 2] == 2 || level[(heroY + dY * 2) * lX + heroX + dX * 2] == 5) {
                    return false;
                }
            }

            return true;
        }

        private void move(int dX, int dY) {
            // Unset previous hero position
            level[heroY * lX + heroX] = goals.contains(heroY * lX + heroX) ? 3 : 0;

            // Set next hero position
            heroX += dX;
            heroY += dY;
            if(level[heroY * lX + heroX] == 2 || level[heroY * lX + heroX] == 5) {
                level[(heroY + dY) * lX + heroX + dX] = level[(heroY + dY) * lX + heroX + dX] == 3 ? 5 : 2;
            }
            level[heroY * lX + heroX] = 4;

            boolean won = true;
            for(int i = 0; i < level.length; i++) {
                if(level[i] == 2) {
                    won = false;
                    break;
                }
            }

            // Won game
            if(won) {
                state = 2;
                Toast.makeText(getContext(), "Game won!", Toast.LENGTH_LONG).show();
            }

            // Redraw level
            invalidate();
        }

        public void changeSize(int w, int h) {
            width = w;
            height = h;

            sW = w / lX;
            sH = h / lY;
        }

        public void draw(Canvas canvas) {
            if(state > 0) {
                for (int i = 0; i < lX; i++) {
                    for (int j = 0; j < lY; j++) {
                        canvas.drawBitmap(bmp[level[i*lX + j]], null,
                            new Rect(j*sW, i*sH,(j+1)*sW, (i+1)*sH), null);
                    }
                }
            }
        }
    }

    Level level = new Level();

    public SokoView(Context context) {
        super(context);
        level.init();
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        level.init();
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        level.init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(level.touch(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        level.changeSize(w, h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        level.draw(canvas);
    }
}
