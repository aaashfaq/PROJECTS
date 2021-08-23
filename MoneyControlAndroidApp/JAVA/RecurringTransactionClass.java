package com.example.PennyWise;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class RecurringTransactionClass extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView userList;
    String username,session;
    DataBaseManager obj;
    private DatePickerDialog.OnDateSetListener  mDateSetListener;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recurring_layout);
        userList = findViewById(R.id.lvRecurringTansaction);
        listItem = new ArrayList<>();

        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();
        obj = new DataBaseManager(RecurringTransactionClass.this);


        viewData();

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                int upToNCharacters = Integer.parseInt(data.substring(0,4).trim());
                obj.deleteOne(String.valueOf(upToNCharacters),session);
                Toast.makeText(RecurringTransactionClass.this, "Deleted transaction", Toast.LENGTH_SHORT).show();
                adapter.clear();
                userList.setAdapter(adapter);
                viewData();
                return false;
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value= (String)userList.getItemAtPosition(position);
                String[] parts = value.split("\n");
                String category=parts[1];
                String [] category1=category.split("  ");
                String category2=category1[1];
                String amount=parts[2];
                String [] amount1=amount.split("  ");
                String amount2=amount1[1];
                String tag=parts[4];
                String [] tag1=tag.split("  ");
                String tag2=tag1[1];
                String recurrsion= parts[5];
                String [] recurrsion1=recurrsion.split("  ");
                String recurrsion2=recurrsion1[1];
                String type=parts[6];
                String [] type1=type.split("  ");
                String type2=type1[1];
                showInputBox(category2, amount2, tag2, recurrsion2, type2);
            }
            

            public void showInputBox(final String category, final String amount, final String tag, final String recurrsion,final String type){
                final Dialog dialog=new Dialog(RecurringTransactionClass.this);
                dialog.setTitle("Input Box");
                dialog.setContentView(R.layout.input_box);
                TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmessage);
                txtMessage.setText("Update Date");
                txtMessage.setTextColor(Color.parseColor("#ff2222"));
                final TextView editText=(TextView) dialog.findViewById(R.id.txtinput);
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal=Calendar.getInstance();
                        int year=cal.get(Calendar.YEAR);
                        int month=cal.get(Calendar.MONTH);
                        int day=cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(RecurringTransactionClass.this,
                                android.R.style.Theme_Material_Dialog_MinWidth,
                                mDateSetListener,
                                year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                    }
                });
                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        editText.setText(date);
                    }
                };
                final Button done=(Button)dialog.findViewById(R.id.btdone);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String date=editText.getText().toString();
                        if(date==""){
                            Toast.makeText(RecurringTransactionClass.this, "Enter Date",Toast.LENGTH_LONG).show();
                        }
                        else {
                            DataBaseManager db = new DataBaseManager(RecurringTransactionClass.this);
                            String result = db.addrecord(category, amount, date, tag, " ", recurrsion, type, username,session);
                            dialog.dismiss();
                            Intent intent = new Intent(RecurringTransactionClass.this, AllTransactions.class);
                            startActivity(intent);
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    public void viewData() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();
        DataBaseManager obj=new DataBaseManager(RecurringTransactionClass.this);
        Cursor result = obj.getRecurringData(username,session);
        if (result.getCount() == 0) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {

                String copy="ID:  "+result.getString(0) + "\nCATEGORY:  " + result.getString(1) + "\nAMOUNT:  " + result.getString(2) + "\nDATE:  " + result.getString(3) + "\nTAG:  " + result.getString(4)+  "\nRECURSION:  "+ result.getString(6)+ "\nTYPE:  " + result.getString(7);
                listItem.add(copy);
            }
            adapter = new ArrayAdapter<>(RecurringTransactionClass.this, android.R.layout.simple_list_item_1,listItem);
            userList.setAdapter(adapter);
        }
    }



}