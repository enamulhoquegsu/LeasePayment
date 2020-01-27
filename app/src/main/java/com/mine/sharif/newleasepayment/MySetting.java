package com.mine.sharif.newleasepayment;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MySetting extends AppCompatActivity {


    Intent homeViewIntent, settingIntent, leaseViewIntent, fullSummaryIntent;

    TextInputLayout percentCredit, percentAccount, percentAccess;

    // class PercentSetting
    PercentSetting percentSetting ;


    String creditString, accountString, accessString;
    Button buttonSetPercent;

    DatabaseHelper myDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        myDb = new DatabaseHelper(this);

        homeViewIntent = new Intent(this, MainActivity.class);
        settingIntent = new Intent(this, MySetting.class);
        leaseViewIntent = new Intent(this,LeaseView.class);
        fullSummaryIntent = new Intent(this, FullSummary.class);

        percentCredit = (TextInputLayout)findViewById(R.id.percentCredit);
        percentAccount = (TextInputLayout)findViewById(R.id.percentAccount);
        percentAccess = (TextInputLayout)findViewById(R.id.percentAccess);

        buttonSetPercent = (Button)findViewById(R.id.buttonSetPercent);


        setPercent ();
    } // end of on create method

    @Override
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




    public void setPercent (){

        buttonSetPercent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try {

                    creditString = percentCredit.getEditText().getText().toString();
                    accountString = percentAccount.getEditText().getText().toString();
                    accessString = percentAccess.getEditText().getText().toString();

                    if (creditString.isEmpty() || accountString.isEmpty() || accessString.isEmpty()) {

                        creditString = String.valueOf(5);
                        accountString = String.valueOf(10);
                        accessString = String.valueOf(10);

                    }

                    else {
                        creditString = creditString.trim();
                        accountString = accountString.trim();
                        accessString = accessString.trim();
                    }


                    Log.i("working", " button is clicked " + creditString);
                    Log.i("working", " button is clicked " + accountString);
                    Log.i("working", " button is clicked " + accessString);


                    boolean isInserted = myDb.insertPercent(creditString, accountString, accessString);

                    if (isInserted == true) {
                        Toast.makeText(MySetting.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        startActivity(leaseViewIntent);
                    } else
                        Toast.makeText(MySetting.this, "Did not work!!!", Toast.LENGTH_LONG).show();


                    startActivity(homeViewIntent);



                }catch (Exception e){e.printStackTrace();}


            }
        });




    }





}
