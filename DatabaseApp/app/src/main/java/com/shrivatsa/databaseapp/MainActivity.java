package com.shrivatsa.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //declaration
    EditText userName;
    EditText passWord;
    Button btnLogin;
    Button btnCancel;

    /* we create a separate class to take care of SQL operations */
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // typecasting the elements
        userName = findViewById(R.id.username_holder);
        passWord = findViewById(R.id.password_holder);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = userName.getText().toString();
                String password = passWord.getText().toString();

                // check if the user id exist in the DB
                int id  = checkUser(new User(name, password));

                if (id == -1){
                    Snackbar.make(view, "User " + name + " does not exist", Snackbar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(view, "User " + name + "found", Snackbar.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(loginIntent);
                }
            }
        });

        DbHandler dbHandler = new DbHandler(MainActivity.this);
        dbHandler.addUser(new User("Shri", "Shetty"));
        dbHandler.addUser(new User("Vatsa", "S"));
        dbHandler.addUser(new User("Gnu", "Linux"));

    }
    public int checkUser(User user){
        return dbHandler.checkUser(user);
    }

}