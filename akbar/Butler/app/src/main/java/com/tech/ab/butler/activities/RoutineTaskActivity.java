package com.tech.ab.butler.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Status;
import com.tech.ab.butler.algo.entities.Task;
import com.tech.ab.butler.db.ButlerSQLiteDB;
import com.tech.ab.butler.elements.DatePickerDialogFragment;
import com.tech.ab.butler.elements.MultiSelectSpinner;
import com.tech.ab.butler.elements.TimePickerDialogFragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.freqStringToInt;
import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.getTimeAffinityFromId;

public class RoutineTaskActivity extends AppCompatActivity {

    Spinner routineFrequencySpinner,routinePrioritySpinner, routineTimeAffinitySpinner;
    EditText etRoutineTaskName,etRoutineDuration;
    MultiSelectSpinner routinePlaceMultiSpinner;
    ArrayList<String> placeDynamicList = new ArrayList<String>();
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;
    TextView tvRoutineDeadlineDate,tvRoutineDeadlineTime;
    Button btnEnterRoutine;
    private String selectedPlaces = "";
    private int sDay, sMonth, sYear, sHour, sMin;
    Task selectedTask = new Task();
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_task);
        etRoutineTaskName=(EditText)findViewById(R.id.etRoutineTaskName);
        routineFrequencySpinner=(Spinner)findViewById(R.id.routineSpinnerFrequency);
        routinePrioritySpinner=(Spinner)findViewById(R.id.routineSpinnerPriority);
        routineTimeAffinitySpinner=(Spinner)findViewById(R.id.routineSpinnerTimeAffinity);
        routinePlaceMultiSpinner =(MultiSelectSpinner)findViewById(R.id.routineSpinnerPlace);
        tvRoutineDeadlineDate = (TextView) findViewById(R.id.tvDeadlineDateRoutine);
        tvRoutineDeadlineTime = (TextView) findViewById(R.id.tvDeadlineTimeRoutine);
        etRoutineDuration = (EditText) findViewById(R.id.tvDurationRoutine);
        btnEnterRoutine = (Button)findViewById(R.id.btnEnterRoutine);

        if(etRoutineTaskName.getText().toString().isEmpty())
        {
            etRoutineTaskName.setError("Please fill the Task Name");
        }

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
                        selectedPlaces = selectedPlaces + placeDynamicList.get(i)+",";
                    }
                }
                Toast.makeText(RoutineTaskActivity.this, selectedPlaces, Toast.LENGTH_LONG).show();
            }
        });

        etRoutineDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDurationPickerDialog(v);
            }
        });

        tvRoutineDeadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        tvRoutineDeadlineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        btnEnterRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTask.setName(etRoutineTaskName.getText().toString());
                Long tsLong = System.currentTimeMillis()/1000;
                String taskIDString = tsLong.toString();
                selectedTask.setTaskId(taskIDString);
                selectedTask.setDependentTaskId("dtid"); //TODO We need to have a tasks drop down, or a task Selecter screen
                selectedTask.setFrequency(freqStringToInt(routineFrequencySpinner.getItemAtPosition(routineFrequencySpinner.getSelectedItemPosition()).toString()));
                selectedTask.setSpatialAffinity(selectedPlaces);
                selectedTask.setStaticScore(routinePrioritySpinner.getSelectedItemId());
                selectedTask.setStatus(Status.FUTURE);
                selectedTask.setTemporalAffinity(getTimeAffinityFromId((int) routineTimeAffinitySpinner.getSelectedItemId()));
                selectedTask.setDeadline(new Date(sYear,sMonth,sDay,sHour,sMin,0));
                Toast.makeText(RoutineTaskActivity.this, "Selected Values : " + selectedTask.toString(), Toast.LENGTH_SHORT).show();
                ButlerSQLiteDB butlerSQLiteDB = new ButlerSQLiteDB(getApplicationContext());
                butlerSQLiteDB.insertTask(selectedTask);
                try {
                    Log.d("getFromDB",""+butlerSQLiteDB.getAvailableTasks().size());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), TaskListActivity.class));
            }
        });
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerDialogFragment( new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sYear=year;
                sDay=dayOfMonth;
                sMonth=month;
                String deadlineDate=  String.format("%d/%d/%d",dayOfMonth,month+1,year);
                tvRoutineDeadlineDate.setText(deadlineDate);
            }
        }, getApplicationContext());
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerDialogFragment(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                sHour=hourOfDay;
                sMin=minute;
                String deadlineTime = String.format("%d:%d",sHour,sMin);
                tvRoutineDeadlineTime.setText(deadlineTime);
            }
        }, getApplicationContext());
        newFragment.show(getSupportFragmentManager(), "TimePicker");
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
        minuteNumberPicker.setMaxValue(4);
        minuteNumberPicker.setMinValue(1);
        minuteNumberPicker.setDisplayedValues(new String[]{"0","15","30","45"});
        minuteNumberPicker.setWrapSelectorWheel(false);
        d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int minsChosen=(minuteNumberPicker.getValue()-1)*15;
                String durationString= String.format("%d h %d m",hourNumberPicker.getValue(),minsChosen);
                long durationMins=hourNumberPicker.getValue()*60+minsChosen;
                selectedTask.setDuration(durationMins);
                Toast.makeText(context, durationString, Toast.LENGTH_SHORT).show();
                etRoutineDuration.setText(durationString);
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
