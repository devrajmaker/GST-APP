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

public class pdetail extends AppCompatActivity {


    private TextView pid;
    private TextView pname;
    private TextView mrp;

    private TextView gst;
    private TextView ptype;

    //Button btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetail);

        setTitle("Product Details");
        pid=(TextView)findViewById(R.id.pid);

        pname=(TextView)findViewById(R.id.pname);
        mrp=(TextView)findViewById(R.id.mrp);

        gst=(TextView)findViewById(R.id.gst);
        ptype=(TextView)findViewById(R.id.ptype);

      //  btnEdit=(Button)findViewById(R.id.btnEdit);
      //  btnDelete=(Button)findViewById(R.id.btnDelete);
        System.out.println("hello");

    }
    protected void onResume() {
        super.onResume();


        pid.setText(Config1.passenger.para1);
        pname.setText(Config1.passenger.para2);
        mrp.setText(Config1.passenger.para3);

        gst.setText(Config1.passenger.para4);

        ptype.setText(Config1.passenger.para5);
    }


    public void onEdit(View v)
    {
//Toast("HI! ");
  //       System.out.println("Vidyalanakar");
    //    System.out.println("sooooo"+Config1.passenger.para1);System.out.println("sooooo"+Config1.passenger.para1);
        Intent intent=new Intent(this,pupdate.class);
        startActivity(intent);
    }

    public void Delete(View v)
    {

        //Toast("HI! ");
       // System.out.println("Vidyalanakar");
        //System.out.println("sooooo"+Config1.passenger.para1);System.out.println("sooooo"+Config1.passenger.para1);
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

                String names[]={"P_ID"};
                String values[]={Config1.passenger.para1};
                System.out.println("sooooo"+Config1.passenger.para1);






                resp = web.sendCompleteData(names, values, "product_delete");

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


            progressDialog = ProgressDialog.show(pdetail.this,
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
