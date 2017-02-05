package com.tech.ab.butler.activities;

/**
 * Created by Ankita on 04-Feb-17.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Task;

import static com.tech.ab.butler.algo.computeconstants.ComputeConstants.freqIntToString;
import static com.tech.ab.butler.elements.MiddleLayer.convertDeadlineToFancyString;
import static com.tech.ab.butler.elements.MiddleLayer.convertMiliSecToFancyString;
import static com.tech.ab.butler.elements.MiddleLayer.convertSpatialAffinityToFancyString;
import static com.tech.ab.butler.elements.MiddleLayer.getTimeAffinityR_Id;

public class TaskPageActivity extends Activity {

    String taskFieldName[] = {"Name","Duration","Frequency","Deadline","Temporal Affinity","Spatial Affinity"};
    String taskFieldValue[] = {"Ambush Shree ;)","24 hrs","Daily","Lifetime","12 A.M. to 6 A.M","My Place"};

    TableLayout tlTaskProps;
    TableRow taskPropRow;
    TextView tvTaskField, tvTaskValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);
        tlTaskProps = (TableLayout) findViewById(R.id.taskFieldTable);
        Intent taskPageIntent = getIntent();
        Task taskObj = (Task) taskPageIntent.getExtras().getSerializable("taskObj");
        taskFieldValue[0]=taskObj.getName();
        taskFieldValue[1]=convertMiliSecToFancyString(taskObj.getDuration());
        taskFieldValue[2]=freqIntToString(taskObj.getFrequency());
        taskFieldValue[3]=convertDeadlineToFancyString(taskObj.getDeadline());
        taskFieldValue[4]=getResources().getString(getTimeAffinityR_Id(taskObj));
        taskFieldValue[5]=convertSpatialAffinityToFancyString(taskObj.getSpatialAffinity());

        addData();
    }

    public void addData(){

        for (int i = 0; i < taskFieldName.length; i++)
        {
            /** Create a TableRow dynamically **/
            taskPropRow = new TableRow(this);
            taskPropRow.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            tvTaskField = new TextView(this);
            tvTaskField.setText(taskFieldName[i]);
            tvTaskField.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tvTaskField.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvTaskField.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            tvTaskField.setPadding(20, 10, 20, 10);
            taskPropRow.addView(tvTaskField);	// Adding textView to tablerow.

            /** Creating another textview **/
            tvTaskValue = new TextView(this);
            tvTaskValue.setText(taskFieldValue[i]);
            tvTaskValue.setTextColor(getResources().getColor(R.color.colorBlack));
            tvTaskValue.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            tvTaskValue.setPadding(20, 10, 20, 10);
            tvTaskValue.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            taskPropRow.addView(tvTaskValue); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tlTaskProps.addView(taskPropRow, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
}