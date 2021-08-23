package com.example.PennyWise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_user extends AppCompatActivity {

    EditText username,password;
    Button register,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.cancel);

    }

    public void registerButton(View view){
        String usernamevalue=username.getText().toString();
        String passwordvalue=password.getText().toString();
        loginHelperDB db=new loginHelperDB(Register_user.this);

        if(usernamevalue.length()>1 && passwordvalue.length()>1)
        {
            String result= db.addUser(usernamevalue,passwordvalue);
            if (result == "Registered user successfully"){
                Toast.makeText(Register_user.this, result ,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Register_user.this,LoginPage.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(Register_user.this, result ,Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
        }

    }
    public void cancelButton(View view){
        Intent intent=new Intent(Register_user.this,LoginPage.class);
        startActivity(intent);
    }
}
