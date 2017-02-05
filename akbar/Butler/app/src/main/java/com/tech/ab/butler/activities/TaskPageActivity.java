package com.tech.ab.butler.activities;

/**
 * Created by Ankita on 04-Feb-17.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.tech.ab.butler.R;
import com.tech.ab.butler.algo.entities.Task;

public class TaskPageActivity extends Activity {

    String taskFieldName[] = {"Name","Duration","Frequency","Deadline","Temporal Affinity","Spatial Affinity"};
    String taskFieldValue[] = {"Ambush Shree ;)","24 hrs","Daily","Lifetime","12 A.M. to 6 A.M","My Place"};

    TableLayout tl;
    TableRow tr;
    TextView companyTV,valueTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);
        tl = (TableLayout) findViewById(R.id.taskFieldTable);
        Intent taskPageIntent = getIntent();
        Task taskObj = (Task) taskPageIntent.getExtras().getSerializable("taskObj");
        taskFieldValue[0]=taskObj.getName();
        taskFieldValue[1]=taskObj.getDuration().toString();
        taskFieldValue[2]=taskObj.getFrequency().toString();
        taskFieldValue[3]=taskObj.getDeadline().toString();
        taskFieldValue[4]=taskObj.getTemporalAffinity().toString();
        taskFieldValue[5]=taskObj.getSpatialAffinity().toString();

        addHeaders();
        addData();
    }

    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView companyTV = new TextView(this);
        companyTV.setText("Task Properties");
        companyTV.setTextColor(Color.GRAY);
        companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        companyTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        companyTV.setPadding(5, 5, 5, 0);
        tr.addView(companyTV);	// Adding textView to tablerow.

        /** Creating another textview **/
        TextView valueTV = new TextView(this);
        valueTV.setText("Value");
        valueTV.setTextColor(Color.GRAY);
        valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        valueTV.setPadding(5, 5, 5, 0);
        valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(getResources().getColor(R.color.colorAccent));
        divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(getResources().getColor(R.color.colorAccent));
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT);
        tr.addView(divider2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
    }

    /** This function add the data to the table **/
    public void addData(){

        for (int i = 0; i < taskFieldName.length; i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            companyTV = new TextView(this);
            companyTV.setText(taskFieldName[i]);
            companyTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            companyTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            companyTV.setPadding(5, 5, 5, 5);
            tr.addView(companyTV);	// Adding textView to tablerow.

            /** Creating another textview **/
            valueTV = new TextView(this);
            valueTV.setText(taskFieldValue[i]);
            valueTV.setTextColor(getResources().getColor(R.color.colorBlack));
            valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
}