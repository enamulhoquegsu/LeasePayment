package com.mine.sharif.newleasepayment;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.NumberFormat;

public class FullSummary extends AppCompatActivity {

    Intent leaseViewIntent, leaseDeatailsIntent, homeViewIntent, fullSummaryIntent, settingIntent;
    String date;
    Double creditDouble=0.0, creditPerDouble=0.0, accessDouble=0.0, accessPerDouble=0.0, accountDouble=0.0, accountPerDouble=0.0,
            cityRideDouble=0.0, accessFeeDouble=0.0, insuranceDouble=0.0, leaseDouble=0.0, incomeDouble=0.0, totalIncome=0.0, totalCredit=0.0, totalCreditPer=0.0,
            totalAccess=0.0, totalAccessPer=0.0, totalAccount=0.0, totalAccountPer=0.0, totalAccessFee=0.0, totalCityRide=0.0,totalInsurance=0.0,
            totalLease=0.0;
    TextView tvIncome,textView;
    TextInputLayout inputCredit, inputAccess, inputAccount, setCreditPer, setAccessPer,
            setAccountPer, inputCityRide, inputLease, inputAccessFee, inputInsurance;
    NumberFormat defaultFormat ;
    DatabaseHelper myDb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Full Summary Report");
        setContentView(R.layout.activity_full_summary);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        myDb = new DatabaseHelper(this);

        homeViewIntent = new Intent(this,MainActivity.class);
        leaseViewIntent = new Intent(this,LeaseView.class);
        leaseDeatailsIntent = new Intent(this,LeaseDetails.class);
        fullSummaryIntent = new Intent(this,FullSummary.class);
        settingIntent = new Intent(this, MySetting.class);

        defaultFormat = NumberFormat.getCurrencyInstance();
        tvIncome = (TextView)findViewById(R.id.tvIncome);
        textView = (TextView)findViewById(R.id.textView);
        inputCredit = (TextInputLayout)findViewById(R.id.inputCredit);
        inputAccess = (TextInputLayout)findViewById(R.id.inputAccess);
        inputAccount = (TextInputLayout)findViewById(R.id.inputAccount);
        setCreditPer = (TextInputLayout)findViewById(R.id.setCreditPer);
        setAccessPer = (TextInputLayout)findViewById(R.id.setAccessPer);
        setAccountPer = (TextInputLayout)findViewById(R.id.setAccountPer);
        inputCityRide = (TextInputLayout)findViewById(R.id.inputCityRide);
        inputAccessFee = (TextInputLayout)findViewById(R.id.inputAccessFee);
        inputInsurance = (TextInputLayout)findViewById(R.id.inputInsurance);
        inputLease = (TextInputLayout)findViewById(R.id.inputLease);

        viewAllData ();
        inputCredit.getEditText().setText(defaultFormat.format(totalCredit));
        setCreditPer.getEditText().setText(defaultFormat.format(totalCreditPer));

        inputAccess.getEditText().setText(String.format("%.2f",totalAccess));
        setAccessPer.getEditText().setText(defaultFormat.format(totalAccessPer));

        inputAccount.getEditText().setText(defaultFormat.format(totalAccount));
        setAccountPer.getEditText().setText(defaultFormat.format(totalAccountPer));

        inputCityRide.getEditText().setText(defaultFormat.format(totalCityRide));
        inputAccessFee.getEditText().setText(defaultFormat.format(totalAccessFee));
        inputInsurance.getEditText().setText(defaultFormat.format(totalInsurance));
        inputLease.getEditText().setText(defaultFormat.format(totalLease));
        tvIncome.setText("Income: " + (defaultFormat.format(totalIncome)));
        textView.setText("As of : "+ date);


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
        try{
            Cursor c = myDb.getAllDataAsc();
            int count = c.getCount();

            Log.i("number of rows", "number of rows" + count);

            while (c.moveToNext()) {

                creditDouble = (c.getDouble(1));
                double credit_percent = Double.parseDouble(c.getString(10));
                creditPerDouble = (creditDouble) * (100.0-credit_percent)/100 ;
                accountDouble = (c.getDouble(2));
                double account_percent = Double.parseDouble(c.getString(11));
                accountPerDouble = accountDouble * (100.0-account_percent)/100;
                accessDouble  = (c.getDouble(3));
                double access_percent = Double.parseDouble(c.getString(11));
                accessPerDouble = (accessDouble*2.6)*(100.0-access_percent)/100;
                cityRideDouble = c.getDouble(4);
                accessFeeDouble = c.getDouble(5);
                insuranceDouble = c.getDouble(6);
                leaseDouble = c.getDouble(7);
                incomeDouble = c.getDouble(8);
                date = c.getString(9);

                totalCredit += creditDouble;
                totalCreditPer+=creditPerDouble;
                totalAccount+=accountDouble;
                totalAccountPer+=accountPerDouble;
                totalAccess += accessDouble;
                totalAccessPer+=accessPerDouble;
                totalCityRide += cityRideDouble;
                totalAccessFee += accessFeeDouble;
                totalInsurance+=insuranceDouble;
                totalLease += leaseDouble;
                totalIncome += incomeDouble;

            }

        }catch (Exception e){e.printStackTrace();}

    }


}

