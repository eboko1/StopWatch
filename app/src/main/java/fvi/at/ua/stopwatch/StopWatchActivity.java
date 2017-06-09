package fvi.at.ua.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends Activity {
    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning;
    private  static final String SECONDS_KEY = "seconds";
    private  static final String RUNNING_KEY = "running";
    private  static final String WAS_RUNNING_KEY = "wasRunning";
    private TextView time_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        init();

       if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            running = savedInstanceState.getBoolean(RUNNING_KEY);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING_KEY);
            Log.i("BUNDLE","!null" +" seconds " + seconds + " RUN " + running + " wasRUN " + wasRunning );
        } else {
            Log.i("BUNDLE","null");
        }
        runTimer();
    }
     @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SECONDS_KEY, seconds);
        savedInstanceState.putBoolean(RUNNING_KEY, running);
        savedInstanceState.putBoolean(WAS_RUNNING_KEY, wasRunning);
        Log.i("BUNDLE","saved seconds "+ seconds+" running " +running + " wasRunning "  + wasRunning );
    }

    public void init(){
        time_tv = (TextView) findViewById(R.id.time_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {

        running = false;

    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        running = true;
    }

    private void runTimer() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minute = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format("%d:%02d:%02d", hour, minute, sec);
                time_tv.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
