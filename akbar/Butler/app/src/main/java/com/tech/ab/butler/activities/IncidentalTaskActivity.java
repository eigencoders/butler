package com.tech.ab.butler.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.elements.DatePickerDialogFragment;
import com.tech.ab.butler.elements.MultiSelectSpinner;
import com.tech.ab.butler.elements.TimePickerDialogFragment;

import java.util.ArrayList;
import java.util.Date;

import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.freqStringToInt;
import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.getTimeAffinityFromId;

public class IncidentalTaskActivity extends AppCompatActivity {

    Spinner incidentalFrequencySpinner,incidentalPrioritySpinner, incidentalTimeAffinitySpinner;
    EditText etIncidentalTaskName;
    MultiSelectSpinner incidentalPlaceMultiSpinner;
    ArrayList<String> placeDynamicList = new ArrayList<String>();
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;
    Button btnIncidentalDeadlineDate,btnIncidentalDeadlineTime, btnEnterIncidental;
    private String selectedPlaces = "";
    Task selectedTask = new Task();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidental_task);
        etIncidentalTaskName=(EditText)findViewById(R.id.etIncidentalTaskName);
        incidentalFrequencySpinner=(Spinner)findViewById(R.id.incidentalSpinnerFrequency);
        incidentalPrioritySpinner=(Spinner)findViewById(R.id.incidentalSpinnerPriority);
        incidentalTimeAffinitySpinner=(Spinner)findViewById(R.id.incidentalSpinnerTimeAffinity);
        incidentalPlaceMultiSpinner =(MultiSelectSpinner)findViewById(R.id.incidentalSpinnerPlace);
        btnIncidentalDeadlineDate = (Button)findViewById(R.id.btnDateIncidental);
        btnIncidentalDeadlineTime = (Button)findViewById(R.id.btnTimeIncidental);
        btnEnterIncidental = (Button)findViewById(R.id.btnEnterIncidental);

        placeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        placeCount = placeSharedPreferences.getInt("placeCount", 0);
        if(placeCount > 0){
            for(int i = 0; i < placeCount; i++){
                placeDynamicList.add(placeSharedPreferences.getString("Value["+i+"]", ""));
            }
        }

        incidentalPlaceMultiSpinner.setItems(placeDynamicList, "Choose a Place", new MultiSelectSpinner.MultiSelectSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedPlaces ="";
                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        Log.i("TAG", i + " : "+ placeDynamicList.get(i));
                        selectedPlaces = selectedPlaces + placeDynamicList.get(i)+",";
                    }
                }
                Toast.makeText(IncidentalTaskActivity.this, selectedPlaces, Toast.LENGTH_LONG).show();
            }
        });

        btnIncidentalDeadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        btnIncidentalDeadlineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        btnEnterIncidental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTask.setName(etIncidentalTaskName.getText().toString());
                selectedTask.setTaskId("tid"); //TODO
                selectedTask.setDependentTaskId("dtid"); //TODO We need to have a tasks drop down, or a task Selecter screen
                selectedTask.setDuration((long)100); //TODO
                selectedTask.setFrequency(freqStringToInt(incidentalFrequencySpinner.getItemAtPosition(incidentalFrequencySpinner.getSelectedItemPosition()).toString()));
                selectedTask.setSpatialAffinity(selectedPlaces);
                selectedTask.setStaticScore(incidentalPrioritySpinner.getSelectedItemId());
                selectedTask.setStatus(Status.FUTURE);
                selectedTask.setTemporalAffinity(getTimeAffinityFromId((int) incidentalTimeAffinitySpinner.getSelectedItemId()));
                Toast.makeText(IncidentalTaskActivity.this, "Selected Values : " + selectedTask.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerDialogFragment( new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedTask.setDeadline(new Date(year, month, dayOfMonth));
            }
        }, getApplicationContext());
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }
}
