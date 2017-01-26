package com.tech.ab.butler.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tech.ab.butler.R;
import com.tech.ab.butler.elements.DatePickerDialogFragment;
import com.tech.ab.butler.elements.MultiSelectSpinner;
import com.tech.ab.butler.elements.TimePickerDialogFragment;

import java.util.Arrays;
import java.util.List;

public class RoutineTaskActivity extends AppCompatActivity {

    Spinner routineFrequencySpinner,routinePrioritySpinner, routineTimeAffinitySpinner;
    EditText etRoutineTaskName;
    MultiSelectSpinner routinePlaceMultiSpinner;
    Button btnRoutineDeadlineDate,btnRoutineDeadlineTime;
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

        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.placeList));
        routinePlaceMultiSpinner.setItems(list, "Choose a Place", new MultiSelectSpinner.MultiSelectSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        //TODO - Add the selected Items to Database
                        Log.i("TAG", i + " : "+ list.get(i));
                    }
                }
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

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }
}
