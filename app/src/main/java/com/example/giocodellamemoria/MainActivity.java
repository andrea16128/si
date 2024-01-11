package com.example.giocodellamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ock_easy(View view) {
        Intent easyMode = new Intent(MainActivity.this, EasyModeActivity.class);
        startActivity(easyMode);
    }

    public void ock_medium(View view) {
        Intent MediumMode = new Intent(MainActivity.this, MediumModeActivity.class);
        startActivity(MediumMode);
    }

    public void ock_hard(View view) {
        Intent hardMode = new Intent(MainActivity.this, HardModeActivity.class);
        startActivity(hardMode);
    }
    public void ock_5f(View view) {
        Intent classeMode = new Intent(MainActivity.this, ClasseModeActivity.class);
        startActivity(classeMode);
    }
}