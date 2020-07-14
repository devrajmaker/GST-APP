package com.example.sonal.teacherdatabase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class User_Update extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText phoneno;

    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__update);

        username=(EditText)findViewById(R.id.username);

        password=(EditText)findViewById(R.id.password);

        phoneno=(EditText)findViewById(R.id.phoneno);

        email=(EditText)findViewById(R.id.email);


        username.setText(Config1.passenger.para1);
        password.setText(Config1.passenger.para2);
        phoneno.setText(Config1.passenger.para3);

        email.setText(Config1.passenger.para4);

    }

    public void onSave(View v)
    {
        if(validate()==0)
        {
            String str_username=username.getText().toString().trim();
            String str_password=password.getText().toString().trim();
            String str_phoneno=phoneno.getText().toString().trim();

            String str_email=email.getText().toString().trim();


            String values[]={str_username,str_password,str_phoneno,str_email};
            AsyncTaskRunner task=new AsyncTaskRunner();
            task.execute(values);
        }

    }

    public void onCancel(View v)
    {
        finish();
    }


    public int validate()
    {
        int validate = 0;
        String str_username=username.getText().toString();
        String str_password=password.getText().toString();
        String str_phoneno=phoneno.getText().toString();

        String str_email=email.getText().toString();




        if (str_username.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Username");
            validate = 1;
        }
        if (str_password.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Password");
            validate = 1;
        }
        if (str_phoneno.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Phonenumber");
            validate = 1;
        }

        if (str_email.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Email");
            validate = 1;
        }


        return validate;
    }

    public void Tooat(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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



                String username=jo.getString("Username");
                String password=jo.getString("Password");
                String phoneno=jo.getString("Phoneno");

                String email=jo.getString("Email");






                // Log.e("Data", jo.getString("event_id"));


            }

        }catch (Exception e) {
            Log.e("Webservice 3", e.toString());
            //Tooat(e.toString());
        }

        //return pendingList;
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {


        private String resp;
        ProgressDialog progressDialog;
        private String result1;
        private String result2;
        @Override
        protected String doInBackground(String... params) {
            publishProgress("Process..."); // Calls onProgressUpdate()
            //Tooat("Welcome "+params[0]);
            try {


                result1="";
                result2="";


                WebService1 web=new WebService1();
                //	String result=web.onLogin(username, password);



                    String names[]={"username","password","phoneno","email","username1"};
                    String values[]={params[0],params[1],params[2],params[3],Config1.passenger.para1};
                    resp = web.sendCompleteData(names, values, "user_update");


                //


                Log.d("Values",resp);


            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return result1;
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            //  list_adapter= new ListAdapter_UserPanel(User_SelectPark.this, pendingList);
            // listView.setAdapter(list_adapter);
            //Tooat(decision);
            String status=resp.trim();
            //Tooat(resp);
            //selectAllDatesExists(resp);
            if(status.equals("Done"))
            {
                String str_username=username.getText().toString();
                String str_password=password.getText().toString();
                String str_phoneno=phoneno.getText().toString();

                String str_email=email.getText().toString();

                Config1.passenger.para1=str_username;
                Config1.passenger.para2=str_password;
                Config1.passenger.para3=str_phoneno;

                Config1.passenger.para6=str_email;

                Tooat("You have successfully Updated User Record! ");
                finish();
            }
            else
            {
                Tooat("Something went wrong "+status);
            }

            progressDialog.dismiss();
            //finalResult.setText(result);
        }

        @Override
        protected void onPreExecute() {


            progressDialog = ProgressDialog.show(User_Update.this,
                    "Process",
                    "Please wait...");
        }
        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

}
