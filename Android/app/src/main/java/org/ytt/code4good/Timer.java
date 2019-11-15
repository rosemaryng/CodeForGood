package org.ytt.code4good;


import android.os.CountDownTimer;

public abstract class Timer {
    private long millisInFuture;
    private long countDownInterval;
    private long millisRemaining;

    CountDownTimer countDownTimer = null;

    private boolean isPaused = true;

    public Timer(long millisInFuture, long countDownInterval) {
        super();
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        this.millisRemaining = this.millisInFuture;
    }

    private void createCountDownTimer(long setTimeTo){
        countDownTimer = new CountDownTimer(setTimeTo,countDownInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                millisRemaining = millisUntilFinished;
                Timer.this.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Timer.this.onFinish();
            }
        };
    }
    /**
     * Callback fired on regular interval.
     *
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);
    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();
    /**
     * Cancel the countdown.
     */
    public final void cancel(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        this.millisRemaining = 0;
    }


    /**
     * Resume the countdown. If the countdown is already running, do nothing
     * @return CountDownTimerPausable current instance
     */
    public synchronized final Timer resume(){
        if(isPaused) {
            createCountDownTimer(millisRemaining);
            countDownTimer.start();
            isPaused = false;
        }
        return this;
    }

    // start the timer from the initial countdown time (I.e. reset)
    public synchronized final Timer start() {
        cancel();
        createCountDownTimer(millisInFuture);
        countDownTimer.start();
        isPaused = false;
        return this;
    }

    /**
     * Pauses so it could be resumed with resume()
     * later from the same point where it was paused.
     */
    public void pause()throws IllegalStateException{
        if(!isPaused){
            countDownTimer.cancel();
        } else{
            throw new IllegalStateException("CountDownTimerPausable is already in pause state, start counter before pausing it.");
        }
        isPaused = true;
    }

    public boolean isPaused() {
        return isPaused;
    }
}

