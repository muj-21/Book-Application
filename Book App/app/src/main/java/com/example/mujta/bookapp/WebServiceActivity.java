package com.example.mujta.bookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class WebServiceActivity extends AppCompatActivity {

    //Created 2 variables for all all objects created in the xml layout
    private Button btnWebservice;
    EditText searchBook;

    private BucketListAdapter adapter;


    //The variables regarding Books won't change
    public static final String Key_Title = "book_title";
    public static final String Key_Author = "book_author";
    public static final String Key_Cover = "book_cover";
    public static final String Key_ISBN = "book_isbn";
    public static final String Key_Year = "book_year";



    //return name of underlying class
    private String classtag= WebServiceActivity.class.getSimpleName();
    ArrayList<Book> bookslist;
    ListView lv;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);




        bookslist = new ArrayList<>();
//        adapter = new BucketListAdapter(WebServiceActivity.this, getData());
        lv = (ListView) findViewById(R.id.idBookList);
        lv.setAdapter(adapter);




        //When a user selects an item from the table list view, the information of that Book will be displayed in another activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book item = (Book) lv.getAdapter().getItem(position);
//                Log.d("STATE", "Book Title is " + item.getTitle());
                Intent intent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                intent.putExtra(Key_Title, item.getTitle());
                intent.putExtra(Key_Author, item.getAuthor());
                intent.putExtra(Key_Cover, item.getCover());
                intent.putExtra(Key_ISBN, item.getIsbn());
                intent.putExtra(Key_Year, item.getYear());

                startActivity(intent);



            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //user to the JSON being used
                new ReadJSON().execute("https://next.json-generator.com/api/json/get/Ny6TipddN");
            }
        });




    }


    //created a class which reads the json that is being retrieved
    class ReadJSON extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                //gets the name of the array of dictionaries from the web service
                JSONArray jsonArray =  jsonObject.getJSONArray("Books");

                //creates an object from values in the array
                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject bookObject = jsonArray.getJSONObject(i);
                    bookslist.add(new Book(
                            bookObject.getString("title"),
                            bookObject.getString("author"),
                            bookObject.getString("cover"),
                            bookObject.getString("isbn"),
                            bookObject.getString("year")
                    ));
                }

                //throws an error regarding the JSON
            } catch (JSONException e) {
                e.printStackTrace();
            }

            BucketListAdapter adapter = new BucketListAdapter(
                    getApplicationContext(), R.layout.bucket_list, bookslist
            );
            lv.setAdapter(adapter);
        }


    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
