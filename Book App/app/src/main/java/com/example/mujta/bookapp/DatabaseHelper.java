package com.example.mujta.bookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper {

    //Created for 4 variables which can't be changed, created a table name and the defined the columns
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Book_Quote";
    private static final String COL1 = "ID";
    private static final String COL2 = "Quote";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    //Created the table as assigned ID with a private key and the second column being the quotes which will display as text
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT)";
        db.execSQL(createTable);
    }

    //onUpgrade created to update the data
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //The user input will be added to the table where it'll be stored
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param quote
     * @return
     */
    public Cursor getItemID(String quote){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + quote + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newQuote
     * @param id
     * @param oldQuote
     */
    public void updateName(String newQuote, int id, String oldQuote){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newQuote + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldQuote + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newQuote);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param quote
     */
    public void deleteName(int id, String quote){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + quote + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + quote + " from database.");
        db.execSQL(query);
    }

}
























