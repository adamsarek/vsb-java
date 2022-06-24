package com.example.snake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private GameView gameView;
    private Canvas canvas;

    // Frame
    private long lastFrameTime = 0;
    private final int fps = 5;

    // Screen
    private int screenWidth;
    private int screenHeight;

    // Game sizes
    private final int blockMargin = 10;
    private final int gameTopBarHeight = 80;
    private final int gameBottomBarHeight = 240;
    private final int gameWidth = 20;
    private int gameHeight;
    private int gameTopMargin;
    private int gameLeftMargin;
    private int blockSize;

    // Game
    private String playerName;
    private Apple apple;
    private Snake snake;
    private CanvasText scoreText;
    private CanvasText playerText;
    private CanvasText timeText;
    private CanvasButton muteButton;
    private int score = 0;
    private long resumeTime = 0;
    private long totalTime = 0;

    private Thread playThread = null;
    private volatile boolean playing = false;
    private volatile boolean muted = false;
    private volatile boolean touched = false;
    private float sTX;
    private float sTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load
        sp = getSharedPreferences("Game", Context.MODE_PRIVATE);
        playerName = sp.getString("playerName", null);

        gameView = new GameView(this);
        setContentView(gameView);

        resume();
    }

    private void resume() {
        playing = true;
        resumeTime = System.currentTimeMillis();
        playThread = new Thread(gameView);
        playThread.start();
    }

    private void end() {
        playing = false;
        totalTime += (System.currentTimeMillis() - resumeTime);
    }

    private void openHighscores() {
        Intent highscoresIntent = new Intent(GameActivity.this, HighscoresActivity.class);
        startActivity(highscoresIntent);
        finish();
    }

    private void mute() {
        muted = true;
        muteButton.setIcon(getResources().getDrawable(R.drawable.unmute, null));
    }

    private void unmute() {
        muted = false;
        muteButton.setIcon(getResources().getDrawable(R.drawable.mute, null));
    }

    private void toggleMuteState() {
        if(muted) {
            unmute();
        }
        else {
            mute();
        }
    }

    private String getDisplayTime() {
        long time = totalTime;
        if(playing) {
            time += (System.currentTimeMillis() - resumeTime);
        }

        long h = time / (60 * 60 * 1000);   time -= h * 60 * 60 * 1000;
        long m = time / (60 * 1000);        time -= m * 60 * 1000;
        long s = time / 1000;

        String H = (h > 0 ? h + ":" : "");
        String M = (h > 0 && m < 10 ? "0" + m : m) + ":";
        String S = (s < 10 ? "0" + s : String.valueOf(s));

        return H + M + S;
    }

    private void update() {
        if(!snake.move()) {
            end();
        }

        Block appleBlock = apple.getBlock();
        Block[] snakeBlocks = snake.getBlocks();
        if(snakeBlocks[0].getX() == appleBlock.getX() && snakeBlocks[0].getY() == appleBlock.getY()) {
            snake.eat();
            score++;
            apple = new Apple(gameWidth, gameHeight, snakeBlocks);
        }

        scoreText.setText(String.valueOf(score));
        timeText.setText(getDisplayTime());
    }

    private void redraw() {
        gameView.postInvalidate(0,0,gameView.getWidth(), gameView.getHeight());
    }

    private class GameView extends SurfaceView implements Runnable{
        private SurfaceHolder holder;

        private GameView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void onWindowFocusChanged(boolean hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
            if(hasWindowFocus) {
                screenWidth = gameView.getMeasuredWidth();
                screenHeight = gameView.getMeasuredHeight();

                // Calculate blockSize and gameHeight
                blockSize = (screenWidth - blockMargin * (gameWidth + 1)) / gameWidth;
                gameLeftMargin = (screenWidth - (gameWidth * (blockSize + blockMargin) + blockMargin)) / 2;
                gameHeight = (screenHeight - gameTopBarHeight - gameBottomBarHeight - blockMargin) / (blockSize + blockMargin);
                gameTopMargin = gameTopBarHeight + (screenHeight - gameTopBarHeight - gameBottomBarHeight - (gameHeight * (blockSize + blockMargin) + blockMargin)) / 2;

                // Spawn objects
                snake = new Snake(gameWidth, gameHeight);
                apple = new Apple(gameWidth, gameHeight, snake.getBlocks());

                // Create texts
                scoreText = new CanvasText(
                    gameLeftMargin + blockMargin,
                    gameTopMargin / 2,
                    "0",
                    Color.argb(255, 187, 187, 187),
                    Paint.Align.LEFT,
                    40
                );
                playerText = new CanvasText(
                    screenWidth / 2,
                    gameTopMargin / 2,
                    playerName,
                    Color.WHITE,
                    Paint.Align.CENTER,
                    40
                );
                timeText = new CanvasText(
                    screenWidth - gameLeftMargin - blockMargin,
                    gameTopMargin / 2,
                    getDisplayTime(),
                    Color.argb(255, 187, 187, 187),
                    Paint.Align.RIGHT,
                    40
                );

                // Create buttons
                muteButton = new CanvasButton(
                    screenWidth / 2 - gameBottomBarHeight / 4,
                    screenHeight - gameBottomBarHeight + gameBottomBarHeight / 4 - blockMargin,
                    gameBottomBarHeight / 2,
                    gameBottomBarHeight / 2,
                    getResources().getDrawable(R.drawable.mute, null),
                    Color.argb(255, 68, 68, 68),
                    blockMargin,
                    new Callback() {
                        @Override
                        public void invoke() {
                            toggleMuteState();
                        }
                    }
                );

                setWillNotDraw(false);
            }
        }

        @Override
        public void run() {
            while(playing) {
                long currentFrameTime = System.currentTimeMillis();
                long elapsedFrameTime = currentFrameTime - lastFrameTime;
                long sleepTime = (long)(1000f / fps - elapsedFrameTime);

                canvas = null;
                try {
                    canvas = holder.lockCanvas();

                    if(canvas == null) {
                        playThread.sleep(1);

                        continue;
                    }
                    else if(sleepTime > 0) {
                        playThread.sleep(sleepTime);

                        synchronized(holder) {
                            update();
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    if(canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                        redraw();
                        lastFrameTime = System.currentTimeMillis();
                    }
                }
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int x, y, w, h;

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);

            // Background
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);

            // Draw texts
            scoreText.draw(canvas);
            playerText.draw(canvas);
            timeText.draw(canvas);

            // Draw buttons
            muteButton.draw(canvas);

            // Grid
            paint.setColor(Color.argb(255, 34, 34, 34));
            for(int i = 0; i < gameWidth; i++) {
                for(int j = 0; j < gameHeight; j++) {
                    x = gameLeftMargin + blockMargin + (blockSize + blockMargin) * i;
                    y = gameTopMargin + blockMargin + (blockSize + blockMargin) * j;
                    canvas.drawRoundRect(x, y, x + blockSize, y + blockSize, blockMargin, blockMargin, paint);
                }
            }

            // Apple
            paint.setColor(Color.argb(255, 34, 221, 34));
            Block appleBlock = apple.getBlock();
            x = gameLeftMargin + blockMargin + (blockSize + blockMargin) * appleBlock.getX();
            y = gameTopMargin + blockMargin + (blockSize + blockMargin) * appleBlock.getY();
            canvas.drawRoundRect(x, y, x + blockSize, y + blockSize, blockMargin, blockMargin, paint);

            // Snake
            Block[] snakeBlocks = snake.getBlocks();
            for(int i = 0; i < snakeBlocks.length; i++) {
                paint.setColor(Color.argb(127 + 128 / snakeBlocks.length * (snakeBlocks.length - i), 255, 255, 255));
                x = gameLeftMargin + blockMargin + (blockSize + blockMargin) * snakeBlocks[i].getX();
                y = gameTopMargin + blockMargin + (blockSize + blockMargin) * snakeBlocks[i].getY();
                canvas.drawRoundRect(x, y, x + blockSize, y + blockSize, blockMargin, blockMargin, paint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            final float eTX = event.getX();
            final float eTY = event.getY();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(muteButton.isTouched(eTX, eTY)) { touched = muteButton.touchDown(); }
                    else { touched = true; }

                    if(touched) {
                        sTX = eTX;
                        sTY = eTY;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if(muteButton.isTouched(sTX, sTY)) {
                        if(muteButton.isTouched(eTX, eTY)) { touched = muteButton.touchUp(); }
                        else { touched = muteButton.touchDownReset(); }
                    }
                    else {
                        final float dTX = sTX - eTX;
                        final float dTY = sTY - eTY;

                        if(Math.abs(dTX) > Math.abs(dTY)) {
                            if(dTX > 0) { snake.changeDirection(3); }
                            else { snake.changeDirection(1); }
                        }
                        else if(Math.abs(dTX) < Math.abs(dTY)) {
                            if(dTY > 0) { snake.changeDirection(0); }
                            else { snake.changeDirection(2); }
                        }
                    }
                    break;
            }

            if(touched) {
                redraw();
                return true;
            }

            return super.onTouchEvent(event);
        }
    }
}