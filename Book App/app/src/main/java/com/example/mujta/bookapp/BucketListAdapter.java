package com.example.mujta.bookapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mujta on 19/03/2018.
 */

public class BucketListAdapter extends ArrayAdapter <Book> {


    ArrayList<Book> books;
    Context context;
    int resource;



    //created BucketListAdapter and set the adapter

    public BucketListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Book> books) {
        super(context, resource, books);
        this.books = books;
        this.context = context;
        this.resource = resource;
    }


    //The objects in the xml layout display the json data being passed
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Inflate the layout
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView =  layoutInflater.inflate(R.layout.bucket_list, null, true);
        }
        //Sets the json data to each object in the xml
        Book book = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_Cover);
        //gets the cover url and sets it into the imageview
        Picasso.with(context).load(book.getCover()).into(imageView);

        //Sets the title, author, isbn and year to TextViews in the xml layout
        TextView titleTextView = (TextView) convertView.findViewById(R.id.list_Title);
        titleTextView.setText(book.getTitle());

        TextView authorTextView = (TextView) convertView.findViewById(R.id.list_author);
        authorTextView.setText(book.getAuthor());

        TextView isbnTextView = (TextView) convertView.findViewById(R.id.list_isbn);
        isbnTextView.setText(book.getIsbn());

        TextView yearTextView = (TextView) convertView.findViewById(R.id.list_year);
        yearTextView.setText(book.getYear());




        return convertView;
    }
}
