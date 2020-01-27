package com.mine.sharif.newleasepayment;

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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class LeaseDetails extends AppCompatActivity implements TextWatcher {
    Intent leaseViewIntent, homeViewIntent, fullSummaryIntent, settingIntent ;
    Button buttonDelete, buttonUpdate;
    NumberFormat defaultFormat;
    TextView  tvDate, tvIncome;
    TextInputLayout inputCredit, inputAccess, inputAccount, setCreditPer, setAccessPer,
            setAccountPer, inputCityRide, inputLease, inputAccessFee, inputInsurance;
    Bundle bundle;
    DatabaseHelper myDb;
    int id;
    String date;
    Double credit, creditPer, account, accountPer, access, accessPer, access_fee, city_ride, insurance , lease,  income, credit_percent, account_percent, access_percent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease_details);
        getSupportActionBar().setTitle("Lease Details");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        defaultFormat = NumberFormat.getCurrencyInstance();

        leaseViewIntent = new Intent(this,LeaseView.class);
        homeViewIntent = new Intent(this, MainActivity.class);
        fullSummaryIntent = new Intent(this,FullSummary.class);
        settingIntent = new Intent(this, MySetting.class);

        myDb = new DatabaseHelper(this);
        bundle = getIntent().getExtras();

        tvDate = (TextView)findViewById(R.id.tvDate);
        tvIncome = (TextView)findViewById(R.id.tvIncome);

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

        id = (int) bundle.get("myid");

        Log.i("ID IS : ", String.valueOf(id));


        getSingleRow();

        tvDate.setText("Lease Paid:  " + date);
        /*

        inputCredit.getEditText().setText(defaultFormat.format(credit));
        setCreditPer.getEditText().setText(defaultFormat.format(creditPer));

        inputAccess.getEditText().setText(String.format("%.2f",access));
        setAccessPer.getEditText().setText(defaultFormat.format(accessPer));

        inputAccount.getEditText().setText(defaultFormat.format(account));
        setAccountPer.getEditText().setText(defaultFormat.format(accountPer));

        inputCityRide.getEditText().setText(defaultFormat.format(city_ride));
        inputAccessFee.getEditText().setText(defaultFormat.format(access_fee));
        inputInsurance.getEditText().setText(defaultFormat.format(insurance));
        inputLease.getEditText().setText(defaultFormat.format(lease));
        tvIncome.setText("Income: " + defaultFormat.format(income));

        */

        inputCredit.getEditText().setText(String.valueOf(credit));
        setCreditPer.getEditText().setText(String.format("%.2f",creditPer));

        inputAccess.getEditText().setText(String.valueOf(access));
        setAccessPer.getEditText().setText(String.format("%.2f",accessPer));

        inputAccount.getEditText().setText(String.valueOf(account));
        setAccountPer.getEditText().setText(String.format("%.2f",accountPer));

        inputCityRide.getEditText().setText(String.valueOf(city_ride));
        inputAccessFee.getEditText().setText(String.valueOf(access_fee));
        inputInsurance.getEditText().setText(String.valueOf(insurance));
        inputLease.getEditText().setText(String.valueOf(lease));
        tvIncome.setText("Income: " + defaultFormat.format(income));

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



        deleteRow ();
        update();




    }  // end of On create Method

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

    private void calculate(){
        String creditString = inputCredit.getEditText().getText().toString();
        String accessString = inputAccess.getEditText().getText().toString();
        String accountString = inputAccount.getEditText().getText().toString();
        String cityRideString = inputCityRide.getEditText().getText().toString();
        String accessFeeString = inputAccessFee.getEditText().getText().toString();
        String insuranceString = inputInsurance.getEditText().getText().toString();
        String leaseString = inputLease.getEditText().getText().toString();


        try{
            if(creditString.isEmpty()){
                setCreditPer.getEditText().setText("");
                creditPer = 0.0;
            }else{
                credit = Double.parseDouble(creditString);
                creditPer= credit* (100.0-credit_percent)/100;
                setCreditPer.getEditText().setText(String.format("%.2f",creditPer));
               // setCreditPer.getEditText().setText(String.valueOf(creditPer));

            }

            if(accessString.isEmpty()){
                setAccessPer.getEditText().setText("");
                accessPer = 0.0;
            }else{
                //upAccess = NumberFormat.getCurrencyInstance().parse(accessString).doubleValue();
                access = Double.parseDouble(accessString);
                accessPer = (access*2.6)*(100.0-access_percent)/100;
                setAccessPer.getEditText().setText(String.format("%.2f",accessPer));
                //setAccessPer.getEditText().setText(String.valueOf(accessPer));
            }
            if(accountString.isEmpty()){
                setAccountPer.getEditText().setText("");
                accountPer = 0.0;
            }else{
                account = Double.parseDouble(accountString);
                accountPer = account * (100.0- account_percent)/100;
                setAccountPer.getEditText().setText(String.format("%.2f",accountPer));
                //setAccountPer.getEditText().setText(String.valueOf(accountPer));
            }
            if(cityRideString.isEmpty()){
                city_ride = 0.0;
            }else{
               // upCityRide = NumberFormat.getCurrencyInstance().parse(cityRideString).doubleValue();
                city_ride = Double.parseDouble(cityRideString);
                //inputCityRide.getEditText().setText(String.valueOf(city_ride));
            }

            if(accessFeeString.isEmpty()){
                access_fee = 0.0;
            }else{
                //access_fee = NumberFormat.getCurrencyInstance().parse(accessFeeString).doubleValue();
                access_fee = Double.parseDouble(accessFeeString);
                //inputAccessFee.getEditText().setText(String.valueOf(upAccessFee));
            }

            if(leaseString.isEmpty()){
                lease = 0.0;
            }else{
                //upLease = NumberFormat.getCurrencyInstance().parse(leaseString).doubleValue();
                lease = Double.parseDouble(leaseString);
                //inputLease.getEditText().setText(String.valueOf(upLease));
            }
            if(insuranceString.isEmpty()){
                insurance = 0.0;
            }else{
                //upInsurance = NumberFormat.getCurrencyInstance().parse(insuranceString).doubleValue();
                //inputInsurance.getEditText().setText(String.valueOf(upInsurance));
                insurance = Double.parseDouble(insuranceString);
            }

            income = (creditPer + accessPer + accountPer + city_ride) - (access_fee + insurance + lease);
            tvIncome.setText(defaultFormat.format(income));
            Log.i("menu", String.valueOf(income));

        }catch (Exception e){e.printStackTrace();}




    }


    public void getSingleRow (){
        try{
            Cursor c = myDb.getSingleRowData(id);
            int count = c.getCount();



            while (c.moveToNext()) {

                credit = (c.getDouble(1));
                credit_percent = Double.parseDouble( c.getString(10));
                creditPer = credit* (100.0-credit_percent)/100 ;
                account = (c.getDouble(2));
                account_percent = Double.parseDouble( c.getString(11));
                accountPer = account * (100.0- account_percent)/100;
                access  = (c.getDouble(3));
                access_percent = Double.parseDouble(c.getString(12));
                accessPer = (access*2.6)*(100.0-access_percent)/100;
                city_ride = c.getDouble(4);
                access_fee = c.getDouble(5);
                insurance = c.getDouble(6);
                lease = c.getDouble(7);
                income = c.getDouble(8);
                date = c.getString(9);

            }

        }catch (Exception e){e.printStackTrace();}

    }

    public void deleteRow (){

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LeaseDetails.this);
                    builder.setMessage("Are you sure you want to delete record?")
                           .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("message", "update button is working");
                                    try {
                                        Integer deletedRows = myDb.deleteData(id);
                                        if (deletedRows > 0){
                                            Toast.makeText(LeaseDetails.this, "data has been deleted", Toast.LENGTH_LONG).show();
                                            startActivity(leaseViewIntent);
                                        }
                                        else{
                                            Toast.makeText(LeaseDetails.this, "not working", Toast.LENGTH_LONG).show();

                                        }
                                    }catch (Exception e){e.printStackTrace();}
                                }
                           })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


    }


    public void backPage(View v){

        startActivity(leaseViewIntent);

    }

    public void mainPage(View v){

        startActivity(homeViewIntent);
    }


    public void update (){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creditString = String.valueOf(credit);
                String accessString = String.valueOf(access);
                String accountString = String.valueOf(account);
                String cityRideString = String.valueOf(city_ride);
                String accessFeeString = String.valueOf(access_fee);
                String insuranceString = String.valueOf(insurance);
                String leaseString = String.valueOf(lease);
                String incomeString = String.valueOf(income);
                String my_id = String.valueOf(id);


                boolean isUpdate = myDb.updateRecord(my_id,creditString, accountString, accessString, cityRideString, accessFeeString, insuranceString, leaseString,incomeString);
                if(isUpdate == true) {
                    Toast.makeText(LeaseDetails.this,"Data Update",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LeaseDetails.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    //openDialog();
                }
            }
        });
    }

    public void openDialog(){

        Message message = new Message();
        message.show(getSupportFragmentManager(),"Example Dialog");

    }






}
