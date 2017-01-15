package com.tech.ab.butler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
         * Table Fields- Name, Task ID, Dependent Task Id, Duration(mins.)
         * Status, Static Score, Frequency, Deadline, Start Time of Day, End Time of Day, Places
         */
        db.execSQL("CREATE TABLE " + taskTable +" (" +
                "  `name` varchar(255)  NOT NULL," +
                "  `taskId` mediumint(9) unsigned NOT NULL AUTO_INCREMENT," +
                "  `dependentTaskId` mediumint(9) unsigned DEFAULT '0'," +
                "  `durationMins` double DEFAULT '0'," +
                "  `status` enum('ACTIVE','EXPIRED','FUTURE')  NOT NULL DEFAULT 'FUTURE'," +
                "  `staticScore` double DEFAULT '0'," +
                "  `frequency` double DEFAULT '0'," +
                "  `deadline` DATETIME DEFAULT '2066-01-01 00:00:00'," +
                "  `startTimeOfTheDay` TIME DEFAULT '00:00:00'," +
                "  `endTimeOfTheDay` Time DEFAULT '23:59:59'," +
                "  `places` varchar(255)  NOT NULL DEFAULT 'OTHER'," +
                "  PRIMARY KEY (`taskId`)," +
                "  UNIQUE KEY `id_UNIQUE` (`taskId`) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
}
