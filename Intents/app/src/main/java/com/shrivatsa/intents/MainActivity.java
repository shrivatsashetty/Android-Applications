package com.shrivatsa.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Declaration of widgets
    EditText toSendText;
    Button switchActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // typecasting
        toSendText = findViewById(R.id.input_text);
        switchActivityBtn = findViewById(R.id.intent_btn);

        switchActivityBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /* get the text the user has typed, numerals are not texts
                   since user may also type numerals we have to explicitly convert to string */
                String toSendMessage = toSendText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent); // to start the intent
                /* assign the intent object with an extra task of carrying the stringMessage
                * we also need to give a name to the stringMessage to identify it */
                intent.putExtra("Message", toSendMessage);
            }
        });
    }
}