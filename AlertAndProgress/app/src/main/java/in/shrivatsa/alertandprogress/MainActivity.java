package in.shrivatsa.alertandprogress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Declaration of elements */
    Button exitButton;

    // Declare one more button to switch activity
    Button switchActivityButton;

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //type casting button object
        exitButton = findViewById(R.id.btn_exit);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert");

                // for positive response lke yes , go ahead
                builder.setPositiveButton("Yes", (dialog, which) ->  finish() );
                // for negative response like no, don't exit
                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                // for neutral response like no, go back
                builder.setNeutralButton("Go Back", (dialog, which) -> { dialog.cancel(); } );

                // to make the builder display the message to user
                builder.setMessage("Are you sure to exit the app!!");

                // to avoid cancel on clicking on the empty area
                builder.setCancelable(false);

                // to make an alert logo to appear
                builder.setIcon(R.drawable.alert_logo);


                // to make the dialog box to show up
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        /* Code for Progress bar */

        // type cast progressbar
        ProgressBar progressBar = findViewById(R.id.progress_bar);

        // type cast text view
        textView = findViewById(R.id.percent_counter);


        // create a new handler object but we are not typecasting it since we are going to program it
        Handler handler = new Handler();

        // create a thread to carry out the process
        new Thread(new Runnable() {

            // declare a int variable to keep track of loading status
            int progressStatus = 0;

            @Override
            public void run() {

                // while loop start
                while (this.progressStatus < 100){
                    this.progressStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(String.valueOf(progressStatus));
                        }
                    });
                    // to make the progressbar to be making progress
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){ e.printStackTrace(); }
                } // while loop end

            }
        }).start();

        /* Code for switching activity and intents */
        // typecast
        switchActivityButton = findViewById(R.id.switch_activity_btn);

        switchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Constructor of Intent class takes two activity files to make out from which activity to move to which activity
                Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
                startActivity(intent); // to start the activity
            }
        });

    }
    
}