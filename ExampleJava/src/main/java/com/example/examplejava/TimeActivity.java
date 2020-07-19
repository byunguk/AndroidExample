package com.example.examplejava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeActivity extends AppCompatActivity {
    private Spinner hourSpinner;
    private Spinner minuteSpinner;
    private Spinner amPmSpinner;

    private int hour;
    private int minute;
    private int amPm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setUi();
    }

    private void setUi() {
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        ((TextView)findViewById(R.id.date_text_view)).setText(sdf.format(c.getTime()));
        final String[] hours = getResources().getStringArray(R.array.hours);
        hourSpinner = findViewById(R.id.hour_spinner);
        ArrayAdapter hourAdapter = new ArrayAdapter(
        this, android.R.layout.simple_spinner_item,
                hours
        );
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hourAdapter);
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ) {
                hour = Integer.valueOf(hours[position]);
            }
        });

        final String[] minutes = getResources().getStringArray(R.array.minutes);
        minuteSpinner = findViewById(R.id.minute_spinner);
        ArrayAdapter minuteAdapter =  new ArrayAdapter(
        this, android.R.layout.simple_spinner_item,
                minutes
        );
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(minuteAdapter);
        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ) {
                minute = Integer.valueOf(minutes[position]);
            }
        });

        amPmSpinner = findViewById(R.id.am_pm_spinner);
        ArrayAdapter amPmAdapter =  new ArrayAdapter(
        this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.am_pm)
        );
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amPmSpinner.setAdapter(amPmAdapter);
        amPmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ) {
                amPm = position;
            }
        });

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.set(Calendar.HOUR, hour);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.AM_PM, amPm);

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.US);
                Toast.makeText(TimeActivity.this, sdf.format(c.getTime()), Toast.LENGTH_LONG).show();
            }
        });
    }
}
