package com.example.PennyWise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {

    Button  AddTransaction, rec_trans;
    String username,session;
    DataBaseManager obj = new DataBaseManager(MainActivity.this);

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddTransaction=findViewById(R.id.btnAddTrans);
        rec_trans = findViewById(R.id.rec_trans);






        rec_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RecurringTransactionClass.class));
            }
        });

        AddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryClass.class);
                startActivity(intent);
            }
        });

    }



    public void allTransactions(View view)
    {
        startActivity(new Intent(this,AllTransactions.class));
    }
    public void help(View view)

    {
        startActivity(new Intent(this,HelpMainPage.class));
    }
    public void overView(View view)
    {
        startActivity(new Intent(this, OverviewClass.class));
    }


    public void export(View view)
    {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();
        Cursor result = obj.allData(username,session);
        StringBuilder data=new StringBuilder();
        data.append("ID,CATEGORY,AMOUNT,DATE,TAG");
        while(result.moveToNext())
        {
            data.append("\n"+result.getString(0)+","+result.getString(1)+","+result.getString(2)+","+result.getString(3)+","+result.getString(4) );
        }

        try {
            //saving file into device
            FileOutputStream out=openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(data.toString().getBytes());
            out.close();

            //exporting data
            Context context=getApplicationContext();
            File filelocation=new File(getFilesDir(),"data.csv");
            Uri path= FileProvider.getUriForFile(context,"com.example.PennyWise.Fileprovider",filelocation);
            Intent fileIntent=new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT,"Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM,path);
            startActivity(Intent.createChooser(fileIntent,"Send mail"));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
