package com.example.PennyWise;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class OverviewClass extends AppCompatActivity {
    String categoryName[];
    float pieAmount[];
    Float incomeCount = 0.0f;
    Float expenseCount = 0.0f;
    Float balanceCount = 0.0f;
    String username,session;
    private final String CHANNEL_ID="personal_notifications";
    private final int NOTIFICATION_ID=001;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_layout);

        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();

        viewExpenseData();
        viewIncomeData();
        balanceCount=incomeCount-expenseCount;
        if (balanceCount<0){
            balanceCount=-balanceCount;
            categoryName= new String[]{"Expense ($)", "Income ($)", "Balance (-$)"};
        }
        else{
            categoryName= new String[]{"Expense ($)", "Income ($)", "Balance ($)"};
        }

        if(Float.compare(incomeCount,expenseCount)<0){
            createNotificationChannel();
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
            builder.setSmallIcon(R.drawable.logo_final2);
            builder.setContentTitle("Penny_Wise");
            builder.setContentText("Your Expenses exceeded your Income");
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
        }
        pieAmount= new float[]{expenseCount,incomeCount,balanceCount};
        setupPieChart();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Personel Notification";
            String description="Include all personal Notifications";
            int importance=NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }


    public void viewExpenseData() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(OverviewClass.this);

        Cursor result = obj.allExpense(username,session);
        while (result.moveToNext()) {

                String copy=result.getString(2);
                String[] getNumber=copy.split(" ");
                String currency=getNumber[1];
                float number=Float.parseFloat(getNumber[0]);
                if (currency.matches("Rs")){
                number=number*0.013f;
                expenseCount=expenseCount+number;
                }
                else if(currency.matches("€")){
                    number=number*1.12f;
                    expenseCount=expenseCount+number;
                }
                else if(currency.matches("₹")){
                    number=number*0.013f;
                    expenseCount=expenseCount+number;
                }
                else {
                    expenseCount=expenseCount+number;
                }

            }

        }


    public void viewIncomeData() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();
        DataBaseManager obj=new DataBaseManager(OverviewClass.this);
        Cursor result = obj.allIncome(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            float number=Float.parseFloat(getNumber[0]);
            String currency=getNumber[1];
            if (currency.matches("Rs")){
                number=number*0.013f;
                incomeCount=incomeCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                incomeCount=incomeCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                incomeCount=incomeCount+number;
            }
            else {
                incomeCount=incomeCount+number;
            }
        }
    }
    private void setupPieChart() {
        List<PieEntry> pieEnteries=new ArrayList<>();
        for(int i = 0; i< pieAmount.length; i++){
            pieEnteries.add(new PieEntry(pieAmount[i], categoryName[i]));

        }
        PieDataSet dataSet=new PieDataSet(pieEnteries,"OverView");
        dataSet.setValueTextSize(15f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data=new PieData(dataSet);
        PieChart chart= (PieChart)findViewById(R.id.chart);
        chart.setEntryLabelTextSize(17);
        chart.setData(data);
        chart.invalidate();
    }
    public void budget(View view)
    {
        startActivity(new Intent(this, BudgetClass.class));
    }
}

