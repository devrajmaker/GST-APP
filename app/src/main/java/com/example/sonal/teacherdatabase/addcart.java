package com.example.sonal.teacherdatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sonal.teacherdatabase.java.java.Common;
import com.example.sonal.teacherdatabase.java.java.Config1;
import com.example.sonal.teacherdatabase.java.java.adapter.Display_Adapter;
import com.example.sonal.teacherdatabase.java.java.webservice.WebService1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class addcart extends AppCompatActivity {

    private Button addButton;
    private ListView listView;
    // private CustomListAdapter adapter;
    private List<Common> pendingList = new ArrayList<Common>();
    private Display_Adapter list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);

        setTitle("CART DETAILS");

        listView = (ListView) findViewById(R.id.list);
        // list_adapter= new Complaint_Adapter(this, pendingList);
        //listView.setAdapter(list_adapter);

        addButton=(Button)findViewById(R.id.addButton);

        fillList();
        //Event Listener
        ListView_Event();

    }

    int i=0;
    protected void onResume()
    {
        super.onResume();


        if(i>0) {
            if(!pendingList.isEmpty()) {
                pendingList.clear();
            }
            fillList();

            // listView.deferNotifyDataSetChanged();
        }
        //Tooat("Welcome");
        i++;
    }


    public void save(View v)
    {
        //Config1.page="Add";
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);

    }


    private void fillList() {

        AsyncTaskRunner task=new AsyncTaskRunner();
        task.execute();

    }


    //========================================
    //***** Functions related to functionality
    //========================================
    private void ListView_Event() {
        // TODO Auto-generated method stub
        //ListView Click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Common value=pendingList.get(position);
                Config1.passenger.para1=value.para1;
                Config1.passenger.para2=value.para2;

                Config1.passenger.para3=value.para3;
                Config1.passenger.para4=value.para4;
                Config1.passenger.para5=value.para5;
                Config1.passenger.para6=value.para6;
                Config1.passenger.para7=value.para7;
                Config1.passenger.para8=value.para8;
                Config1.passenger.para9=value.para9;
                Config1.passenger.para10=value.para10;


                Intent intent=new Intent(addcart.this,cart_detail.class);
                startActivity(intent);






            }
        });


    }


    public void Toast(String message)
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

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Calendar cal = Calendar.getInstance();

            for(int i=0; i<ja.length(); i++) {

                jo = ja.getJSONObject(i);


                String username=jo.getString("Username");
                String pid2=jo.getString("pid");
                String ptype2=jo.getString("ptype");

                String mrp2=jo.getString("mrp");
                String gst2=jo.getString("gst");
                String qty2=jo.getString("qty");
                String totalmrp2=jo.getString("totalmrp");
                String totalgst2=jo.getString("totalgst");
                String totalprice2=jo.getString("tprice");
                String paid2=jo.getString("paid");




                Common common=new Common();

                common.para1=username;//notice_title
                common.para2=pid2;//created_date
                common.para3=ptype2;//notice_desc
                common.para4=mrp2;
                common.para5=gst2;
                common.para6=qty2;
                common.para7=totalmrp2;
                common.para8=totalgst2;
                common.para9=totalprice2;
                common.para10=paid2;

                pendingList.add(common);
                // Log.e("Data", jo.getString("event_id"));


            }

            list_adapter= new Display_Adapter(this, pendingList);
            listView.setAdapter(list_adapter);
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
        private String result3;
        private String result4;
        private String result5; private String result6;


        @Override
        protected String doInBackground(String... params) {
            publishProgress("Process..."); // Calls onProgressUpdate()
            //Tooat("Welcome "+params[0]);
            try {


                result1="";
                result2="";
                result3="";
                result4="";result5="";result6="";


                WebService1 web=new WebService1();
                //	String result=web.onLogin(username, password);

                // String names[]={"notice_title","notice_desc"};
                //String values[]={params[0],params[1]};
                resp = web.sendCompleteData(null, null, "cart_retrieve");
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
            //String status=resp.trim();
            //Tooat(resp);
            selectAllDatesExists(resp);

            progressDialog.dismiss();
            //finalResult.setText(result);
        }

        @Override
        protected void onPreExecute() {


            progressDialog = ProgressDialog.show(addcart.this,"Process","Please wait...");
        }
        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }


}
