package com.shrivatsa.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // elements declaration
    EditText txtMobileNo ;
    EditText txtMessage;
    Button sendMessageBtn;


    /* every android activity starts with an onCreate() method which is called when the app is launched */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // typecasting
        txtMessage = (EditText) findViewById(R.id.to_send_mssg);
        txtMobileNo = (EditText) findViewById(R.id.to_send_no);
        sendMessageBtn = (Button) findViewById(R.id.btn_send);

    }
    public void onClick(View view){
        
    }
}