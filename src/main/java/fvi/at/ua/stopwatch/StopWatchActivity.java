package fvi.at.ua.stopwatch;

import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.RenamingDelegatingContext;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {
    private  int seconds = 0;
    private boolean running = false;
    private  boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);


        Button start = (Button)findViewById(R.id.start_button);
        Button stop = (Button)findViewById(R.id.stop_button);
        Button reset = (Button)findViewById(R.id.reset_button);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    public  void  onSaveInstanceState(Bundle sevedInstanseState){


    }
    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        super.onStop();
        wasRunning = running;
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final  TextView time_view = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){

            @Override
            public void run() {
                int hour = seconds / 3600;
                int minute = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format("%d:%02d:%02d", hour, minute, sec);
                time_view.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }





}
