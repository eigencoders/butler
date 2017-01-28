package com.tech.ab.butler.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.ab.butler.R;

public class SettingsActivity extends AppCompatActivity {

    TextView tvAddPlaces, tvYourName, tvAddedPlaces, btClearPlaces;
    final Activity context=this;
    SharedPreferences placeSharedPreferences;
    int placeCount = 0;

    void updateAddedPlacesText() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str = "";
        int count = prefs.getInt("placeCount",0);
        for (int i = 0; i < count; i++) {
            str =  str + prefs.getString("Value[" + i + "]", "") + "\n";
        }
        tvAddedPlaces.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tvAddPlaces=(TextView)findViewById(R.id.tvAddPlaces);
        tvAddedPlaces=(TextView)findViewById(R.id.tvAddedPlaces);
        tvYourName =(TextView)findViewById(R.id.tvYourName);
        btClearPlaces = (TextView)findViewById(R.id.btClearPlaces);
        placeSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!placeSharedPreferences.getString("yourName","").isEmpty())
            tvYourName.setText(placeSharedPreferences.getString("yourName",""));

        updateAddedPlacesText();
        tvAddPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.add_place_dialog, null);
                placeCount = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("placeCount",0);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                final EditText etAddPlaces = (EditText) promptsView
                        .findViewById(R.id.etAddPlaces);
                alertDialogBuilder
                        .setCancelable(true)
                        .setTitle("Add a place!")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        if(!etAddPlaces.getText().toString().trim().isEmpty()) {
                                            etAddPlaces.setText(etAddPlaces.getText());
                                            placeSharedPreferences.edit().putString("Value[" + placeCount + "]", etAddPlaces.getText().toString()).commit();
                                            placeCount += 1;
                                            placeSharedPreferences.edit().putInt("placeCount", placeCount).commit();
                                            updateAddedPlacesText();
                                        } else {
                                            etAddPlaces.setError("Cannot be left blank!");
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        updateAddedPlacesText();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                alertDialog.show();

            }
        });

        btClearPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog =  new AlertDialog.Builder(v.getContext())
                        .setMessage("Clear all saved places?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.d("placeCountShr", String.valueOf(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("placeCount",0)));
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("placeCount",0).commit();
                                Log.d("placeCountShr", String.valueOf(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("placeCount",0)));
                                updateAddedPlacesText();
                            }})
                        .setNegativeButton(android.R.string.no, null)
                        .create();
                dialog.show();
            }
        });

        tvYourName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.your_name_dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);
                final EditText etYourName = (EditText) promptsView
                        .findViewById(R.id.etYourName);
                alertDialogBuilder
                        .setCancelable(true)
                        .setTitle("Enter your Name!")
                        .setPositiveButton("Change",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        if(!etYourName.getText().toString().trim().isEmpty()) {
                                            etYourName.setText(etYourName.getText());
                                            placeSharedPreferences.edit().putString("yourName", etYourName.getText().toString()).commit();
                                            tvYourName.setText(placeSharedPreferences.getString("yourName", ""));
                                            Toast.makeText(context, String.format("Welcome, %s", placeSharedPreferences.getString("yourName", "null")), Toast.LENGTH_SHORT).show();
                                        } else {
                                            etYourName.setError("Empty Not allowed");
                                        }
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
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                // show it
                alertDialog.show();


            }
        });


    }
}
