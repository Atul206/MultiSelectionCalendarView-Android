package com.roadster.sakhala.multselectioncalendarview.acitvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.roadster.sakhala.multselectioncalendarview.R;
import com.roadster.sakhala.multselectioncalendarview.calendarview.MultiSelectionCalendarView;
import com.roadster.sakhala.multselectioncalendarview.callback.OnDateChangeListener;


/**
 * Sample example for multiselection calendarviewg
 */

public class SampleCalendarActivity extends AppCompatActivity implements OnDateChangeListener {
    private static  final String TAG = SampleCalendarActivity.class.getSimpleName();

    MultiSelectionCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_calendar);
        calendarView = (MultiSelectionCalendarView)findViewById(R.id.calendar);
        init();
    }

    private void init(){
        calendarView.populateCalendar();
        calendarView.setOnDateChangeListener(this);
    }

    @Override
    public void onDateChange(View view, int date, int month, int year) {
        Log.d(TAG, date + " " + month + " " + year);
    }

    @Override
    public void resetCalendar() {
        calendarView.invalidateSelection();
    }
}
