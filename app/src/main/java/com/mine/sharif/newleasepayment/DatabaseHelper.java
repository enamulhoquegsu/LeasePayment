package com.mine.sharif.newleasepayment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Lease.db";
    public static final String TABLE_PAYMENT = "payment";


    public static final String COL_1 = "ID";
    public static final String COL_2 = "credit";
    public static final String COL_3 = "account";
    public static final String COL_4 = "access";
    public static final String COL_5 = "city_ride";
    public static final String COL_6 = "access_fee";
    public static final String COL_7 = "insurance_fee";
    public static final String COL_8 = "lease";
    public static final String COL_9 = "income";
    public static final String COL_10 = "lease_date";
    public static final String COL_CREDIT_PERCENT = "credit_percent";
    public static final String COL_ACCOUNT_PERCENT = "account_percent";
    public static final String COL_ACCESS_PERCENT = "access_percent";

    // table percent
    public static final String TABLE_PERCENT = "percent";
    private static final String COL_ID = "ID";
    private static final String COL_CREDIT = "credit_percent";
    private static final String COL_ACCOUNT = "account_percent";
    private static final String COL_ACCESS = "access_percent";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,6);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PAYMENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, credit INT(5,2), account INT(5,2), access INT(5,2)," +
                " city_ride INT(5,2), access_fee INT(5,2), insurance_fee INT(5,2), lease INT(5,2), income INT(5,2)," +
                " lease_date DATE DEFAULT (datetime('now','localtime')), credit_percent TEXT, account_percent TEXT, access_percent TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PERCENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, credit_percent TEXT NOT NULL DEFAULT '5' ," +
                " account_percent TEXT NOT NULL DEFAULT '10'," +
                " access_percent TEXT NOT NULL DEFAULT '10')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PAYMENT);
        onCreate(db);
    }

    public boolean insertData(double credit, double account, double access,double city_ride, double access_fee, double insurance,
                              double lease, double income, String credit_percent,String account_percent, String access_percent ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,credit);
        contentValues.put(COL_3,account);
        contentValues.put(COL_4,access);
        contentValues.put(COL_5,city_ride);
        contentValues.put(COL_6,access_fee);
        contentValues.put(COL_7,insurance);
        contentValues.put(COL_8,lease);
        contentValues.put(COL_9,income);
        contentValues.put(COL_CREDIT_PERCENT,credit_percent);
        contentValues.put(COL_ACCOUNT_PERCENT,account_percent);
        contentValues.put(COL_ACCESS_PERCENT,access_percent);

        long result = db.insert(TABLE_PAYMENT,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // insert into table percent
    public boolean insertPercent(String creditPercent, String accountPercent, String accessPercent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CREDIT,creditPercent);
        contentValues.put(COL_ACCOUNT,accountPercent);
        contentValues.put(COL_ACCESS,accessPercent);

        long result = db.insert(TABLE_PERCENT,null ,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }


    //

    public Cursor getAllDataAsc() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM  " + TABLE_PAYMENT + " ORDER BY lease_date Asc" , null );

        return c;


    }

    public Cursor getAllDataDesc() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM  " + TABLE_PAYMENT + " ORDER BY lease_date Desc" , null );

        return c;


    }


    public Cursor getSingleRowData(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_PAYMENT + " where ID = " + id, null);
        return  c;
    }

    public Cursor getSinglePercentData(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_PERCENT , null);
        return  c;
    }


    public  boolean updateRecord(String id, String credit, String account, String access, String city_ride, String access_fee, String insurance,String lease, String income) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,credit);
        contentValues.put(COL_3,account);
        contentValues.put(COL_4,access);
        contentValues.put(COL_5,city_ride);
        contentValues.put(COL_6,access_fee);
        contentValues.put(COL_7,insurance);
        contentValues.put(COL_8,lease);
        contentValues.put(COL_9,income);


        try {

            db.update(TABLE_PAYMENT, contentValues, "ID = ?",new String[] { id });
            db.close();
            return true;
        } catch (SQLiteException e) {
            db.close();

            return false;
        }
    }






    public Integer deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PAYMENT, COL_1 + "=" + id, null);

    }

}
