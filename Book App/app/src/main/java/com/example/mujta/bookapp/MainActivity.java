package com.example.mujta.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //When user selects the register button, they will be taken to the register activity
    public void btnRegister_Click(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
    //When user selects the login button, they will be taken to the login activity
    public void btnLogin_Click(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
