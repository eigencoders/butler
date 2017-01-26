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

public class IncidentalTaskActivity extends AppCompatActivity {

    Spinner incidentalFrequencySpinner,incidentalPrioritySpinner, incidentalTimeAffinitySpinner;
    EditText etIncidentalTaskName;
    MultiSelectSpinner incidentalPlaceMultiSpinner;
    Button btnIncidentalDeadlineDate,btnIncidentalDeadlineTime;
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

        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.placeList));
        incidentalPlaceMultiSpinner.setItems(list, "Choose a Place", new MultiSelectSpinner.MultiSelectSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                for(int i=0; i<selected.length; i++) {
                    if(selected[i]) {
                        //TODO - Add the selected items to database
                        Log.i("TAG", i + " : "+ list.get(i));
                    }
                }
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
