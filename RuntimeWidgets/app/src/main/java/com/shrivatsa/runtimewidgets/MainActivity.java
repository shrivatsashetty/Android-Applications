package com.shrivatsa.runtimewidgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtn;
    Switch stateSwitch;
    ConstraintLayout innerConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // view casting
        imgBtn = findViewById(R.id.img_btn);
        stateSwitch = findViewById(R.id.state_switch);
        innerConstraintLayout = findViewById(R.id.inner_constraint_layout);

        stateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    imgBtn.setEnabled(true); // to control the states of image button
                    stateSwitch.setText("Enabed");
                }
                else {
                    imgBtn.setEnabled(false);
                    stateSwitch.setText("Disabled");
                }
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Button Pressed", Snackbar.LENGTH_LONG ).show();
            }
        });
        // while creating a calendar view we have to mention it where to appear inside it's constructor
        CalendarView calendarView = new CalendarView(getApplicationContext());
        // to create a constraint parameters object
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // now we call the container inside which we want to put our widget
        innerConstraintLayout.addView(calendarView, layoutParams);

    }
}