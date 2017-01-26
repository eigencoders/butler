package com.tech.ab.butler.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tech.ab.butler.R;



public class TaskListActivity extends AppCompatActivity {

    FloatingActionButton fabMain, fabRoutine, fabIncidentals;
    LinearLayout fabRoutineLayout, fabIncidentalsLayout;
    Intent routineIntent, incidentalIntent,settingsIntent;
    boolean isFABOpen = false;
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
        fabRoutineLayout = (LinearLayout) findViewById(R.id.fabRoutineLayout);
        fabIncidentalsLayout = (LinearLayout) findViewById(R.id.fabIncidentalsLayout);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    private void showFABMenu() {
        isFABOpen = true;
        fabIncidentalsLayout.setVisibility(View.VISIBLE);
        fabRoutineLayout.setVisibility(View.VISIBLE);
        fabMain.animate().rotationBy(360);
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fabIncidentalsLayout.setVisibility(View.INVISIBLE);
        fabRoutineLayout.setVisibility(View.INVISIBLE);
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
