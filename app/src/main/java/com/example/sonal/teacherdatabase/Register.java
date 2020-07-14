package com.example.sonal.teacherdatabase;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sonal.teacherdatabase.java.java.webservice.WebService1;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText phoneno;
    private EditText email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Sign Up");

        Initialized_Data();

    }


    private void Initialized_Data() {

        username=(EditText)findViewById(R.id.id);

        password=(EditText)findViewById(R.id.pass);

        phoneno=(EditText)findViewById(R.id.phone);

        email=(EditText)findViewById(R.id.email);





    }


    public void save(View v )
    {
        if(validate()==0)
        {

            String str_username=username.getText().toString();

            String str_password=password.getText().toString();
            String str_phoneno=phoneno.getText().toString();


            String str_email=email.getText().toString();

            String names[]={str_username,str_password,str_phoneno,str_email};

            AsyncTaskRunner task=new AsyncTaskRunner();
            task.execute(names);


        }
    }


   public void cancel(View v)
        {
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
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
            Tooat("Please provide User Name");
            validate = 1;
        }
        if (str_password.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Password");
            validate = 1;
        }
        if (str_phoneno.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Phone Number");
            validate = 1;
        }

        if (str_email.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Email ID");
            validate = 1;
        }


        return validate;
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



                String names[]={"username","password","phoneno","email"};
                String values[]={params[0],params[1],params[2],params[3]};
                //   resp = web.sendCompleteData(null, null, "retrieve_events");
                WebService1 web=new WebService1();
                resp = web.sendCompleteData(names, values, "user_save");
                //result1=resp;
                Log.d("Values",resp);


            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return result1;
        }

        @Override
        protected void onPostExecute(String result) {

            String status=resp.trim();


            if(status.equals("Done")) {
                Tooat("You have successfully saved data");
                finish();
            }
            else if(status.equals("Already"))
            {
                Tooat("User already exists");
            }
            else
            {
                Tooat("Something went wrong! : "+status);
            }
            progressDialog.dismiss();
            //finalResult.setText(result);
        }



        @Override
        protected void onPreExecute() {


            progressDialog = ProgressDialog.show(Register.this,"Process",
                    "Please wait...");
        }
        @Override
        protected void onProgressUpdate(String... text) {

        }
    }




    public void Tooat(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }





}
