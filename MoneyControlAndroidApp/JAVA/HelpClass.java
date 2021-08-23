package com.example.PennyWise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpClass extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_layout);
    }
    public void helpAddNewCategory(View view)
    {
        startActivity(new Intent(this,HelpAddNewCategoryClass.class));
    }
    public void developers(View view)
    {
        startActivity(new Intent(this,DevelopsClass.class));
    }
    public void  helpFilteringTransactions(View view)
    {
        startActivity(new Intent(this,HelpFilteringTransactions.class));
    }
    public void  helpRecurringTransaction(View view)
    {
        startActivity(new Intent(this,HelpRecurringTransactionClass.class));
    }
    public void  helpAddTransactionDetails(View view)
    {
        startActivity(new Intent(this,HelpTransactionDetailsClass.class));
    }

}
