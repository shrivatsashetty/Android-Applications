package com.shrivatsa.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // declare views
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.recieved_text);

        // declare a new intent object to get the passed message
        Intent intent = getIntent(); // returns the intent object that started this activity
        String toRecieveMessage = intent.getStringExtra("Message");
        textView.setText(toRecieveMessage);
    }
}