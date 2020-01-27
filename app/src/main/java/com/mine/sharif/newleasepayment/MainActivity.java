package com.mine.sharif.newleasepayment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher{


    Intent leaseViewIntent, homeViewIntent, fullSummaryIntent, settingIntent;
    Double creditDouble, creditPerDouble, accessDouble, accessPerDouble, accountDouble, accountPerDouble,
            cityRideDouble, accessFeeDouble, insuranceDouble, leaseDouble, totalIncome, cr_per, as_per, acc_per;
    TextView tvIncome,textView,  tvAcc, tvCr,tvAcess;
    TextInputLayout inputCredit, inputAccess, inputAccount, setCreditPer, setAccessPer,
            setAccountPer, inputCityRide, inputLease, inputAccessFee, inputInsurance;
    NumberFormat defaultFormat ;
    DatabaseHelper myDb;
    PercentSetting percentSetting;
    AlertDialog alertDialog;
    AlertDialog.Builder mBuilder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // dialog

        mBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialog = mBuilder.create();

        homeViewIntent = new Intent(this,MainActivity.class);
        leaseViewIntent = new Intent(this,LeaseView.class);
        fullSummaryIntent = new Intent(this,FullSummary.class);
        settingIntent = new Intent(this, MySetting.class);

        defaultFormat = NumberFormat.getCurrencyInstance();
        myDb = new DatabaseHelper(this);
        tvIncome = (TextView)findViewById(R.id.tvIncome);
        textView = (TextView)findViewById(R.id.textView);
        tvAcc = (TextView)findViewById(R.id.tvAcc);
        tvCr = (TextView)findViewById(R.id.tvCr);
        tvAcess = (TextView)findViewById(R.id.tvAcess);

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


        // text watcher change listener
        inputCredit.getEditText().addTextChangedListener((TextWatcher)this);
        inputAccess.getEditText().addTextChangedListener((TextWatcher)this);
        inputAccount.getEditText().addTextChangedListener((TextWatcher)this);
        setCreditPer.getEditText().addTextChangedListener((TextWatcher)this);
        setAccessPer.getEditText().addTextChangedListener((TextWatcher)this);
        setAccountPer.getEditText().addTextChangedListener((TextWatcher)this);
        inputCityRide.getEditText().addTextChangedListener((TextWatcher)this);
        inputAccessFee.getEditText().addTextChangedListener((TextWatcher)this);
        inputLease.getEditText().addTextChangedListener((TextWatcher)this);
        inputInsurance.getEditText().addTextChangedListener((TextWatcher)this);


        showPercent();


    } // end of onCreate method

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

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {



        try {
            if (inputCredit.getEditText().getText().hashCode()==editable.hashCode()){
                calculate();

            }else if (inputAccess.getEditText().getText().hashCode() == editable.hashCode()){
                calculate();

            }else if (inputAccount.getEditText().getText().hashCode() == editable.hashCode()){
                calculate();
            }else if (inputCityRide.getEditText().getText().hashCode()==editable.hashCode()){
                calculate();
            } else if (inputAccessFee.getEditText().getText().hashCode()==editable.hashCode()){
                calculate();
            } else if (inputInsurance.getEditText().getText().hashCode()==editable.hashCode()){
                calculate();
            } else if (inputLease.getEditText().getText().hashCode()==editable.hashCode()){
                calculate();
            }

        }catch (Exception e){

        }


    }

    public void buttonSave(View view){

        String creditString = inputCredit.getEditText().getText().toString();
        String accessString = inputAccess.getEditText().getText().toString();
        String accountString = inputAccount.getEditText().getText().toString();
        String cityRideString = inputCityRide.getEditText().getText().toString();
        String accessFeeString = inputAccessFee.getEditText().getText().toString();
        String insuranceString = inputInsurance.getEditText().getText().toString();
        String leaseString = inputLease.getEditText().getText().toString();
        String tvIncomeString = tvIncome.getText().toString();

        if (leaseString.isEmpty() || insuranceString.isEmpty() ){
            Toast.makeText(this, "Please fill out insurance and lease field!!!",
                    Toast.LENGTH_LONG).show();

        }else{


            try {
                if(creditString.isEmpty()){
                    creditDouble = 0.0;
                }else{
                    creditDouble = Double.parseDouble(creditString);
                }

                if(accessString.isEmpty()){
                    accessDouble = 0.0;

                }else{
                    accessDouble = Double.parseDouble(accessString);
                }
                if(accountString.isEmpty()){
                    accountDouble = 0.0;

                }else{
                    accountDouble = Double.parseDouble(accountString);

                }
                if(cityRideString.isEmpty()){
                    cityRideDouble = 0.0;
                }else{
                    cityRideDouble = Double.parseDouble(cityRideString);
                }

                if(accessFeeString.isEmpty()){
                    accessFeeDouble = 0.0;
                }else{
                    accessFeeDouble = Double.parseDouble(accessFeeString);
                }

                leaseDouble = Double.parseDouble(leaseString);
                insuranceDouble = Double.parseDouble(insuranceString);


                totalIncome = NumberFormat.getCurrencyInstance().parse(tvIncomeString).doubleValue();


                boolean isInserted = myDb.insertData(creditDouble, accountDouble, accessDouble, cityRideDouble, accessFeeDouble,
                        insuranceDouble, leaseDouble, totalIncome, String.valueOf(cr_per),String.valueOf(acc_per), String.valueOf(as_per));

                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    startActivity(leaseViewIntent);
                }

                else
                    Toast.makeText(MainActivity.this, "Did not work!!!", Toast.LENGTH_LONG).show();

            }catch (Exception b){ b.printStackTrace();}
        }

    }


    private void calculate(){
        String creditString = inputCredit.getEditText().getText().toString();
        String accessString = inputAccess.getEditText().getText().toString();
        String accountString = inputAccount.getEditText().getText().toString();
        String cityRideString = inputCityRide.getEditText().getText().toString();
        String accessFeeString = inputAccessFee.getEditText().getText().toString();
        String insuranceString = inputInsurance.getEditText().getText().toString();
        String leaseString = inputLease.getEditText().getText().toString();

        if(creditString.isEmpty()){
            setCreditPer.getEditText().setText("");
            creditDouble = 0.0;
            creditPerDouble = 0.0;
        }else{
            creditDouble = Double.parseDouble(creditString);

            creditPerDouble = (creditDouble) * (100.0-cr_per)/100.0;
            setCreditPer.getEditText().setText(String.format("%.2f",creditPerDouble));

        }

        if(accessString.isEmpty()){
            setAccessPer.getEditText().setText("");
            accessDouble = 0.0;
            accessPerDouble = 0.0;
        }else{
            accessDouble = Double.parseDouble(accessString);
            accessPerDouble = (accessDouble * 2.6) * ((100.0-as_per)/ 100);
            setAccessPer.getEditText().setText(String.format("%.2f",accessPerDouble));
        }
        if(accountString.isEmpty()){
            setAccountPer.getEditText().setText("");
            accountDouble = 0.0;
            accountPerDouble = 0.0;
        }else{
            accountDouble = Double.parseDouble(accountString);
            accountPerDouble = accountDouble * ((100.0-acc_per)/100);
            setAccountPer.getEditText().setText(String.format("%.2f",accountPerDouble));
        }
        if(cityRideString.isEmpty()){
            cityRideDouble = 0.0;
        }else{
            cityRideDouble = Double.parseDouble(cityRideString);

        }

        if(accessFeeString.isEmpty()){
            accessFeeDouble = 0.0;
        }else{
            accessFeeDouble = Double.parseDouble(accessFeeString);
        }

        if(leaseString.isEmpty()){
            leaseDouble = 0.0;
        }else{
            leaseDouble = Double.parseDouble(leaseString);
        }
        if(insuranceString.isEmpty()){
            insuranceDouble = 0.0;
        }else{
            insuranceDouble = Double.parseDouble(insuranceString);
        }

        totalIncome = (creditPerDouble + accessPerDouble + accountPerDouble + cityRideDouble) - (accessFeeDouble + insuranceDouble + leaseDouble);
        tvIncome.setText(defaultFormat.format(totalIncome));
        //textView.setText("income: " +defaultFormat.format(totalIncome));

    }


    public  void buttonSummaryLease(View view){

        startActivity(leaseViewIntent);

    }


    public void showPercent (){

        try {

            Cursor c = myDb.getSinglePercentData();
            int count = c.getCount();

            if (count == 0 ) {
                acc_per = 10.0;
                as_per = 10.0;
                cr_per = 5.0;
                Log.i("number ", "number of column " + count);
                //percentSetting = new PercentSetting(String.valueOf(cr_per),String.valueOf(acc_per),String.valueOf(as_per));

                //tvCr.setText("credit: " + cr_per + "%");
                Log.i("number ", "number of column " + acc_per);

                Log.i("number ", "number of column " + as_per);

                Log.i("number ", "number of column " + cr_per);

                tvCr.setText("credit: " + 5.0 + "%");
                //tvAcc.setText("account: " + acc_per + "%");
                tvAcc.setText("account: " + 10.0 + "%");
                //tvAcess.setText("access: " + as_per + "%");
                tvAcess.setText("access: " + 10.0 + "%");

            }

            else if (count>0) {
                c.moveToLast();

                cr_per = Double.parseDouble(c.getString(1));
                acc_per = Double.parseDouble(c.getString(2));
                as_per = Double.parseDouble(c.getString(3));

               // percentSetting = new PercentSetting(String.valueOf(cr_per),String.valueOf(acc_per),String.valueOf(as_per));

                tvCr.setText("credit: " + cr_per + "%" );
                tvAcc.setText("account: " + acc_per + "%");
                tvAcess.setText("access: " + as_per +"%");

            }

            else{

                acc_per = 10.0;
                as_per = 10.0;
                cr_per = 5.0;
                Log.i("number ", "number of column else is working");

                tvCr.setText("credit: " + cr_per + "%" );
                tvAcc.setText("account: " + acc_per + "%");
                tvAcess.setText("access: " + as_per +"%");

                Log.i("number ", "number of column " + acc_per);

                Log.i("number ", "number of column " + as_per);

                Log.i("number ", "number of column " + cr_per);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
