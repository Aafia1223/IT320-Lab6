package com.myfirstapplication.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT_TABLE = "Student_Table";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_ID = "ID";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    //First time, the database will be accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatements = "Create TABLE " + STUDENT_TABLE + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_AGE + " INT )";
        db.execSQL(createTableStatements);
    }
    //In case, the database version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(StudentMod studentMod){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STUDENT_NAME, studentMod.getName());
        cv.put(COLUMN_STUDENT_AGE, studentMod.getAge());

        long insert = db.insert(STUDENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
        return true;
        }
    }

    public List<StudentMod> getEveryone() {
        List<StudentMod> returnList = new ArrayList<>();

        //get data from the database
        String queryString = "SELECT * FROM Student_Table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            //loop through the cursor (result set) and create new student objects and put them into the resultList
            do{
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int studentAge = cursor.getInt(2);

                StudentMod newStudent = new StudentMod(studentID, studentName, studentAge);
                returnList.add(newStudent);
            }while (cursor.moveToNext());
        }
        else{
            //Failure. Do not add anything to the list.
        }
        // close both the db and the cursor when done
        cursor.close();
        db.close();
        return returnList;
    }
}