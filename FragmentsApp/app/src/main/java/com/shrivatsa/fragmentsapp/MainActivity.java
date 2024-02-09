package com.shrivatsa.fragmentsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo = new FragmentTwo();
    public int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* creating a fragment manager object to manage all fragment related activities */
        FragmentManager fragmentManager = getSupportFragmentManager(); // the method returns a FragmentManager object
        /* create a fragment transaction object to add, remove fragments etc */
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // initially when the app starts up there are no fragments, therefore we add a fragment at first
        fragmentTransaction.add(R.id.clayout, fragmentOne);
        // a fragment transaction must always be committed for the changes to be saved
        fragmentTransaction.commit();

    }

    /* we have already defined a method to be triggered when the button is clicked in the layout file activity_main.xml */
    public void switchFragment(View view){
        // getSupportFragmentManager() method originally belongs to the superclass AppCompatActivity
        // hence we are able to call it directly without creating an object
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(currentFragment == 1){
            fragmentTransaction.replace(R.id.clayout, fragmentTwo);
            currentFragment = 2;
        }
        else{
            fragmentTransaction.replace(R.id.clayout, fragmentOne);
            currentFragment = 1;
        }
        fragmentTransaction.commit();

    }
}