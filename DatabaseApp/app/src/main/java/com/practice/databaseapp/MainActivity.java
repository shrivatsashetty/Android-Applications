package com.practice.databaseapp;

import android.content.Intent;
import android.os.Bundle;
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
    // container to store username
    EditText usernameHolder;
    // container to store password
    EditText passwordHolder;
    // Login & Cancel button
    Button btnLogin;
    Button btnCancel;

    DbHandler dbHandler = new DbHandler(MainActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_clayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // type casting
        usernameHolder = findViewById(R.id.edit_text_username);
        passwordHolder = findViewById(R.id.edit_text_password);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);

        // add some users to database during onCreate()
        dbHandler.addUser(new User("Shrivatsa", "2102"));
        dbHandler.addUser(new User("Ubuntu", "mca"));
        dbHandler.addUser(new User("GNU", "linux"));

        // setting the onClick behaviour of the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the user inputs from the widgets
                String username = usernameHolder.getText().toString();
                String password = passwordHolder.getText().toString();

                // a flag variable to check if the user exists in the DB
                int userExists = dbHandler.checkUser(new User(username, password));

                if(userExists == -1){
                    Snackbar.make(v, "User Not Found!!!", Snackbar.LENGTH_LONG).show();
                }
                else {
                    // if user is found create a new Intent object to switch from MainActivity.this to LoginActivity.cl
                    Snackbar.make(v, "User Found", Snackbar.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });



    }
}