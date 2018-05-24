package com.example.mujta.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveQuoteActivity extends AppCompatActivity {

    //Create 3 variables, 1 references the Database and the other are object in the xml
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private Button btnViewData;
    private EditText bookQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_quote);
        //assign the varibale to id of the objects
        bookQuote = (EditText) findViewById(R.id.txtBookQuote);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);


        //when user presses add, it saves the quote to the database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = bookQuote.getText().toString();
                if (bookQuote.length() != 0) {
                    AddData(newEntry);
                    bookQuote.setText("");
                } else {
                    toastMessage("Field is empty");
                }
            }
        });

        //When the user presses the view data, they are taken to the list quote activity
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveQuoteActivity.this, ListQuoteActivity.class);
                startActivity(intent);
            }
        });
    }

    //Adds new entry to the database
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        //Given prompts whether it was successful or not
        if (insertData) {
            toastMessage("Data successfully added!");
        } else {
            toastMessage("Data couldn't save! ");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
