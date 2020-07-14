package com.example.sonal.teacherdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void register(View v)
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }


    public void display(View v)
    {
        Intent intent=new Intent(this,Display.class);
        startActivity(intent);
    }


}
