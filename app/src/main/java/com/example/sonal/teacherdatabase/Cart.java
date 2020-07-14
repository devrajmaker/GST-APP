package com.example.sonal.teacherdatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonal.teacherdatabase.java.java.Config1;
import com.example.sonal.teacherdatabase.java.java.webservice.WebService1;

public class Cart extends AppCompatActivity {


    private TextView P_ID;
    private TextView ptype;
    private TextView mrp;

    private TextView gst;
    private TextView price;
    private TextView tgst;
    private TextView tmrp;
    int totalmrp,totalgst,tprice;
    String totalmrp1,totalprice,totalgst1;
private EditText qty;
    Button btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setTitle("Food Details");
        P_ID=(TextView)findViewById(R.id.PID);

        ptype=(TextView)findViewById(R.id.Type1);
        mrp=(TextView)findViewById(R.id.mrp1);
        tmrp=(TextView)findViewById(R.id.tmrp);
        tgst=(TextView)findViewById(R.id.tgst);
        price=(TextView)findViewById(R.id.price1);
        gst=(TextView)findViewById(R.id.GST1);
       qty=(EditText)findViewById(R.id.quantity1);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        System.out.println("hello");


    }

public void addcart (View v)
{
    String str_pid=P_ID.getText().toString();

    String str_ptype=ptype.getText().toString();
   String str_mrp=mrp.getText().toString();


   String str_gst=gst.getText().toString();
    String str_qty=qty.getText().toString();


    String names[]={str_pid,str_ptype,str_mrp,str_gst,str_qty,totalmrp1,totalgst1,totalprice};

    Cart.AsyncTaskRunner task=new Cart.AsyncTaskRunner();
    task.execute(names);
}

    public void calculate (View v)
    {
         String q=qty.getText().toString();
      //  String q="2";
        System.out.println("quantityyyyy="+q);
        int quantity = Integer.parseInt(q);
        String g=Config1.passenger.para4;
        int gstt=Integer.parseInt(g);
        String m=Config1.passenger.para3;
        int mrpp=Integer.parseInt(m);
         totalgst=quantity*((gstt*mrpp)/100);
         totalmrp=quantity*mrpp;
         tprice=totalmrp+((gstt*totalmrp)/100);
        // String ttgst=totalgst;
      System.out.println("total gst="+totalgst);
      System.out.println("total mrp="+totalmrp);
     // String ttgst=totalgst.();
      totalgst1=Integer.toString(totalgst);
        totalmrp1=Integer.toString(totalmrp);
        totalprice=Integer.toString(tprice);
        tgst.setText(totalgst1);
        tmrp.setText(totalmrp1);
        price.setText(totalprice);

    }
    protected void onResume() {
        super.onResume();


        P_ID.setText(Config1.passenger.para1);
        ptype.setText(Config1.passenger.para2);
        mrp.setText(Config1.passenger.para3);

        gst.setText(Config1.passenger.para4);
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


                String names[]={"username","pid","ptype","mrp","gst","qty","totalmrp","totalgst","tprice","paid"};
                System.out.println("username="+Config1.uname);
                System.out.println("sonalpid="+params[0]);
                String values[]={Config1.uname,params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7],"NO"};
                //   resp = web.sendCompleteData(null, null, "retrieve_events");
                WebService1 web=new WebService1();
                resp = web.sendCompleteData(names, values, "cart_save");
                //result1=resp;
                Log.d("Values of cart",resp);




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
                Toast("You have successfully added product to cart! ");
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


            progressDialog = ProgressDialog.show(Cart.this,
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
