package com.sp.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class HelloWorld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

}

