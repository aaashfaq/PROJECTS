package com.example.PennyWise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WindowSession extends AppCompatActivity {
    static String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windowsesssion);
    }

    public void house(View view)
    {
        setSession("house");
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(WindowSession.this, session, Toast.LENGTH_SHORT).show();
    }

    public void work(View view)
    {
        setSession("work");
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(WindowSession.this, session, Toast.LENGTH_SHORT).show();
    }

    public void other(View view)
    {
        setSession("other");
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(WindowSession.this, session, Toast.LENGTH_SHORT).show();
    }


}