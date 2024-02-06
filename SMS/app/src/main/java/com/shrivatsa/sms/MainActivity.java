package com.shrivatsa.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

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
        txtMobileNo = (EditText) findViewById(R.id.to_send_no);
        txtMessage = (EditText) findViewById(R.id.to_send_mssg);
        sendMessageBtn = (Button) findViewById(R.id.btn_send);

    }

    // onClick() method determines the course of action to take when an view is clicked
    public void onClick(View view){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            // the sendTextMessage() method, takes multiple arguments
            smsManager.sendTextMessage(
                    txtMobileNo.getText().toString(), null, txtMessage.getText().toString(), null, null
            );
            // to display success message if sms is sent
            Snackbar.make(view, "SMS Sent Successfully", Snackbar.LENGTH_LONG).show();
        }
        catch(Exception e){
            Snackbar.make(view, "Failed to send SMS", Snackbar.LENGTH_LONG).show();
        }
    }
}