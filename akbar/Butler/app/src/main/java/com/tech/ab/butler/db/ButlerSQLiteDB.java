package com.tech.ab.butler.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tech.ab.butler.algo.computeconstants.ComputeConstants;
import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.algo.entities.TimePeriod;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shreenath on 26/1/17.
 */

public class ButlerSQLiteDB {
    private ButlerSQLiteDBHelper dbHelper;
    private SQLiteDatabase database;

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


    public ButlerSQLiteDB(Context context) {
        dbHelper = new ButlerSQLiteDBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public List<Task> getAvailableTasks() throws ParseException {
        List<Task> allTasks = new ArrayList<>();
        String getFutureTasks = "SELECT * FROM " + TABLE_NAME + " WHERE " + STATUS +"= 'FUTURE'";
        Cursor r = database.rawQuery(getFutureTasks, null);

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

        database.insert(TABLE_NAME, null, row);
    }

    public void deleteTaskbByTaskId(String taskId) {
        database.delete(TABLE_NAME , TASK_ID + " = ?", new String[] {taskId});
    }

    public Task getTaskByTaskId(String taskId) throws ParseException {
        Cursor r = database.query(TABLE_NAME,
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
        ContentValues newValues = new ContentValues();
        newValues.put(STATUS, status.toString());
        database.update(TABLE_NAME, newValues, TASK_ID + " = ?", new String[]{taskId});
    }
}
