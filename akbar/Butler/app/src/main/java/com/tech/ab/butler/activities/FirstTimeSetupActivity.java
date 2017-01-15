package com.tech.ab.butler.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tech.ab.butler.R;

public class FirstTimeSetupActivity extends AppCompatActivity {

    TextView tvAddPlaces, tvScheduleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup);

        tvAddPlaces=(TextView)findViewById(R.id.tvAddPlaces);
        tvScheduleName=(TextView)findViewById(R.id.tvScheduleName);

    }
}
