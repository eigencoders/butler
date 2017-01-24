package com.tech.ab.butler.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.tech.ab.butler.R;
import com.tech.ab.butler.elements.MultiSelectSpinner;

public class RoutineTaskActivity extends AppCompatActivity {

    Spinner routineFrequencySpinner,routinePrioritySpinner, routineTimeAffinitySpinner;
    EditText etRoutineTaskName;
    MultiSelectSpinner routinePlaceMultiSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_task);
        etRoutineTaskName=(EditText)findViewById(R.id.etRoutineTaskName);
        routineFrequencySpinner=(Spinner)findViewById(R.id.routineSpinnerFrequency);
        routinePrioritySpinner=(Spinner)findViewById(R.id.routineSpinnerPriority);
        routineTimeAffinitySpinner=(Spinner)findViewById(R.id.routineSpinnerTimeAffinity);
        routinePlaceMultiSpinner =(MultiSelectSpinner)findViewById(R.id.routineSpinnerPlace);




    }
}
