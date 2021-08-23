package com.example.PennyWise;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchClass extends AppCompatActivity {
    private Spinner spinner2;
    private Spinner spinner3;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDialog;
    Button submit;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    String date;
    String username,session;
    String category;
    DataBaseManager obj;
    String payment;
    ListView userList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        mDisplayDialog = (TextView) findViewById(R.id.DateofTransaction);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        //tv=(TextView)findViewById(R.id.filterview);
        submit=findViewById(R.id.button);


        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        userList = findViewById(R.id.lvSearch);
        obj = new DataBaseManager(SearchClass.this);

        listItem = new ArrayList<>();



        String[] Categories = getResources().getStringArray(R.array.Categories);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Categories);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter1);

        String[] PaymentMode = getResources().getStringArray(R.array.PaymentMode);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PaymentMode);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter2);

        mDisplayDialog.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SearchClass.this, android.R.style.Theme_Material_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDialog.setText(date);
            }
        };

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                int upToNCharacters = Integer.parseInt(data.substring(0,4).trim());
                obj.deleteOne(String.valueOf(upToNCharacters),session);
                Toast.makeText(SearchClass.this, "Deleted transaction", Toast.LENGTH_SHORT).show();
                adapter.clear();
                userList.setAdapter(adapter);
                showData();
                return false;
            }
        });
    }

    public void showData(){
        listItem.clear();

        DataBaseManager obj=new DataBaseManager(SearchClass.this);
        Cursor result;

        category = spinner2.getSelectedItem().toString();
        payment = spinner3.getSelectedItem().toString();
        date = mDisplayDialog.getText().toString();

        if(!"Select".equals(category) && !"Select".equals(payment) && !"Select".equals(date) ) {
            result = obj.getDatabyall(spinner2.getSelectedItem().toString(),spinner3.getSelectedItem().toString(),mDisplayDialog.getText().toString(), username,session);
            //Toast.makeText(SearchClass.this, "all not null", Toast.LENGTH_SHORT).show();
            Toast.makeText(SearchClass.this, category + " " + payment + " " + date, Toast.LENGTH_SHORT).show();
        }
        else if(!"Select".equals(category)&& "Select".equals(payment) && "Select".equals(date))
        {
            result=obj.getbyCategory(spinner2.getSelectedItem().toString(), username,session);
        }
        else if(!"Select".equals(category)&& !"Select".equals(payment) && "Select".equals(date))
        {
            result=obj.getbyCatandPayment(spinner2.getSelectedItem().toString(),spinner3.getSelectedItem().toString(), username,session);
        }
        else if(!"Select".equals(category)&& "Select".equals(payment) && !"Select".equals(date))
        {
            result=obj.getbyCatandDate(spinner2.getSelectedItem().toString(),mDisplayDialog.getText().toString(),username,session);
        }
        else if("Select".equals(category)&& !"Select".equals(payment) && "Select".equals(date))
        {
            result=obj.getbyPayment(spinner3.getSelectedItem().toString(), username,session);
        }
        else if("Select".equals(category)&& !"Select".equals(payment) && !"Select".equals(date))
        {
            result=obj.getbyPayandDate(spinner3.getSelectedItem().toString(),mDisplayDialog.getText().toString(), username,session);
        }
        else if("Select".equals(category)&& "Select".equals(payment) && !"Select".equals(date))
        {
            result=obj.getByDate(mDisplayDialog.getText().toString(),username,session);
        }
        else
        {
            result = obj.allData(username,session);
            Toast.makeText(SearchClass.this, "1"+category+"\n 2"+payment+"\n 3"+date, Toast.LENGTH_SHORT).show();
        }

        if (result != null && result.getCount() > 0)
        {
            while (result.moveToNext())
            {
                String copy=result.getString(0) + "   " + result.getString(1) + "   " + result.getString(2) + "   " + result.getString(3) + "   " + result.getString(4) + "   " + result.getString(5);
                listItem.add(copy);
            }
            adapter = new ArrayAdapter<>(SearchClass.this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(SearchClass.this, "no Data", Toast.LENGTH_LONG).show();
        }

        mDisplayDialog.setText("Select");
    }


}
