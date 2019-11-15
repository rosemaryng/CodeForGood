package org.ytt.code4good;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import android.view.inputmethod.InputMethodManager;
import android.content.Context;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Game1 extends AppCompatActivity {

    private TextView categoryText;
    private TextView answerText;
    private TextView answersDisplay;
    private TextView timerText;
    private ImageView R_Circle;
    private ImageView L_Circle;

    int count;
    Boolean gameRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game1);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        R_Circle = findViewById(R.id.R_Circle);
        L_Circle = findViewById(R.id.L_Circle);
        categoryText = findViewById(R.id.categoryText);
        answerText = findViewById(R.id.answerText);
        answersDisplay = findViewById(R.id.answersDisplay);
        timerText = findViewById(R.id.timer);


        categoryText.setText("Category: Movies");
        answerText.setHint("Answer");

        fiveSecondTimer.start();
        gameRunning = true;

        L_Circle.setVisibility(View.VISIBLE); // start with L circle displaying
        R_Circle.setVisibility(View.INVISIBLE);
        //updateAnswer();
        answerText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                answersDisplay.append(answerText.getText().toString() + " ");   // Update answer
                reset();           // swicth player, change yellow circle display, and clear text

                return true;
            }
        });

    }

    // Used to reset input & timer for next player's round
    private void reset() {
        answerText.setText("");

        if (L_Circle.getVisibility() == View.VISIBLE) {
            R_Circle.setVisibility(View.VISIBLE);
            L_Circle.setVisibility(View.INVISIBLE);
        } else {
            L_Circle.setVisibility(View.VISIBLE);
            R_Circle.setVisibility(View.INVISIBLE);
        }

        fiveSecondTimer.start();         // reset timer

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    private void updateAnswer() {
        answerText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                answersDisplay.append(answerText.getText().toString() + " ");   // Update answer
                reset();           // swicth player, change yellow circle display, and clear text

                return true;
            }
        });
    }


    //Count down timer
    final Timer fiveSecondTimer = new Timer(11000, 10) {
        public void onTick(long millisUntilFinished) {
            count = (int) millisUntilFinished / 50;

            int minutes = (int) (millisUntilFinished / 1000) / 60;
            int seconds = (int) (millisUntilFinished / 1000) % 60;

            String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            ImageView timesUp = findViewById(R.id.timesUp);

            timesUp.setVisibility(View.INVISIBLE);

            timerText.setText(timeFormatted);


            if (count == 0) {
                gameRunning = false;
                timesUp.setVisibility(View.VISIBLE);

                // Current player loses
            }
        }

        public void onFinish() {
            // call function to display text and reset things
        }
    }.start();


    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
