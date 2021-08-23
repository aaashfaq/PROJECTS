package com.example.PennyWise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper{

    private static final String dbname = "PennyWise2.db";

    public DataBaseManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table expense_tbl(id integer primary key autoincrement,category text,amount text, transactiondate text,payment text,notes text, recurr text, type text,username text,session text);";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expense_tbl ");
        onCreate(db);
    }

    public String addrecord(String category, String amount, String date, String payment, String notes, String recurr, String type, String username,String session){
        SQLiteDatabase db=this.getWritableDatabase(); //object to write data into DB

        ContentValues cv=new ContentValues();
        cv.put("category",category);
        cv.put("amount",amount);
        cv.put("transactiondate", String.valueOf(date));
        cv.put("payment",payment);
        cv.put("notes",notes);
        cv.put("recurr",recurr);
        cv.put("type", type);
        cv.put("username", username);
        cv.put("session",session);


        long result=db.insert("expense_tbl", null, cv);  //inserts record to db and retruns value

        if(result==-1)
            return "entry Failed";
        else
            return "transaction successfull";
    }



    public Cursor getDatabyall(String category1,String Payment1,String date1,String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where category='"+category1+"' and "+
                "payment='"+Payment1+"' and transactiondate = '"+date1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }

    public Cursor getRecurringData(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE username='"+username+"'  and session='"+session+"' and ( recurr = 'Daily' or recurr = 'Weekly' or recurr = 'Monthly' or recurr = 'Annual' ) ", null);
        return crs;
    }

    public Cursor allExpense(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"' and session='"+session+"'", null);

        return crs;

    }

    public Cursor allIncome(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Income' AND username='"+username+"' and session='"+session+"'", null);

        return crs;
    }
    public Cursor allFood(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category ='Food' and session='"+session+"'", null);

        return crs;
    }
    public Cursor allHealth(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category ='Health' and session='"+session+"'", null);

        return crs;
    }
    public Cursor allEducation(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category ='Education' and session='"+session+"'", null);

        return crs;
    }
    public Cursor allSport(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category ='Sports' and session='"+session+"'", null);

        return crs;
    }
    public Cursor allMisc(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category ='Misc' and session='"+session+"'" , null);

        return crs;
    }
    public Cursor allLend(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs = db. rawQuery("SELECT * FROM expense_tbl WHERE type = 'Expense' AND username='"+username+"'AND category !='Misc' and category !='Sport' and category !='Health' and category !='Food'and category !='Education' and session='"+session+"'", null);

        return crs;
    }


    public Cursor  allData(String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor crs=db.rawQuery("select * from expense_tbl where username='"+username+"' and session='"+session+"'",null);
        return crs;
    }


    public Cursor getbyCatandPayment(String category1,String Payment1,String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where category='"+category1+"' and "+
                "payment='"+Payment1+"' and username='"+username+"' and session='"+session+"'" ,null);

        return crs;
    }

    public Cursor getbyCategory(String category1,String username,String session)
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where category='"+category1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }


    public Cursor getbyCatandDate(String category1,String date1,String username,String session )
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where category='"+category1+"' and transactiondate ='"+date1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }
    public Cursor getbyPayment(String Payment1,String username,String session )
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where payment='"+Payment1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }
    public Cursor getbyPayandDate(String Payment1,String date1,String username,String session )
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where payment='"+Payment1+"' and transactiondate ='"+date1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }

    public Cursor getByDate(String date1,String username,String session )
    {
        SQLiteDatabase db=this.getReadableDatabase(); //object to read data into DB
        Cursor crs=db.rawQuery("select * from expense_tbl where transactiondate ='"+date1+"' and username='"+username+"' and session='"+session+"'",null);
        return crs;
    }

    public boolean deleteOne(String id,String session){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor crs=db.rawQuery("delete from expense_tbl where id ='"+id+"' and session='"+session+"'",null);
        if (crs.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

}
