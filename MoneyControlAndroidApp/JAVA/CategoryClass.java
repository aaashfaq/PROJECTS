package com.example.PennyWise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryClass extends AppCompatActivity {
    public String cato;
    private RecyclerView recyclerView;
    Button Submit;
    EditText addNewCat;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_selection);

        recyclerView = findViewById(R.id.recycler_view);
        Submit = findViewById(R.id.btnAddCat);
        addNewCat = findViewById(R.id.etNewCat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        final List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.food, "Food", "Food Expenses"));
        modelClassList.add(new ModelClass(R.drawable.health, "Health", "Health Expenses"));
        modelClassList.add(new ModelClass(R.drawable.education, "Education", "Education Expenses"));
        modelClassList.add(new ModelClass(R.drawable.sports, "Sports", "Sports Expenses"));
        modelClassList.add(new ModelClass(R.drawable.ic_person_black_24dp,"Lend/Borrow", "Select from contacts"));
        modelClassList.add(new ModelClass(R.drawable.misc, "Misc", "Miscellaneous Expenses"));

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String copy= addNewCat.getText().toString().toLowerCase();
                for (int i=0; i< modelClassList.size(); i++){
                    if( copy.equals(modelClassList.get(i).getTitle().toLowerCase())){
                        Toast toast = Toast.makeText(getApplicationContext(), "Category already exists", Toast.LENGTH_SHORT);
                        toast.show();
                        flag=true;
                        break;
                    }
                }
                if( flag==false){
                    modelClassList.add(new ModelClass(R.drawable.ic_launcher_background, addNewCat.getText().toString(),
                            "User Defined Category"));
                    Toast toast = Toast.makeText(getApplicationContext(), "Category has been added", Toast.LENGTH_SHORT);
                    toast.show();
                }

                Adapter1 adapterr = new Adapter1(modelClassList);
                recyclerView.setAdapter(adapterr);
                adapterr.notifyDataSetChanged();
            }
        });

        Adapter1 adapterr = new Adapter1(modelClassList);
        recyclerView.setAdapter(adapterr);
        adapterr.notifyDataSetChanged();




    }

}
