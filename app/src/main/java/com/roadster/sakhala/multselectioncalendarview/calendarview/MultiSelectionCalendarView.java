package com.roadster.sakhala.multselectioncalendarview.calendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roadster.sakhala.multselectioncalendarview.R;
import com.roadster.sakhala.multselectioncalendarview.adapter.NonRangeAdapter;
import com.roadster.sakhala.multselectioncalendarview.adapter.RangeAdapter;
import com.roadster.sakhala.multselectioncalendarview.callback.AdapterCallback;
import com.roadster.sakhala.multselectioncalendarview.callback.CalendarCallback;
import com.roadster.sakhala.multselectioncalendarview.callback.OnDateChangeListener;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;

/**
 * Created by atulsakhala on 02/09/17.
 *
 */

public class MultiSelectionCalendarView extends RelativeLayout implements CalendarView, CalendarCallback, AdapterCallback {

    private static final String TAG = MultiSelectionCalendarView.class.getSimpleName();
    protected ImageView leftNav;
    protected ImageView rightNav;
    protected TextView selectedDateTitle;
    protected RecyclerView recyclerView;
    private NonRangeAdapter nonRangeAdapter;
    private RangeAdapter rangeAdapter;
    private CalendarPresenter presenter;
    private Context context;
    private OnDateChangeListener listener;
    private View view;
    private boolean isRangeMode;
    private int enableMonthFromCurrentDate;
    private CalendarDateManager calendarDateManager;
    private int rangeUpto = 0;

    public MultiSelectionCalendarView(Context context) {
        super(context);
        init(context);
    }

    public MultiSelectionCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttributes(context, attrs);
    }

    public MultiSelectionCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttributes(context, attrs);
    }

    private void init(Context context) {
        this.context = context;
        view = inflate(getContext(), R.layout.calender, this);
        selectedDateTitle = (TextView) view.findViewById(R.id._selected_date);
        leftNav = (ImageView) view.findViewById(R.id.left_nav);
        rightNav = (ImageView) view.findViewById(R.id.right_nav);
        recyclerView = (RecyclerView) view.findViewById(R.id.dates);
        calendarDateManager = new CalendarDateManager(true, this);
        presenter = new CalendarPresenter(this);
        JodaTimeAndroid.init(context);
    }


    public void populateCalendar() {
        presenter.setUpRecycleView();
        selectedDateTitle.setText(presenter.getMonthInWord() + " " + presenter.getCurrentYear());
    }


    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomCalender,
                0, 0);
        try {
            isRangeMode = a.getBoolean(R.styleable.CustomCalender_isRangeMode, true);
            enableMonthFromCurrentDate = a.getInt(R.styleable.CustomCalender_noOfRange, 2);
        } finally {
            a.recycle();
        }
    }

    @Override
    public void setUpRecycleView(int dateStartFromWeekDay, int currentFocusMonth, int currentFocusyear, boolean is31stDayMonth) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        recyclerView.addItemDecoration(new SpacesItemDecoration());
        nonRangeAdapter = new NonRangeAdapter(this, context, dateStartFromWeekDay, currentFocusMonth, currentFocusyear, is31stDayMonth, calendarDateManager);
        recyclerView.setAdapter(nonRangeAdapter);
    }

    @Override
    public void updateRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        nonRangeAdapter.updaDate(skipDays, currentMonth, currentYear, is31stDayMonth);
    }

    @Override
    public void updateNoRangeRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        nonRangeAdapter.updaDate(skipDays, currentMonth, currentYear, is31stDayMonth);
    }

    @Override
    public void updateRangeRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        rangeAdapter.updaDate(skipDays, currentMonth, currentYear, is31stDayMonth);
    }

    @Override
    public void setDateMonthYear(int date, int month, int year) {
        listener.onDateChange(view, date, month, year);
    }

    @Override
    public void setAction() {
        leftNav.setVisibility(INVISIBLE);
        leftNav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                rightNav.setEnabled(true);
                rightNav.setVisibility(View.VISIBLE);
                presenter.updateCalenderView(false);
                selectedDateTitle.setText(presenter.getMonthInWord() + " " + presenter.getCurrentYear());
                rangeUpto--;
                if (rangeUpto == 0) {
                    leftNav.setEnabled(false);
                    leftNav.setVisibility(View.INVISIBLE);
                    return;
                }
            }
        });


        rightNav.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                leftNav.setEnabled(true);
                leftNav.setVisibility(View.VISIBLE);
                presenter.updateCalenderView(true);
                selectedDateTitle.setText(presenter.getMonthInWord() + " " + presenter.getCurrentYear());
                rangeUpto++;
                if (enableMonthFromCurrentDate == rangeUpto) {
                    rightNav.setEnabled(false);
                    rightNav.setVisibility(View.INVISIBLE);
                    return;
                }
            }
        });
    }

    public void setOnDateChangeListener(OnDateChangeListener l) {
        this.listener = l;
    }

    public void enableRangeMode() {
        this.isRangeMode = true;
    }

    public void disableRangeMode() {
        this.isRangeMode = false;
    }

    @Override
    public boolean isRangeModeEnable() {
        return isRangeMode;
    }

    @Override
    public void setUpRecycleViewMultipleMode(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        Log.d(TAG, "Multiple Selection");
        calendarDateManager.setSelectionMode(2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        recyclerView.addItemDecoration(new SpacesItemDecoration());
        rangeAdapter = new RangeAdapter(this, context, skipDays, currentMonth, currentYear, is31stDayMonth, calendarDateManager);
        recyclerView.setAdapter(rangeAdapter);
    }

    @Override
    public void setUpRecycleViewSingleMode(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        Log.d(TAG, "Single Selection");
        calendarDateManager.setSelectionMode(1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        recyclerView.addItemDecoration(new SpacesItemDecoration());
        nonRangeAdapter = new NonRangeAdapter(this, context, skipDays, currentMonth, currentYear, is31stDayMonth, calendarDateManager);
        recyclerView.setAdapter(nonRangeAdapter);
    }

    public void invalidateSelection() {
        if (isRangeModeEnable()) {
            rangeAdapter.invalidateSelection();
        } else {
            nonRangeAdapter.invalidateSelection();
        }
    }

    @Override
    public void clearAllData() {
        listener.resetCalendar();
    }

    public void setEvent(ArrayList<Event> events) {
        calendarDateManager.setEvents(events);
    }

    public ArrayList<Event> setEvents(ArrayList<Event> events){
        return events;
    }
}
