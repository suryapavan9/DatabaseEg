package com.example.android.databaseeg;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment ff = new FirstFragment();
        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction ft = f.beginTransaction();
        ft.add(R.id.container,ff);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
