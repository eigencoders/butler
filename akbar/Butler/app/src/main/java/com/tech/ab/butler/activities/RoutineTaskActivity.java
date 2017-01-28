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

public class RoutineTaskActivity extends AppCompatActivity {

    Spinner routineFrequencySpinner,routinePrioritySpinner, routineTimeAffinitySpinner;
    EditText etRoutineTaskName;
    MultiSelectSpinner routinePlaceMultiSpinner;
    ArrayList<String> placeDynamicList = new ArrayList<String>();
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;
    Button btnRoutineDeadlineDate,btnRoutineDeadlineTime, btnEnterRoutine;
    private String selectedPlaces = "";
    Task selectedTask = new Task();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_task);
        etRoutineTaskName=(EditText)findViewById(R.id.etRoutineTaskName);
        routineFrequencySpinner=(Spinner)findViewById(R.id.routineSpinnerFrequency);
        routinePrioritySpinner=(Spinner)findViewById(R.id.routineSpinnerPriority);
        routineTimeAffinitySpinner=(Spinner)findViewById(R.id.routineSpinnerTimeAffinity);
        routinePlaceMultiSpinner =(MultiSelectSpinner)findViewById(R.id.routineSpinnerPlace);
        btnRoutineDeadlineDate = (Button)findViewById(R.id.btnDateRoutine);
        btnRoutineDeadlineTime = (Button)findViewById(R.id.btnTimeRoutine);
        btnEnterRoutine = (Button)findViewById(R.id.btnEnterRoutine);

        placeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        placeCount = placeSharedPreferences.getInt("placeCount", 0);
        if(placeCount > 0){
            for(int i = 0; i < placeCount; i++){
                placeDynamicList.add(placeSharedPreferences.getString("Value["+i+"]", ""));
            }
        }

        routinePlaceMultiSpinner.setItems(placeDynamicList, "Choose a Place", new MultiSelectSpinner.MultiSelectSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                selectedPlaces ="";
                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        Log.i("TAG", i + " : "+ placeDynamicList.get(i));
                        selectedPlaces = selectedPlaces + placeDynamicList.get(i)+",";
                    }
                }
                Toast.makeText(RoutineTaskActivity.this, selectedPlaces, Toast.LENGTH_LONG).show();
            }
        });

        btnRoutineDeadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        btnRoutineDeadlineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        btnEnterRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTask.setName(etRoutineTaskName.getText().toString());
                selectedTask.setTaskId("tid"); //TODO
                selectedTask.setDependentTaskId("dtid"); //TODO We need to have a tasks drop down, or a task Selecter screen
                selectedTask.setDuration((long)100); //TODO
                selectedTask.setFrequency(freqStringToInt(routineFrequencySpinner.getItemAtPosition(routineFrequencySpinner.getSelectedItemPosition()).toString()));
                selectedTask.setSpatialAffinity(selectedPlaces);
                selectedTask.setStaticScore(routinePrioritySpinner.getSelectedItemId());
                selectedTask.setStatus(Status.FUTURE);
                selectedTask.setTemporalAffinity(getTimeAffinityFromId((int) routineTimeAffinitySpinner.getSelectedItemId()));
                Toast.makeText(RoutineTaskActivity.this, "Selected Values : " + selectedTask.toString(), Toast.LENGTH_SHORT).show();
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
