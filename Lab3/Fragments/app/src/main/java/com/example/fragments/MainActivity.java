package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    Button buttonA;
    Button buttonB;
    Fragment fragmentA;
    Fragment fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        fragmentA = getSupportFragmentManager().findFragmentById(R.id.fragmentA);
        fragmentB = getSupportFragmentManager().findFragmentById(R.id.fragmentB);
        fragmentA.getView().setVisibility(View.INVISIBLE);
        fragmentB.getView().setVisibility(View.INVISIBLE);
    }
    public void onClickButtonA(View view) {
        fragmentA.getView().setVisibility(View.VISIBLE);
        fragmentB.getView().setVisibility(View.INVISIBLE);
    }
    public void onClickButtonB(View view) {
        fragmentA.getView().setVisibility(View.INVISIBLE);
        fragmentB.getView().setVisibility(View.VISIBLE);
    }
}
