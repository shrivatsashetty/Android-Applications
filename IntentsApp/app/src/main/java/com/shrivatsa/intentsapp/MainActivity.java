package com.shrivatsa.intentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // elements declaration
    EditText userNameHolder;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // typecasting elements
        userNameHolder = findViewById(R.id.user_name_holder);
        btnLogin = findViewById(R.id.btn_login);

        // to make the cursor appear in the username box by default
        userNameHolder.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText = userNameHolder.getText().toString();
                Intent intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                intent.putExtra("userMessage", enteredText);
                startActivity(intent);
            }
        });
    }
}