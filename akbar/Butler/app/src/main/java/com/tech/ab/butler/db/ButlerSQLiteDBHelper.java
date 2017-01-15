package com.tech.ab.butler.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.algo.entities.TimePeriod;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankita on 15-Jan-17.
 */

public class ButlerSQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="TaskDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TABLE_NAME";

    private static final String TASK_NAME = "name";
    private static final String TASK_ID = "taskId";
    private static final String DEPENDENT_TASK_ID = "dependentTaskId";
    private static final String DURATION_MINS = "durationMins";
    private static final String STATUS = "status";
    private static final String STATIC_SCORE = "staticScore";
    private static final String FREQ = "frequency";
    private static final String DEADLINE = "deadline";
    private static final String START_TOD = "startTimeOfTheDay";
    private static final String END_TOD = "endTimeOfTheDay";
    private static final String PLACES = "places";

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
                "  UNIQUE KEY `id_UNIQUE` (`taskId`) ";
        db.execSQL(createTableSyntax);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Task> getAvailableTasks() throws ParseException {
        List<Task> allTasks = new ArrayList<>();
        String getFutureTasks = "SELECT * FROM " + TABLE_NAME + " WHERE " + STATUS +"= 'FUTURE'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor r = db.rawQuery(getFutureTasks, null);

        if(r.moveToFirst()) {
            do {
                TimePeriod timePeriod = new TimePeriod(r.getString(8), r.getString(9));
                Task t = new Task(r.getString(0),
                    r.getString(1),
                    r.getString(2),
                    r.getLong(3)* ComputeConstants.MINS_2_MS,
                    Status.valueOf(r.getString(4)),
                    r.getLong(5),
                    r.getInt(6),
                    ComputeConstants.format.parse(r.getString(7)),
                    timePeriod,
                    r.getString(10));
                allTasks.add(t);
            } while (r.moveToNext());
        }
        r.close();
        return allTasks;
    }

    public void insertTask(Task t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(TASK_NAME, t.getName());
        row.put(TASK_ID, t.getTaskId());
        row.put(DEPENDENT_TASK_ID, t.getDependentTaskId());
        row.put(DURATION_MINS, t.getDuration());
        row.put(STATUS, t.getStatus().toString());
        row.put(STATIC_SCORE, t.getStaticScore());
        row.put(FREQ,t.getFrequency());
        row.put(DEADLINE, t.getDeadline().toString());
        row.put(START_TOD, t.getTemporalAffinity().getStartTimeOfTheDay());
        row.put(END_TOD, t.getTemporalAffinity().getEndTimeOfTheDay());
        row.put(PLACES, t.getSpatialAffinity());

        db.insert(TABLE_NAME, null, row);
        db.close();
    }

    public void deleteTaskbByTaskId(String taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME , TASK_ID + " = ?", new String[] {taskId});
        db.close();
    }

    public Task getTaskByTaskId(String taskId) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor r = db.query(TABLE_NAME,
                null, //This is to get all columns
                TASK_ID + "=?",
                new String[] {taskId}, null, null, null, null);
        if (r != null)
            r.moveToFirst();
        TimePeriod timePeriod = new TimePeriod(r.getString(8), r.getString(9));
        Task t = new Task(r.getString(0),
                        r.getString(1),
                        r.getString(2),
                        r.getLong(3)* ComputeConstants.MINS_2_MS,
                        Status.valueOf(r.getString(4)),
                        r.getLong(5),
                        r.getInt(6),
                        ComputeConstants.format.parse(r.getString(7)),
                        timePeriod,
                        r.getString(10));
        r.close();
        return t;
    }

    public void updateTaskStatus(String taskId, Status status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(STATUS, status.toString());
        db.update(TABLE_NAME, newValues, TASK_ID + " = ?", new String[]{taskId});
        db.close();
    }
    
}
