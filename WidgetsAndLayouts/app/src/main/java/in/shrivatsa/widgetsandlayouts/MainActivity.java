package in.shrivatsa.widgetsandlayouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // declaration of different components(views)
    Button toastButton, snackbarButton;
    Switch controlSwitch;
    ImageButton redBtn;

    // declare a variable for the nested constraint layout
    ConstraintLayout innerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiating a button object and typecasting
        toastButton = findViewById(R.id.toast_btn);

        // functionalities for toast message
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toast Pressed!!", Toast.LENGTH_LONG).show();
            }
        });


        // typecasting
        snackbarButton = findViewById(R.id.snackbar_btn);

        //  functionalities for snack bar button
        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack bar Pressed!!", Snackbar.LENGTH_LONG).show();
            }
        });

        // programming the control switch
        controlSwitch = findViewById(R.id.on_off_switch);
        // invoking the setter method of onCheckedChangedListener class
        controlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    controlSwitch.setText("Enabled");
                    redBtn.setEnabled(true);
                }
                else{
                    controlSwitch.setText("Disabled");
                    redBtn.setEnabled(false);
                }
            }
        });

        redBtn = findViewById(R.id.red_btn);

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Big Red Button Clicked!!!", Snackbar.LENGTH_LONG).show();
            }
        });

        /* Code for inner constraint layout */

        // typecasting
        innerLayout = findViewById(R.id.inner_layout);

        CalendarView calendarWidget = new CalendarView(getApplicationContext());
        ConstraintLayout.LayoutParams layoutParams= new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        innerLayout.addView(innerLayout, layoutParams);

    }
}