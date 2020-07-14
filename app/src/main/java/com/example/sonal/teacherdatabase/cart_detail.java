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

public class cart_detail extends AppCompatActivity {


    private TextView username;
    private TextView pid;
    private TextView ptype;

    private TextView mrp;
    private TextView gst;
    private TextView qty;
    private TextView totalmrp;
    private TextView totalgst;
    private TextView tprice;
    private TextView paid;


    Button btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);

        setTitle("User Details");
        username=(TextView)findViewById(R.id.username);

        pid=(TextView)findViewById(R.id.pid1);
        ptype=(TextView)findViewById(R.id.ptype1);

        mrp=(TextView)findViewById(R.id.mrp1);
        gst=(TextView)findViewById(R.id.gst1);
        qty=(TextView)findViewById(R.id.qty1);
        totalmrp=(TextView)findViewById(R.id.totalmrp1);
        totalgst=(TextView)findViewById(R.id.totalgst1);
        tprice=(TextView)findViewById(R.id.totalprice1);
       paid=(TextView)findViewById(R.id.paid1);

        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        System.out.println("hello");

    }
    protected void onResume() {
        super.onResume();


        username.setText(Config1.passenger.para1);
        pid.setText(Config1.passenger.para2);
        ptype.setText(Config1.passenger.para3);

        mrp.setText(Config1.passenger.para4);
        gst.setText(Config1.passenger.para5);
        qty.setText(Config1.passenger.para6);
        totalmrp.setText(Config1.passenger.para7);
        totalgst.setText(Config1.passenger.para8);
        tprice.setText(Config1.passenger.para9);
        paid.setText(Config1.passenger.para10);

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

                String names[]={"pid"};
                String values[]={Config1.passenger.para1};
                resp = web.sendCompleteData(names, values, "cart_delete");

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


            progressDialog = ProgressDialog.show(cart_detail.this,
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
