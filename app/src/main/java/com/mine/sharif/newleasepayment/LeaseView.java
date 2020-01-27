package com.mine.sharif.newleasepayment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaseView extends AppCompatActivity {

    ListView listView;
    ArrayList<Lease>arrayLease;
    DatabaseHelper myDb;
    Intent intent, homeViewIntent, leaseViewIntent, leaseDetailsIntent,fullSummaryIntent, settingIntent;
    int myid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease_view);
        getSupportActionBar().setTitle("Lease Summary");
        //
        homeViewIntent = new Intent(this,MainActivity.class);
        leaseViewIntent = new Intent(this,LeaseView.class);
        leaseDetailsIntent = new Intent(this,LeaseDetails.class);
        fullSummaryIntent = new Intent(this,FullSummary.class);
        settingIntent = new Intent(this, MySetting.class);

        listView = (ListView) findViewById(R.id.listView);
        arrayLease = new ArrayList<Lease>();
        myDb = new DatabaseHelper(this);

        viewAllData();

        CustomLeaseAdapter leaseAdapter = new CustomLeaseAdapter(this,R.layout.list_view,arrayLease);

        listView.setAdapter(leaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lease mylease = arrayLease.get(position);
                myid = mylease.id;
                sendId();

            }
        });


    }// end of on create method

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(homeViewIntent);
                return true;
            case R.id.my_setting:
                startActivity(settingIntent);
                return true;
            case R.id.list_of_leases:
                startActivity(leaseViewIntent);
                return true;
            case R.id.summary:
                startActivity(fullSummaryIntent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }



    public void viewAllData(){
       try {
           Cursor c = myDb.getAllDataDesc();

           if (c.getCount() == 0) {
               return;
           }

           int count = c.getCount();


           while (c.moveToNext()) {
               arrayLease.add(new Lease(c.getString(9), c.getInt(0) ,c.getDouble(1) ,c.getDouble(2),c.getDouble(3), c.getDouble(4), c.getDouble(5),
                       c.getDouble(6), c.getDouble(7), c.getDouble(8), c.getString(10), c.getString(11),c.getString(12)));

           }
       }catch (Exception e){

       }


    }

    public void sendId(){

        leaseDetailsIntent.putExtra("myid", myid);
        startActivity(leaseDetailsIntent);
    }




}


