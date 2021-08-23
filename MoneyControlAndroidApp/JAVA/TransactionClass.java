package com.example.PennyWise;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TransactionClass extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener  mDateSetListener;
    Spinner spinnerCategory;
    Button btnRecur;
    TextView txtRecur;
    Switch ioSwitch;
    String [] noOfCategory={"Card","Cash","Online"};
    String [] currency={"$","€","Rs","₹" };
    Spinner spinnerCurrency;
    Button submit;
    EditText etAmount;
    EditText notes;
    TextView etDate;
    String typeOfExpense;
    TextView categoryDisplay;
    Integer recursive;
    String [] recursiveList;
    String category_text;
    String usernamevalue,session;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_layout);
        btnRecur = findViewById(R.id.recur);
        txtRecur = findViewById(R.id.txtrecur);
        submit = findViewById(R.id.btnSubmit);
        etAmount = findViewById(R.id.etAmount);
        etDate = findViewById(R.id.tvDate);
        categoryDisplay =findViewById(R.id.txtcato);
        notes = findViewById(R.id.notes);
        ioSwitch = findViewById(R.id.ioSwitch);
        Intent i=getIntent();

        final String title=i.getStringExtra("title");
        categoryDisplay.setText(title);
      /*  if(categoryDisplay.getText().toString().substring(0, 4) == "Lend"){
            category_text = "Lend/Borrow";
        }else{
            category_text = categoryDisplay.getText().toString();}
      */
        category_text = categoryDisplay.getText().toString();
        LoginPage u = new LoginPage();
        usernamevalue = u.getUsernamevalue();

        WindowSession s=new WindowSession();
        session=s.getSession();


        spinnerCategory = findViewById(R.id.spinnerCategory);
        populateSpinnerCategory();
        spinnerCurrency = findViewById(R.id.spCurrency);
        populateSpinnerCurrency();

        ioSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ioSwitch.isChecked())
                {
                    typeOfExpense =  ioSwitch.getTextOn().toString();
                }
                else {
                    typeOfExpense =  ioSwitch.getTextOff().toString();
                }
            }
        });
        if (typeOfExpense == null){typeOfExpense = "Expense";}




        btnRecur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recursiveList=new String[]{"Daily","Weekly","Monthly","Annual"};
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(TransactionClass.this);
                mBuilder.setTitle("Select Frequency");
                mBuilder.setIcon(R.drawable.icon);
                mBuilder.setSingleChoiceItems(recursiveList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtRecur.setText(recursiveList[which]);
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog=mBuilder.create();
                mDialog.show();
            }
        });


        mDisplayDate=findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TransactionClass.this,
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
                mDisplayDate.setText(date);
            }
        };
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check=0;
                if(mDisplayDate.getText().toString()==""){
                    check=1;
                }

                String copy=etAmount.getText().toString()+" "+spinnerCurrency.getSelectedItem().toString();

                DataBaseManager db = new DataBaseManager(TransactionClass.this);


                if (etAmount.getText().toString().length() < 10 && etAmount.getText().toString().length() > 0 &&  check==0){

                        String result = db.addrecord(category_text , copy, mDisplayDate.getText().toString(), spinnerCategory.getSelectedItem().toString(), notes.getText().toString(), txtRecur.getText().toString(), typeOfExpense, usernamevalue,session);
                        Toast.makeText(TransactionClass.this, session ,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(TransactionClass.this, MainActivity.class));
                    }
                else if (etAmount.getText().toString().length() > 10 ||etAmount.getText().toString().length() == 0){
                    Toast.makeText(TransactionClass.this, "Enter less than 10 digits", Toast.LENGTH_SHORT).show();

                }
                else if(check==1) {
                    Toast.makeText(TransactionClass.this, "please fill all modules", Toast.LENGTH_SHORT).show();
                }

                etAmount.setText("");
                ioSwitch.setChecked(false);
                txtRecur.setText("");
                mDisplayDate.setText(null);
                spinnerCategory.setSelection(0);
                spinnerCurrency.setSelection(0);
                recursive = 0;
                notes.setText("");
                categoryDisplay.setText(" ");
            }
        });
    }



    private void populateSpinnerCategory() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,noOfCategory);
        categoryAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);
    }
    private void populateSpinnerCurrency() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,currency);
        categoryAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(categoryAdapter);
    }
}