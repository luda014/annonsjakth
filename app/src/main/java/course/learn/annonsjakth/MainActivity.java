package course.learn.annonsjakth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Göm SupportActionBar
        getSupportActionBar().hide();
        //Skapa handler för PostDelay
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, botnav.class);
                startActivity(intent);
                finish();
            }
        }, 1400); // swap to 1200
    }
}