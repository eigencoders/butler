package com.tech.ab.butler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ankita on 15-Jan-17.
 */

public class ButlerSQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="TaskDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TABLE_NAME";

    public ButlerSQLiteDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**Creating Tables*
         * Table Name- TABLE_NAME
         * Table Fields- Name, Task ID, Dependent Task Id, Duration(mins.)
         * Status, Static Score, Frequency, Deadline, Start Time of Day, End Time of Day, Places
         */
        String createTableSyntax = "CREATE TABLE " + TABLE_NAME +" (" +
                "  `name` varchar(255)  NOT NULL," +
                "  `taskId` mediumint unsigned NOT NULL PRIMARY KEY," +
                "  `dependentTaskId` mediumint unsigned DEFAULT '0'," +
                "  `durationMins` double DEFAULT '0'," +
                "  `status` varchar(255)  NOT NULL DEFAULT 'FUTURE'," +
                "  `staticScore` double DEFAULT '0'," +
                "  `frequency` double DEFAULT '0'," +
                "  `deadline` DATETIME DEFAULT '2066-01-01 00:00:00'," +
                "  `startTimeOfTheDay` TIME DEFAULT '00:00:00'," +
                "  `endTimeOfTheDay` Time DEFAULT '23:59:59'," +
                "  `places` varchar(255)  NOT NULL DEFAULT 'OTHER') ";
        db.execSQL(createTableSyntax);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
}
