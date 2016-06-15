package sample.fabrics.io.study;
// hey guys

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TimerActivity extends AppCompatActivity {

    Button start;
    Button stop;
    TextView timer;
    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 00;
    int hour = 00;
    int milliseconds = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        timer = (TextView) findViewById(R.id.textView);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t == 1) {
                    start.setText("Pause");
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
                    start.setText("Start");
                    timer.setTextColor(Color.BLUE);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startTime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedTime = 0L;
                t = 1;
                secs = 0;
                mins = 00;
                hour = 00;
                milliseconds = 0;
                start.setText("Start");
                handler.removeCallbacks(updateTimer);
                timer.setText("00:00:00");
            }
        });
    }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedTime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            hour = mins / 60;
            timer.setText("" + String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
            timer.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //draws a menu on the android screen
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_plus_button:
                Intent i = new Intent(TimerActivity.this, LogView.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}