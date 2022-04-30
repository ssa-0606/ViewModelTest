package com.example.viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.viewmodeltest.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_contain, ListFragment.class,null)
                .commit();
    }
}