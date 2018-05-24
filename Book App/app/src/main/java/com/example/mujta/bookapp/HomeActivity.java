package com.example.mujta.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    //created 4 variables for all objects created in the xml layout
    private TextView tvEmail;
    private Button btnWebservice;
    private Button btnLibraryMap;
    private Button btnQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Assigned the variables to the id of the objects
        tvEmail = findViewById(R.id.tvEmailProfile);
        //The email passed on from the login activity will be displayed in the home screen
        tvEmail.setText(getIntent().getExtras().getString("Email"));
        btnWebservice = (Button)findViewById(R.id.btnBooks);
        btnLibraryMap = (Button)findViewById(R.id.btnMap);
        btnQuotes = (Button)findViewById(R.id.btnQuotes);

        //When user selects the webservice button, they will be taken to the web service activity which displays all the books
        btnWebservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WebServiceActivity.class);
                startActivity(intent);
            }
        });
        //When user selects the maps button, they will be taken to the google maps activity
        btnLibraryMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, PlacePickerActivity.class);
                startActivity(i);
            }
        });
        //When user selects the quotes button, they will be taken to the books quote activity
        btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, SaveQuoteActivity.class);
                startActivity(i);
            }
        });
    }


}
