package com.example.sonal.teacherdatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonal.teacherdatabase.java.java.Config1;
import com.example.sonal.teacherdatabase.java.java.webservice.WebService1;

public class User_Detail extends AppCompatActivity {


    private TextView username;
    private TextView password;
    private TextView phoneno;

    private TextView email;

    Button btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__detail);

        setTitle("User Details");
        username=(TextView)findViewById(R.id.username);

        password=(TextView)findViewById(R.id.password);
        phoneno=(TextView)findViewById(R.id.phoneno);

        email=(TextView)findViewById(R.id.email);

        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        System.out.println("hello");

    }
    protected void onResume() {
        super.onResume();


        username.setText(Config1.passenger.para1);
        password.setText(Config1.passenger.para2);
        phoneno.setText(Config1.passenger.para3);

        email.setText(Config1.passenger.para4);
    }

    public void onEdit(View v)
    {

        Intent intent=new Intent(this,User_Update.class);
        startActivity(intent);
    }

    public void onDelete(View v)
    {
        AsyncTaskRunner task=new AsyncTaskRunner();
        task.execute();
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

                String names[]={"Username"};
                String values[]={Config1.passenger.para1};
                resp = web.sendCompleteData(names, values, "user_delete");

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
                Toast("You have successfully deleted notice! ");
                finish();
            }
            else
            {
                Toast("Something went wrong "+status);
            }

            progressDialog.dismiss();
            //finalResult.setText(result);
        }

        @Override
        protected void onPreExecute() {


            progressDialog = ProgressDialog.show(User_Detail.this,
                    "Process",
                    "Please wait...");
        }
        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }
    public void Toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
