package com.example.mujta.bookapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.mujta.bookapp.WebServiceActivity.Key_Author;
import static com.example.mujta.bookapp.WebServiceActivity.Key_Cover;
import static com.example.mujta.bookapp.WebServiceActivity.Key_ISBN;
import static com.example.mujta.bookapp.WebServiceActivity.Key_Title;
import static com.example.mujta.bookapp.WebServiceActivity.Key_Year;

public class BookDetailsActivity extends AppCompatActivity {

    Button shareBbtn;
    Intent shareIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //empty variables
        String title = "";
        String author = "";
        String cover = "";
        String isbn = "";
        String year = "";

        final Intent intent = getIntent();
        //the empty variables contain the the values from the json being passed through
        if(null != intent){
            title = intent.getStringExtra(Key_Title);
            author = intent.getStringExtra(Key_Author);
            cover = intent.getStringExtra(Key_Cover);
            isbn = intent.getStringExtra(Key_ISBN);
            year = intent.getStringExtra(Key_Year);
        }
        //The objects in the xml layout display the json values that have been passed through
        TextView titleText = (TextView)findViewById(R.id.bookTitle);
        titleText.setText(title);

        TextView authorText = (TextView)findViewById(R.id.bookAuthor);
        authorText.setText(author);

        ImageView coverText = (ImageView)findViewById(R.id.bookCover);
        //used picasso library to convert url of the image to text
        Picasso.with(this).load(cover).into(coverText);

        TextView isbnText = (TextView)findViewById(R.id.bookIsbn);
        isbnText.setText(isbn);

        TextView yearText = (TextView)findViewById(R.id.bookYear);
        yearText.setText(year);

        //created variable for the share button
        shareBbtn = (Button)findViewById(R.id.btnShare);

        //when the clicks ont the share button, they will be able to share the Book to other peopl
        shareBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/pain");
                //The subject for the compossed email
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book App");
                //The message being sent
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I recommend reading " + intent.getStringExtra(Key_Title)+ " by " + intent.getStringExtra(Key_Author));
                startActivity(Intent.createChooser(shareIntent, "Share via"));

            }
        });



    }

}
