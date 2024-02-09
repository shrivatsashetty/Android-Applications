package com.shrivatsa.fragmentspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // declare elements
    Button btnSwitchFragment;

    // creating a fragment manager
    FragmentManager fragmentManager = getSupportFragmentManager();
    // create a fragment transaction object
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    // a flag variable to keep track of current fragment being displayed
    int currentFragment;

    // onCreate method, called when the app is launched
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // typecast button
        btnSwitchFragment = findViewById(R.id.btn_switch_fragment);

        // create the references for first and second fragments
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();

        fragmentTransaction.add(R.id.constraint_layout, firstFragment);
        this.currentFragment = 1;
        fragmentTransaction.commit();

        // defining the logic to switch fragment when the button is clicked
        btnSwitchFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     if(currentFragment == 1){
                         fragmentTransaction.replace(R.id.constraint_layout, secondFragment);
                         currentFragment = 2;
                         fragmentTransaction.commit();
                     }
                     else{
                         fragmentTransaction.replace(R.id.constraint_layout, firstFragment);
                         currentFragment = 1;
                         fragmentTransaction.commit();
                     }
            }
        });

    }
}