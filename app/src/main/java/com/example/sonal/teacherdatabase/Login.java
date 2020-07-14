package com.example.sonal.teacherdatabase;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sonal.teacherdatabase.java.java.Config1;
import com.example.sonal.teacherdatabase.java.java.webservice.WebService1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Login extends AppCompatActivity {

    private EditText username1;
    private EditText password1;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }


        username1=(EditText)findViewById(R.id.username1);
        password1=(EditText)findViewById(R.id.password1);
    }

    public void onLogin(View v)
    {

        Log.d("Come","1");
        String Username=username1.getText().toString();
        String Password=password1.getText().toString();
        Log.d("Come","2");
        int furthur=0;
        if(Username.trim().equals(""))
        {
            Tooat("Please enter username");
            furthur=1;
        }
        if(Password.trim().equals("") && furthur==0)
        {
            Tooat("Please enter password");
            furthur=1;
        }

        if(furthur==0) {

            String[] values={Username,Password};
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute(values);
        }
    }


    public void onSetting(View v)
    {
        Intent intent=new Intent(this,Setting.class);
        startActivity(intent);
    }

    public void onRegister(View v)
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
    public void selectAllDatesExists(String json)
    {

        //Tooat(json);
        try {
            Log.e("Data", json);
            JSONArray ja = new JSONArray(json);
            JSONObject jo = null;

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
            Calendar cal = Calendar.getInstance();

            for(int i=0; i<ja.length(); i++) {

                jo = ja.getJSONObject(i);

                String Username=jo.getString("Username");
               // String flatno=jo.getString("flatno");
                String Password=jo.getString("Password");
               // String user_type=jo.getString("user_type");
                Config1.uname=Username.toString();

                Tooat("You have successfully Logged In!");
                if(Username.trim().equals("admin") &&Password.trim().equals("admin") )
                {
                    Intent intent = new Intent(getApplicationContext(), Product_Add.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(getApplicationContext(), GstApp.class);
                    startActivity(intent);

                }



            }

        }catch (Exception e) {
            Tooat("Either Username or Passowrd is not incorrect");
            Log.e("Webservice 3", e.toString());
            Tooat(e.toString());
        }

        //return pendingList;
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Process..."); // Calls onProgressUpdate()
            try {

               // String username=edit_username.getText().toString();
               // String password=edit_password.getText().toString();

                String[] values={params[0],params[1]};
                String[] names={"Username","Password"};
                //Log.d("Come","1");
                WebService1 web=new WebService1();
                resp=web.sendCompleteData(names,values,"validate_user");
                //resp=web.onLogin(username, password);
                //Log.d("Come","2"+result);




            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {


            // execution of result of Long time consuming operation
            progressDialog.dismiss();

            String status=resp.trim();
            //Tooat(resp);
            selectAllDatesExists(resp);
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(Login.this,
                    "Process",
                    "Please wait...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

    public void Tooat(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
