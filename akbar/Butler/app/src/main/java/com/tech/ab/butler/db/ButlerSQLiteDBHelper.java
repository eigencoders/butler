package com.tech.ab.butler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ankita on 15-Jan-17.
 */

public class ButlerSQLiteDBHelper extends SQLiteOpenHelper {

    private  static  final  String DATABASE_NAME="TaskDB";
    private static  final  String taskTable = "taskTable";


    public ButlerSQLiteDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**Creating Tables*
         * Table Name- taskTable
         * Table Fields-
         *
         */
       // db.execSQL("CREATE TABLE "+taskTable+);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
