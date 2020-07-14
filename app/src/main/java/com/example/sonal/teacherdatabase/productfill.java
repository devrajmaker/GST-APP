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

public class productfill extends AppCompatActivity {

    private EditText pid;
    private EditText pname;
    private EditText mrp;
    private EditText gst;
    private EditText ptype;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productfill);

        setTitle("FILL FORM");

        Initialized_Data();

    }


    private void Initialized_Data() {

        pid=(EditText)findViewById(R.id.pid);

        pname=(EditText)findViewById(R.id.pname);

        mrp=(EditText)findViewById(R.id.mrp);

        gst=(EditText)findViewById(R.id.gst);

        ptype=(EditText)findViewById(R.id.ptype);



    }


    public void save(View v )
    {
        if(validate()==0)
        {

            String str_pid=pid.getText().toString();

            String str_pname=pname.getText().toString();
            String str_mrp=mrp.getText().toString();


            String str_gst=gst.getText().toString();
            String str_ptype=ptype.getText().toString();


            String names[]={str_pid,str_pname,str_mrp,str_gst,str_ptype};

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
        String str_pid=pid.getText().toString();
        String str_pname=pname.getText().toString();
        String str_mrp=mrp.getText().toString();


        String str_gst=gst.getText().toString();
        String str_ptype=ptype.getText().toString();


        if (str_pid.trim().equals("") && validate==0 )
        {
            Tooat("Please provide pid");
            validate = 1;
        }
        if (str_pname.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Pname");
            validate = 1;
        }
        if (str_mrp.trim().equals("") && validate==0 )
        {
            Tooat("Please provide MRP");
            validate = 1;
        }

        if (str_gst.trim().equals("") && validate==0 )
        {
            Tooat("Please provide GST");
            validate = 1;
        }
        if (str_ptype.trim().equals("") && validate==0 )
        {
            Tooat("Please provide ptype");
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



                String names[]={"pid","pname","mrp","gst","ptype"};
                String values[]={params[0],params[1],params[2],params[3],params[4]};
                //   resp = web.sendCompleteData(null, null, "retrieve_events");
                WebService1 web=new WebService1();
                resp = web.sendCompleteData(names, values, "product_save");
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
                Tooat("Teacher already exists");
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


            progressDialog = ProgressDialog.show(productfill.this,"Process",
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
