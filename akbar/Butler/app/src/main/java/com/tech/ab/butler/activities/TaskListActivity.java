package com.tech.ab.butler.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tech.ab.butler.R;

public class TaskListActivity extends AppCompatActivity {

    FloatingActionButton fabMain,fabRoutine,fabIncidentals;
    LinearLayout fabRoutineLayout,fabIncidentalsLayout;
    boolean isFABOpen=false;
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
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        fabRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskListActivity.this, "Clicked fabRoutine", Toast.LENGTH_SHORT).show();
            }
        });

        fabIncidentals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskListActivity.this, "Clicked fabIncidentals", Toast.LENGTH_SHORT).show();
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFABMenu(){
        isFABOpen=true;
        fabIncidentalsLayout.setVisibility(View.VISIBLE);
        fabRoutineLayout.setVisibility(View.VISIBLE);
        fabMain.animate().rotationBy(360);
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabIncidentalsLayout.setVisibility(View.INVISIBLE);
        fabRoutineLayout.setVisibility(View.INVISIBLE);
    }
}
