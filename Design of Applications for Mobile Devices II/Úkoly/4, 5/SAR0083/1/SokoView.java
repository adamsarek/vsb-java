package com.vsb.kru13.sokoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{
    private class Game{
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
            BitmapFactory.decodeResource(getResources(), R.drawable.boxok),
            BitmapFactory.decodeResource(getResources(), R.drawable.hero)
        };

        // Levels
        private int[][][] levels;
        private int levelCount;
        private int levelId = 0;
        private int lX = 0;
        private int lY = 0;
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

        public Game() {}

        public void start() {
            setLevel();

            state = 1;
        }

        private void end() {
            state = 2;
            Toast.makeText(getContext(), "Game won!", Toast.LENGTH_LONG).show();

            if(levelId + 1 < levelCount) {
                levelId++;

                start();
            }
        }

        private void setLevel() {
            // Set level sizes
            lY = levels[levelId].length;
            lX = levels[levelId][0].length;

            // Get level
            level = new int[lY * lX];
            for(int i = 0; i < lY; i++) {
                for(int j = 0; j < lX; j++) {
                    level[i * lX + j] = levels[levelId][i][j];
                }
            }

            // Get goals
            for(int i = 0; i < level.length; i++) {
                // Get goals from map
                if(level[i] == 3 || level[i] == 6) {
                    goals.add(i);
                }

                // Get hero initial position from map
                if(level[i] == 4) {
                    heroY = i / lX;
                    heroX = i - heroY * lX;
                }
            }

            // Change size
            sW = width / lX;
            sH = height / lY;

            // Redraw level
            invalidate();
        }

        public void addLevel(int[][] level, int w, int h) {
            int[][][] newLevels = new int[levelCount+1][][];

            int i = 0;
            // Copy existing levels
            for(; i < levelCount; i++) {
                newLevels[i] = levels[i];
            }

            // Add new level
            newLevels[i] = level;

            // Set new levels
            levels = newLevels;

            levelCount++;
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

            // Redraw level
            invalidate();

            // Won game
            if(won) {
                end();
            }
        }

        public void changeSize(int w, int h) {
            width = w;
            height = h;

            sW = w / lX;
            sH = h / lY;
        }

        public void draw(Canvas canvas) {
            if(state > 0) {
                for (int i = 0; i < lY; i++) {
                    for (int j = 0; j < lX; j++) {
                        canvas.drawBitmap(bmp[level[i*lX + j]], null,
                            new Rect(j*sW, i*sH,(j+1)*sW, (i+1)*sH), null);
                    }
                }
            }
        }
    }

    // Game
    Game game = new Game();

    public SokoView(Context context) {
        super(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLevels(String rawLevels) {
        for(String rawLevel : rawLevels.split("\\r\\n\\r\\n")){
            int w = 0;
            int h = 0;

            for(String rawLevelLine : rawLevel.split("\\r\\n")) {
                if(rawLevelLine.length() > w) {
                    w = rawLevelLine.length();
                }
                h++;
            }

            int[][] level = new int[h][w];

            int y = 0;
            int x = 0;
            int pads = 0;
            for(int i = 0; i < rawLevel.length(); i++) {
                switch(rawLevel.charAt(i)) {
                    case ' ':
                        level[y][x] = 0;
                        x++;
                        break;
                    case '#':
                        level[y][x] = 1;
                        x++;
                        break;
                    case '$':
                        level[y][x] = 2;
                        x++;
                        break;
                    case '.':
                        level[y][x] = 3;
                        x++;
                        break;
                    case '@':
                        level[y][x] = 4;
                        x++;
                        break;
                    case '*':
                        level[y][x] = 5;
                        x++;
                        break;
                    case '+':
                        level[y][x] = 6;
                        x++;
                        break;
                    case '\r':
                        int pad = (y + 1) * w - (i + pads) + (y * 2);
                        pads += pad;
                        for(int j = 0; j < pad; j++) {
                            level[y][x + j] = 0;
                        }
                        y++;
                        x = 0;
                        break;
                }
            }

            game.addLevel(level, w, h);
        }

        game.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(game.touch(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        game.changeSize(w, h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        game.draw(canvas);
    }
}
