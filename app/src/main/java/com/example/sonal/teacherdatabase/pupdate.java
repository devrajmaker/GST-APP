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

public class pupdate extends AppCompatActivity {

    private EditText pid;
    private EditText pname;
    private EditText mrp;

    private EditText gst;

    private EditText ptype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupdate);

        pid=(EditText)findViewById(R.id.pid);

        pname=(EditText)findViewById(R.id.pname);

        mrp=(EditText)findViewById(R.id.mrp);

        gst=(EditText)findViewById(R.id.gst);
        ptype=(EditText)findViewById(R.id.ptype);
System.out.println("Sooo"+Config1.passenger.para1);
        System.out.println("Sooo"+Config1.passenger.para2);
        System.out.println("Sooo"+Config1.passenger.para3);
        System.out.println("Sooo"+Config1.passenger.para4);
        System.out.println("Sooo"+Config1.passenger.para5);
        pid.setText(Config1.passenger.para1);
        pname.setText(Config1.passenger.para2);
        mrp.setText(Config1.passenger.para3);
        gst.setText(Config1.passenger.para4);
        ptype.setText(Config1.passenger.para5);


    }

    public void onSave(View v)
    {
        if(validate()==0)
        {
            String str_pid=pid.getText().toString().trim();
            String str_pname=pname.getText().toString().trim();
            String str_mrp=mrp.getText().toString().trim();

            String str_gst=gst.getText().toString().trim();
            String str_ptype=ptype.getText().toString().trim();


            String values[]={str_pid,str_pname,str_mrp,str_gst,str_ptype};
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
            Tooat("Please provide pname");
            validate = 1;
        }
        if (str_mrp.trim().equals("") && validate==0 )
        {
            Tooat("Please provide mrp");
            validate = 1;
        }

        if (str_gst.trim().equals("") && validate==0 )
        {
            Tooat("Please provide gst");
            validate = 1;
        }
        if (str_ptype.trim().equals("") && validate==0 )
        {
            Tooat("Please provide ptype");
            validate = 1;
        }



        return validate;
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



                String pid=jo.getString("P_ID");
                String pname=jo.getString("P_name");
                String mrp=jo.getString("MRP");

                String gst=jo.getString("GST");
                String ptype=jo.getString("P_type");






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



                String names[]={"pid","pname","mrp","gst","ptype","pid1"};
                String values[]={params[0],params[1],params[2],params[3],params[4],Config1.passenger.para1};
                System.out.println("bags"+params[0]+" "+params[1]+" "+params[2]+" "+params[3]+" "+params[4]+" "+Config1.passenger.para1);
                resp = web.sendCompleteData(names, values, "product_update");


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
                String str_pid=pid.getText().toString();
                String str_pname=pname.getText().toString();
                String str_mrp=mrp.getText().toString();

                String str_gst=gst.getText().toString();
                String str_ptype=ptype.getText().toString();

                Config1.passenger.para1=str_pid;
                Config1.passenger.para2=str_pname;
                Config1.passenger.para3=str_mrp;

                Config1.passenger.para4=str_gst;
                Config1.passenger.para5=str_ptype;

                Tooat("You have successfully Updated Record! ");
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


            progressDialog = ProgressDialog.show(pupdate.this,
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
