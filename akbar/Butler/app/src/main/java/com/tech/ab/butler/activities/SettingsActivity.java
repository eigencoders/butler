package com.tech.ab.butler.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tech.ab.butler.R;

public class SettingsActivity extends AppCompatActivity {

    TextView tvAddPlaces, tvScheduleName;
    EditText etAddPlaces,etScheduleName;
    final Activity context=this;
    SharedPreferences placeSharedPreferences;
    SharedPreferences.Editor pspEditor;
    int placeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tvAddPlaces=(TextView)findViewById(R.id.tvAddPlaces);
        tvScheduleName=(TextView)findViewById(R.id.tvScheduleName);
        placeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        pspEditor = placeSharedPreferences.edit();


        tvAddPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.add_place_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                final EditText etAddPlaces = (EditText) promptsView
                        .findViewById(R.id.etAddPlaces);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        etAddPlaces.setText(etAddPlaces.getText());
                                        pspEditor.putString("Value["+placeCount+"]", etAddPlaces.getText().toString());
                                        pspEditor.commit();
                                        placeCount += 1;
                                        pspEditor.putInt("placeCount", placeCount);
                                        pspEditor.commit();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });


        tvScheduleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.schedule_name_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                final EditText etScheduleName = (EditText) promptsView
                        .findViewById(R.id.etScheduleName);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Change",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        etScheduleName.setText(etScheduleName.getText());
                                        /*TODO-
                                          Maintain a variable for this.
                                         */
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });


    }
}
