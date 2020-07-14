package com.example.sonal.teacherdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Product_Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__add);
    }
    public void product(View view)
    {
        Intent intent=new Intent(this,pdisplay.class);
        startActivity(intent);
    }
}
