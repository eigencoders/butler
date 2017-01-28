package com.tech.ab.butler.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.elements.DatePickerDialogFragment;
import com.tech.ab.butler.elements.MultiSelectSpinner;
import com.tech.ab.butler.elements.TimePickerDialogFragment;

import java.util.ArrayList;
import java.util.Date;

import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.getTimeAffinityFromId;

public class IncidentalTaskActivity extends AppCompatActivity {

    Spinner incidentalPrioritySpinner, incidentalTimeAffinitySpinner;
    EditText etIncidentalTaskName;
    MultiSelectSpinner incidentalPlaceMultiSpinner;
    ArrayList<String> placeDynamicList = new ArrayList<String>();
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;
    TextView tvIncidentalDeadlineDate,tvIncidentalDeadlineTime,tvIncidentalDuration;
    Button btnEnterIncidental;
    private String selectedPlaces = "";
    Task selectedTask = new Task();
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        if(etIncidentalTaskName.getText().toString().isEmpty())
        {
            etIncidentalTaskName.setError("Please fill the Task Name");
        }

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

        tvIncidentalDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDurationPickerDialog(v);

            }
        });
        tvIncidentalDeadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        tvIncidentalDeadlineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        btnEnterIncidental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTask.setName(etIncidentalTaskName.getText().toString());
                Long tsLong = System.currentTimeMillis()/1000;
                String taskID = tsLong.toString();
                selectedTask.setTaskId(taskID);
                selectedTask.setDependentTaskId("dtid"); //TODO We need to have a tasks drop down, or a task Selecter screen
                selectedTask.setSpatialAffinity(selectedPlaces);
                selectedTask.setStaticScore(incidentalPrioritySpinner.getSelectedItemId());
                selectedTask.setStatus(Status.FUTURE);
                selectedTask.setTemporalAffinity(getTimeAffinityFromId((int) incidentalTimeAffinitySpinner.getSelectedItemId()));
                Toast.makeText(IncidentalTaskActivity.this, "Selected Values : " + selectedTask.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerDialogFragment( new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedTask.setDeadline(new Date(year, month, dayOfMonth));
                String deadlineDate=  String.format("%d/%d/%d",dayOfMonth,month+1,year);
                tvIncidentalDeadlineDate.setText(deadlineDate);
            }
        }, getApplicationContext());
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
        //TODO - Correct Time Picker
        //String deadlineTime=  String.format("%d/%d/%d",dayOfMonth,month,year);
        //tvIncidentalDeadlineTime.setText(deadlineTime);
    }

    public void showDurationPickerDialog(View v)
    {
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_duration_dialog, null);
        d.setTitle("Task Duration");
        d.setView(dialogView);
        final NumberPicker hourNumberPicker = (NumberPicker) dialogView.findViewById(R.id.hour_number_picker);
        hourNumberPicker.setMaxValue(24);
        hourNumberPicker.setMinValue(0);
        hourNumberPicker.setWrapSelectorWheel(false);
        final NumberPicker minuteNumberPicker = (NumberPicker) dialogView.findViewById(R.id.minute_number_picker);
        minuteNumberPicker.setMinValue(1);
        minuteNumberPicker.setMaxValue(4);
        minuteNumberPicker.setDisplayedValues(new String[]{"0","15","30","45"});
        minuteNumberPicker.setWrapSelectorWheel(false);
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String durationString= String.format("%d h %d m",hourNumberPicker.getValue(),minuteNumberPicker.getValue());
                long durationMins=hourNumberPicker.getValue()*60+(minuteNumberPicker.getValue()-1)*15;
                selectedTask.setDuration(durationMins);
                Toast.makeText(context, durationString, Toast.LENGTH_SHORT).show();
                tvIncidentalDuration.setText(durationString);
                Log.d("NumberPicker", "onClick: " + hourNumberPicker.getValue());
            }
        });
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = d.create();
        alertDialog.show();
    }
}
