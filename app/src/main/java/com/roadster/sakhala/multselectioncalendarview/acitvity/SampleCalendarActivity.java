package com.roadster.sakhala.multselectioncalendarview.acitvity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.roadster.sakhala.multselectioncalendarview.R;
import com.roadster.sakhala.multselectioncalendarview.calendarview.Event;
import com.roadster.sakhala.multselectioncalendarview.calendarview.MultiSelectionCalendarView;
import com.roadster.sakhala.multselectioncalendarview.callback.OnDateChangeListener;

import java.util.ArrayList;


/**
 * Sample example for multiSelection calendarviewg
 */

public class SampleCalendarActivity extends AppCompatActivity implements OnDateChangeListener {
    private static final String TAG = SampleCalendarActivity.class.getSimpleName();

    MultiSelectionCalendarView calendarView;
    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_calendar);
        calendarView = (MultiSelectionCalendarView) findViewById(R.id.calendar);
        init();
    }

    private void init() {
        calendarView.populateCalendar();
        calendarView.setOnDateChangeListener(this);
        long event1 = 1505025304000l;
        long event2 = 1505198104000l;
        long event3 = 1505630104000l;
        events.add(new Event(event1, "#19b985"));
        //events.add(new Event(event2, R.color.v2_yellow));
        //events.add(new Event(event3, R.color.v2_green));
        calendarView.setEvent(events);
        findViewById(R.id.clear).setVisibility(View.GONE);
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.invalidateSelection();
            }
        });
    }

    @Override
    public void onDateChange(View view, int date, int month, int year, boolean isActive) {
        Log.d(TAG, date + " " + month + " " + year + " " + isActive);
    }


    @Override
    public void resetCalendar() {
        calendarView.invalidateSelection();
    }

}
