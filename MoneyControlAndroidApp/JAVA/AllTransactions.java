package com.example.PennyWise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllTransactions extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DataBaseManager obj;
    ListView userList;
    String username,session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);


        userList = findViewById(R.id.lvTransactions);
        obj = new DataBaseManager(AllTransactions.this);
        listItem = new ArrayList<>();

        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                int upToNCharacters = Integer.parseInt(data.substring(5,8).trim());
                obj.deleteOne(String.valueOf(upToNCharacters),session);
                Toast.makeText(AllTransactions.this, "Deleted transaction", Toast.LENGTH_SHORT).show();
                adapter.clear();
                userList.setAdapter(adapter);
                viewData();
                return false;
            }
        });

        viewData();



    }

    public void filters(View view) {
        startActivity(new Intent(this, SearchClass.class));
    }

    public void viewData() {
        LoginPage u = new LoginPage();
        username = u.getUsernamevalue();
        WindowSession s=new WindowSession();
        session=s.getSession();
        Cursor result = obj.allData(username,session);
        if (result.getCount() == 0) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()) {
                String copy="ID:  "+result.getString(0) + "       CATEGORY:  " + result.getString(1) + "\n                AMOUNT:     " + result.getString(2) + "\n                DATE:            " + result.getString(3) + "     TAG:  " + result.getString(4);
                listItem.add(copy);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);
        }
    }
}
