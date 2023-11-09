package com.example.termwork2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Dbhandler extends SQLiteOpenHelper {

    private  static final String dbname = "my_db";
    private static final int version = 2;
    public  Dbhandler(Context context) {super(context,dbname,null,version);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE contacts(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT, phone_no TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public void addStudent(String name, String phone_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone_no",phone_no);
        db.insert("contacts",null,values);
        db.close();
    }

    public ArrayList<ContactModal> readStudents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorStudent = db.rawQuery("SELECT name, phone_no FROM CONTACTS",
                null);

        ArrayList<ContactModal> contactModalArrayList = new ArrayList<>();

        // move cursor to first position
        if(cursorStudent.moveToFirst()){
            do{
                // add data from cursor to arraylist
                contactModalArrayList.add(new ContactModal(cursorStudent.getString(0),
                        cursorStudent.getString(1)));
            }while (cursorStudent.moveToNext());
            // move cursor to nxt
        }
        cursorStudent.close();
        return contactModalArrayList;
    }

    public boolean updateStudent(String phone_no, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);

        // Define the WHERE clause to specify which row to update (based on roll number)
        String selection = "phone_no = ?";
        String[] selectionArgs = {phone_no};

        // Use the update method and check the result
        int rowsUpdated = db.update("contacts", values, selection, selectionArgs);

        // Close the database
        db.close();

        return rowsUpdated > 0; // Return true if one or more rows were updated, indicating success.
    }

    // Method to delete a student based on roll number
    public boolean deleteStudent(String phone_no) {
        SQLiteDatabase db = this.getWritableDatabase(); // Open the database for writing

        // Define the WHERE clause to specify which row to delete (based on roll number)
        String selection = "phone_no = ?";
        String[] selectionArgs = {phone_no};

        // Use the delete method and check the result
        int rowsDeleted = db.delete("contacts", selection, selectionArgs);

        // Close the database
        db.close();

        return rowsDeleted > 0; // Return true if one or more rows were deleted, indicating success.
    }
}
