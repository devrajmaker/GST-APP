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

public class cart_update extends AppCompatActivity {

    private EditText username;
    private EditText pid;
    private EditText ptype;

    private EditText mrp;
    private EditText gst;
    private EditText qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_update);

        username=(EditText)findViewById(R.id.username);

        pid=(EditText)findViewById(R.id.pid);

        ptype=(EditText)findViewById(R.id.ptype);

        mrp=(EditText)findViewById(R.id.mrp);
        gst=(EditText)findViewById(R.id.gst);
        qty=(EditText)findViewById(R.id.qty);

        username.setText(Config1.passenger.para1);
        pid.setText(Config1.passenger.para2);
        ptype.setText(Config1.passenger.para3);

        mrp.setText(Config1.passenger.para4);

        gst.setText(Config1.passenger.para5);
        qty.setText(Config1.passenger.para6);


    }

    public void onSave(View v)
    {
        if(validate()==0)
        {
            String str_username=username.getText().toString().trim();
            String str_pid=pid.getText().toString().trim();
            String str_ptype=ptype.getText().toString().trim();

            String str_mrp=mrp.getText().toString().trim();
            String str_gst=gst.getText().toString().trim();
            String str_qty=qty.getText().toString().trim();




            String values[]={str_username,str_pid,str_ptype,str_mrp,str_gst,str_qty};
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
        String str_pid=pid.getText().toString();
        String str_ptype=ptype.getText().toString();

        String str_mrp=mrp.getText().toString();
        String str_gst=gst.getText().toString();
        String str_qty=qty.getText().toString();






        if (str_username.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Username");
            validate = 1;
        }
        if (str_pid.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Pid");
            validate = 1;
        }
        if (str_ptype.trim().equals("") && validate==0 )
        {
            Tooat("Please provide Ptype");
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
        if (str_qty.trim().equals("") && validate==0 )
        {
            Tooat("Please provide qty");
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
                String pid=jo.getString("pid");
                String ptype=jo.getString("ptype");

                String mrp=jo.getString("mrp");
                String gst=jo.getString("gst");
                String qty=jo.getString("qty");






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



                String names[]={"username","pid","ptype","mrp","gst","qty","username1"};
                String values[]={params[0],params[1],params[2],params[3],params[4],params[5],Config1.passenger.para1};
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
                String str_pid=pid.getText().toString();
                String str_ptype=ptype.getText().toString();
                String str_mrp=mrp.getText().toString();
                String str_gst=gst.getText().toString();
                String str_qty=qty.getText().toString();


                Config1.passenger.para1=str_username;
                Config1.passenger.para2=str_pid;
                Config1.passenger.para3=str_ptype;

                Config1.passenger.para4=str_mrp;
                Config1.passenger.para5=str_gst;
                Config1.passenger.para6=str_qty;

                Tooat("You have successfully Updated Cart Record! ");
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


            progressDialog = ProgressDialog.show(cart_update.this,
                    "Process",
                    "Please wait...");
        }
        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

}
