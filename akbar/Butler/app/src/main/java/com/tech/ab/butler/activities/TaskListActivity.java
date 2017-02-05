package com.tech.ab.butler.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.db.ButlerSQLiteDB;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class TaskListActivity extends ActionBarListActivity {

    FloatingActionButton fabMain, fabRoutine, fabIncidentals;
    Intent routineIntent, incidentalIntent,settingsIntent,taskPageIntent;
    ListView lvTaskList;
    List<Task> tasks;
    boolean isFABOpen = false;
    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabMain = (FloatingActionButton) findViewById(R.id.fabMain);
        fabRoutine = (FloatingActionButton) findViewById(R.id.fabRoutine);
        fabIncidentals = (FloatingActionButton) findViewById(R.id.fabIncidentals);
        lvTaskList = (ListView)findViewById(R.id.content_task_list);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                    Log.d("fabThing","showFABMenu()");
                } else {
                    closeFABMenu();
                    Log.d("fabThing","closeFABMenu()");
                }
            }
        });
        fabRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TaskListActivity.this, "Clicked fabRoutine", Toast.LENGTH_SHORT).show();
                routineIntent = new Intent(getApplicationContext(), RoutineTaskActivity.class);
                startActivity(routineIntent);
            }
        });

        fabIncidentals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TaskListActivity.this, "Clicked fabIncidentals", Toast.LENGTH_SHORT).show();
                incidentalIntent = new Intent(getApplicationContext(),IncidentalTaskActivity.class);
                startActivity(incidentalIntent);
            }
        });

        lvTaskList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        historicX = event.getX();
                        historicY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (event.getX() - historicX < -DELTA) {
                            Toast.makeText(TaskListActivity.this, "SwipeLeft", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        else if (event.getX() - historicX > DELTA) {
                            Toast.makeText(TaskListActivity.this, "SwipeRight", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        break;

                    default:
                        return false;
                }
                return false;
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        ButlerSQLiteDB db = new ButlerSQLiteDB(getApplicationContext());
        List<String> taskStrList = new ArrayList<>();
        String[] taskDetailsArray = new String[]{};
        try {
            tasks = db.getAvailableTasks();
            for (Task t: tasks ) {
                taskStrList.add(t.getName().toString());
            }
            Log.d("sizeTask",""+tasks.size());
            taskDetailsArray = taskStrList.toArray(new String[taskStrList.size()]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, taskDetailsArray);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            settingsIntent=new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("click", "Position click " + position);
        String item = (String) getListAdapter().getItem(position);
        String taskID= (String)tasks.get(position).getTaskId();
        taskPageIntent = new Intent(getApplicationContext(),TaskPageActivity.class);
        taskPageIntent.putExtra("taskObj",tasks.get(position));
        startActivity(taskPageIntent);

    }

    @Override
    protected int getListViewId() {
        return R.id.content_task_list;
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabIncidentals.setVisibility(View.VISIBLE);
        fabRoutine.setVisibility(View.VISIBLE);
        fabMain.animate().rotationBy(360);
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fabIncidentals.setVisibility(View.INVISIBLE);
        fabRoutine.setVisibility(View.INVISIBLE);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("TaskList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
