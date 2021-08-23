package com.example.PennyWise;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpMainPage extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_mainpage_layout);

    }
    public void info(View view)
    {
        startActivity(new Intent(this,HelpClass.class));
    }
    public void faq(View view)
    {
        //startActivity(new Intent(this,AllTransactions.class));
        startActivity(new Intent(this,FAQClass.class));
    }
    public void contact(View view)
    {
       // startActivity(new Intent(this,AllTransactions.class));
        Toast.makeText(HelpMainPage.this,"Hello" ,Toast.LENGTH_SHORT).show();
    }
}
