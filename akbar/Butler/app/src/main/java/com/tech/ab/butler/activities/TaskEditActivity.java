package com.tech.ab.butler.activities;

/**
 * Created by Pratik on 04-Feb-17.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.db.ButlerSQLiteDB;
import com.tech.ab.butler.elements.MultiSelectSpinner;

import java.text.ParseException;
import java.util.ArrayList;

import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.getTimeAffinityFromId;


public class TaskEditActivity extends Activity {



    Spinner incidentalPrioritySpinner, incidentalTimeAffinitySpinner;
    MultiSelectSpinner incidentalPlaceMultiSpinner;
    TextView tvIncidentalDeadlineDate,tvIncidentalDeadlineTime,tvIncidentalDuration;
    EditText etIncidentalTaskName;
    Button btnEnterIncidental;
    private String selectedPlaces = "";
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;
    ArrayList<String> placeDynamicList = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidental_task);
        etIncidentalTaskName=(EditText)findViewById(R.id.etIncidentalTaskName);
        incidentalPrioritySpinner=(Spinner)findViewById(R.id.incidentalSpinnerPriority);
        incidentalTimeAffinitySpinner=(Spinner)findViewById(R.id.incidentalSpinnerTimeAffinity);
        incidentalPlaceMultiSpinner =(MultiSelectSpinner)findViewById(R.id.incidentalSpinnerPlace);
        tvIncidentalDeadlineDate = (TextView) findViewById(R.id.tvDeadlineDateIncidental);
        tvIncidentalDeadlineTime = (TextView) findViewById(R.id.tvDeadlineTimeIncidental);
        tvIncidentalDuration = (TextView) findViewById(R.id.tvDurationIncidental);
        btnEnterIncidental = (Button)findViewById(R.id.btnEnterIncidental);
        Intent taskPageIntent = getIntent();

        Task taskObj = (Task) taskPageIntent.getExtras().getSerializable("taskObj");
        String taskID=taskPageIntent.getExtras().getString("taskID");
        Log.d("getFromDB",""+taskID);
        ButlerSQLiteDB butlerSQLiteDB = new ButlerSQLiteDB(getApplicationContext());
        try {
            Task t= butlerSQLiteDB.getTaskByTaskId(taskID);
            Log.d("task",t.getName());

            incidentalPrioritySpinner.getSelectedItemId();
            etIncidentalTaskName.setText(t.getName());
            incidentalPrioritySpinner.setSelection(t.getStaticScore().intValue());
            incidentalTimeAffinitySpinner.setSelection(getTimeAffinityFromId(t.getTemporalAffinity()));
            tvIncidentalDuration.setText(t.getDuration().toString());
            Log.d("temporal affinity",t.getStaticScore().toString());

            placeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            placeCount = placeSharedPreferences.getInt("placeCount", 0);
            if(placeCount > 0){
                for(int i = 0; i < placeCount; i++){
                    placeDynamicList.add(placeSharedPreferences.getString("Value["+i+"]", ""));
                }
            }
            incidentalPlaceMultiSpinner.setItems(placeDynamicList, "Choose a Place",t.getSpatialAffinity(), new MultiSelectSpinner.MultiSelectSpinnerListener() {
                @Override
                public void onItemsSelected(boolean[] selected) {
                    selectedPlaces ="";
                    for(int i=0; i<selected.length; i++) {
                        if(selected[i]) {
                            Log.i("TAG", i + " : "+ placeDynamicList.get(i));
                            selectedPlaces = selectedPlaces + placeDynamicList.get(i)+",";
                        }
                    }
                    Toast.makeText(TaskEditActivity.this, selectedPlaces, Toast.LENGTH_LONG).show();
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //incidentalPrioritySpinner.setGravity(2);



    }

    /** This function add the headers to the table **/

}