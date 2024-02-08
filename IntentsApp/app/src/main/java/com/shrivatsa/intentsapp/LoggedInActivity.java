package com.shrivatsa.intentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        textView = findViewById(R.id.textView2);

        Intent recievedIntent = getIntent(); // to fetch the incoming intent object
        String receivedMessage = recievedIntent.getStringExtra("userMessage"); // be careful to provide the same key as an argument
        textView.setText(receivedMessage);

    }
}