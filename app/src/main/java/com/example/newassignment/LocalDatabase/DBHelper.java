package com.example.newassignment.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.newassignment.Util.Config;


public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public static final String SAMPLE_TABLE = "sample";

    public DBHelper(Context context) {
        super(context, Config.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase MyDb) {

        String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + Config.EMPLOYEE_TBL + "("
                + Config.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.EMPLOYEE_NAME + " TEXT, "
                + Config.EMPLOYEE_SALARY + " INTEGER, "
                + Config.EMPLOYEE_AGE + " INTEGER, "
                + Config.PROFILE_IMAGE + " VARCHAR "
                + ")";

        MyDb.execSQL(CREATE_TABLE_EMPLOYEE);









    }

    public void onUpgrade(SQLiteDatabase MyDb, int i, int i2) {
        MyDb.execSQL("Drop table if exists " + Config.EMPLOYEE_TBL);

        onCreate(MyDb);


    }


    public Boolean InsertEmployee(String id, String employee_name, String employee_salary, String employee_age, String profile_image) {

        SQLiteDatabase MyDb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.ID, id);
        contentValues.put(Config.EMPLOYEE_NAME, employee_name);
        contentValues.put(Config.EMPLOYEE_SALARY, employee_salary);
        contentValues.put(Config.EMPLOYEE_AGE, employee_age);
        contentValues.put(Config.PROFILE_IMAGE, profile_image);


        long result = MyDb.insert(Config.EMPLOYEE_TBL, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }


    public Cursor readEmployeeData() {

        SQLiteDatabase MyDb = this.getWritableDatabase();
        String first = "SELECT * FROM " + Config.EMPLOYEE_TBL + " ORDER BY " + Config.ID + " DESC";
        Cursor cursor = MyDb.rawQuery(first, null);
        return cursor;
    }



}
