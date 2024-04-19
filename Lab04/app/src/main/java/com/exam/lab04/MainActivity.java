package com.exam.lab04;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    // create objects for both of the fragments
    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo  = new FragmentTwo ();
    // create a flag variable for keeping track of the current fragment in display
    public int currentFragment = 0;

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
        // create an object of FragmentManager class using the static get method of the parent class
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a transaction object, this commences the transaction as well
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // setting up initial fragment transaction
        fragmentTransaction.add(R.id.main_clayout, fragmentOne);
        // don't forget to change the flag once the fragment is set
        currentFragment = 1;
        // also don't forget to commit the transaction
        fragmentTransaction.commit();

    }

    public void switchFragment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // conditionally replace the fragment object, set the flag and commit
        if(currentFragment == 1){
            fragmentTransaction.replace(R.id.main_clayout, fragmentTwo);
            currentFragment = 2;
        }
        else{
            fragmentTransaction.replace(R.id.main_clayout, fragmentOne);
            currentFragment = 1;
        }
        // committing the transaction
        fragmentTransaction.commit();
    }
}