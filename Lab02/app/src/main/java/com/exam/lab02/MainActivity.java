package com.exam.lab02;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // declaration of the widgets
    Button btnExit;
    ProgressBar pgBar;
    TextView pgStatusView;
    // declaring an integer to keep track of progress status
    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* progressbar part */
        pgBar = findViewById(R.id.pg_bar);
        pgStatusView = findViewById(R.id.pg_status);

        // create a thread handler
        Handler threadHandler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    progressStatus += 1;

                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pgBar.setProgress(progressStatus);
                            pgStatusView.setText(String.valueOf(progressStatus));
                        }
                    });

                    // to make the progressbar to progress slowly
                    try{
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        ).start(); // to start the progressbar thread

        /* alert dialog box */
        btnExit = (Button) findViewById(R.id.btn_exit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Alert!!!");
                builder.setMessage("Are you sure you want to exit ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (dialog, which) -> { finish(); });
                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                builder.setNeutralButton("Cancel", ((dialog, which) -> dialog.cancel()));
                builder.setIcon(R.drawable.alert_logo);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }
}