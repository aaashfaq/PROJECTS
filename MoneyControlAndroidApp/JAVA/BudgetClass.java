package com.example.PennyWise;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class BudgetClass extends AppCompatActivity {

    String categoryName[]={"Food ($)", "Health ($)","Sports ($)","Education ($)","Misc ($)","Lend/Borrow ($)"};
    float pieAmount[];
    float foodCount=0.0f;
    float healthCount=0.0f;
    float educationCount=0.0f;
    float sportsCount=0.0f;
    float miscCount=0.0f;
    float lendCount=0.0f;
    String username,session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_layout);

        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();

        getFoodCount();
        getHealthCount();
        getEducationCount();
        getSportsCount();
        getMisc();
        getLendCount();
        pieAmount= new float[]{foodCount,healthCount,sportsCount,educationCount,miscCount,lendCount};
        setupPieChart();
    }

    private void getMisc() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allMisc(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                miscCount=miscCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                miscCount=miscCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                miscCount=miscCount+number;
            }
            else {
                miscCount=miscCount+number;
            }

        }

    }

    private void getSportsCount() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allSport(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                sportsCount=sportsCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                sportsCount=sportsCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                sportsCount=sportsCount+number;
            }
            else {
                sportsCount=sportsCount+number;
            }

        }
    }

    private void getEducationCount() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allEducation(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                educationCount=educationCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                educationCount=educationCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                educationCount=educationCount+number;
            }
            else {
                educationCount=educationCount+number;
            }

        }
    }

    private void getHealthCount() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allHealth(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                healthCount=healthCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                healthCount=healthCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                healthCount=healthCount+number;
            }
            else {
                healthCount=healthCount+number;
            }

        }
    }

    private void getLendCount() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allLend(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                lendCount=lendCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                lendCount=lendCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                lendCount=lendCount+number;
            }
            else {
                lendCount=lendCount+number;
            }

        }
    }

    private void getFoodCount() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        DataBaseManager obj=new DataBaseManager(BudgetClass.this);

        Cursor result = obj.allFood(username,session);
        while (result.moveToNext()) {

            String copy=result.getString(2);
            String[] getNumber=copy.split(" ");
            String currency=getNumber[1];
            float number=Float.parseFloat(getNumber[0]);
            if (currency.matches("Rs")){
                number=number*0.013f;
                foodCount=foodCount+number;
            }
            else if(currency.matches("€")){
                number=number*1.12f;
                foodCount=foodCount+number;
            }
            else if(currency.matches("₹")){
                number=number*0.013f;
                foodCount=foodCount+number;
            }
            else {
                foodCount=foodCount+number;
            }

        }

    }


    private void setupPieChart() {
        List<PieEntry> pieEnteries=new ArrayList<>();
        for(int i = 0; i< pieAmount.length; i++){
            if(pieAmount[i]!=0) {
                pieEnteries.add(new PieEntry(pieAmount[i], categoryName[i]));
            }
        }
        PieDataSet dataSet=new PieDataSet(pieEnteries,"Expense Data");
        dataSet.setValueTextSize(15f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);
        PieChart chart= (PieChart)findViewById(R.id.chart1);
        chart.setData(data);
        chart.invalidate();
    }


}
