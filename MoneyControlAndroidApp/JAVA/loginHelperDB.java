package com.example.PennyWise;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class loginHelperDB extends SQLiteOpenHelper {

    private static String logindbname="User2.db";
    public loginHelperDB(@Nullable Context context) {
        super(context, logindbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table login_tbl(id integer primary key autoincrement,username text,password text);";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login_tbl ");
        onCreate(db);

    }

    public String addUser(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase(); //object to write data into DB

        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        long result=db.insert("login_tbl",null,cv);  //inserts record to db and retruns value

        if(result == -1)
            return "Registration failed";
        else
            return "Registered user successfully";
    }

    public boolean isloginvalid(String username,String password)
    {
        String sql="Select count(*) from login_tbl where username='"+username+"' and password='"+password+"'";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();
        if(l==1)
        {
            return true;
        }
        else
            return false;

    }

}
