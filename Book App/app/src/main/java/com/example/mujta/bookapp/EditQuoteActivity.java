package com.example.mujta.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditQuoteActivity extends AppCompatActivity {

    //Created 3 variable for all objects created in the xml
    private Button btnSave;
    private Button btnDelete;
    private EditText editBookQuote;

    //refers to the DatabaseHelp created earlier
    DatabaseHelper mDatabaseHelper;

    private String selectedQuote;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quote);
        //The variables created are assigned to the id of the object in the xml
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editBookQuote = (EditText) findViewById(R.id.editBookQuote);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListQuoteActivity
        Intent receivedIntent = getIntent();

        //get the itemID passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //get the name passed as an extra
        selectedQuote = receivedIntent.getStringExtra("quote");

        //set the text to show the current selected name
        editBookQuote.setText(selectedQuote);


        //When user presses save, it saves the edited Quote to the Table
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editBookQuote.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID, selectedQuote);
                }else{
                    toastMessage("Field is empty");
                }
            }
        });
        //When user presses delete, it delets the Quote from the table
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedQuote);
                editBookQuote.setText("");
                toastMessage("Removed from Quote List");
            }
        });

    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
