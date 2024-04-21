package com.practice.lab06;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // element declaration
    EditText mobNo;
    EditText mssgBody;
    Button btnSend;

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

        mobNo = (EditText) findViewById(R.id.mob_no);
        mssgBody = (EditText) findViewById(R.id.mssg_body);
        btnSend = (Button) findViewById(R.id.btn_send);

//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try{
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(mobNo.getText().toString(), null, mssgBody.getText().toString(), null, null);
//                    Snackbar.make(v, "Message Sent Sucessfully", Snackbar.LENGTH_LONG).show();
//                }catch(Exception e){
//                    Snackbar.make(v, "Message Not Sent", Snackbar.LENGTH_LONG).show();
//                }
//            }
//        });

    }

    public void sendSms(View view){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobNo.getText().toString(), null, mssgBody.getText().toString(), null, null);
            Snackbar.make(view, "Message Sent Sucessfully", Snackbar.LENGTH_LONG).show();
        }catch(Exception e){
            Snackbar.make(view, "Message Not Sent", Snackbar.LENGTH_LONG).show();
        }
    }
}