package com.example.sonal.teacherdatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.sonal.teacherdatabase.java.java.Config1;

public class GstApp extends AppCompatActivity {

private EditText search1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst_app);


    }
    public void food(View view)
    {
        Intent intent=new Intent(this,dfood.class);
        startActivity(intent);
    }
    public void electronic(View view)
    {
        Intent intent=new Intent(this,delectronic.class);
        startActivity(intent);
    }
    public void dailyneeds(View view)
    {
        Intent intent=new Intent(this,ddaily.class);
        startActivity(intent);
    }
    public void others(View view)
    {
        Intent intent=new Intent(this,dothers.class);
        startActivity(intent);
    }
    public void display(View view)
    {
        Intent intent=new Intent(this,Display.class);
        startActivity(intent);
    }
    public void addcart(View view)
    {
        Intent intent=new Intent(this,addcart.class);
        startActivity(intent);
    }
    public void onsearch(View view)
    {
        search1 =(EditText)findViewById(R.id.searchk);
        Config1.pname=search1.getText().toString();
        Intent intent=new Intent(this,searchdis.class);
        startActivity(intent);
    }

}
