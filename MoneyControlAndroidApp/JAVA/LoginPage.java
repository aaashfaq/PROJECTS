package com.example.PennyWise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    static String usernamevalue;

    public String getUsernamevalue() {
        return usernamevalue;
    }

    public void setUsernamevalue(String usernamevalue) {
        Toast.makeText(this, usernamevalue, Toast.LENGTH_SHORT).show();
        this.usernamevalue = usernamevalue;
    }

    EditText username,password;
    Button login,register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        final loginHelperDB db=new loginHelperDB(LoginPage.this);

    }

    public void jil(View view){
        usernamevalue = username.getText().toString();
        String passwordvalue=password.getText().toString();
        setUsernamevalue(usernamevalue);
        loginHelperDB db1 = new loginHelperDB(LoginPage.this);

        if(db1.isloginvalid(usernamevalue,passwordvalue))
        {
            startActivity(new Intent(this, WindowSession.class));
            Toast.makeText(LoginPage.this,"login Successfull", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LoginPage.this,"wrong credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void jiga(View view){

        Intent intent=new Intent(LoginPage.this,Register_user.class);
        startActivity(intent);
    }

}
