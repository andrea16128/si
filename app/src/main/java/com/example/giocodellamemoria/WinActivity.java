package com.example.giocodellamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

    }

    public void ock_restart(View view) {
        switch (Actions.getId_activity()){
            case 1:
                Intent easyModeActivity = new Intent(WinActivity.this, EasyModeActivity.class);
                startActivity(easyModeActivity);
                finish();
                break;
            case 2:
                Intent mediumModeActivity = new Intent(WinActivity.this, MediumModeActivity.class);
                startActivity(mediumModeActivity);
                finish();
                break;
            case 3:
                Intent hardModeActivity = new Intent(WinActivity.this, HardModeActivity.class);
                startActivity(hardModeActivity);
                finish();
                break;
            case 4:
                Intent classeModeActivity = new Intent(WinActivity.this, ClasseModeActivity.class);
                startActivity(classeModeActivity);
                finish();
                break;
        }
    }

    public void ock_next(View view) {
        Intent mainActivity = new Intent(WinActivity.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}