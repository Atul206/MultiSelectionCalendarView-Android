package com.roadster.sakhala.multselectioncalendarview.calendarview;

/**
 * Created by atulsakhala on 02/09/17.
 */

public interface CalendarView {
    void setUpRecycleView(int skipNumber, int monthOfYear, int currentYear, boolean is31stDayMonth);

    void setAction();

    void updateRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth);

    boolean isRangeModeEnable();

    void setUpRecycleViewSingleMode(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth);

    void setUpRecycleViewMultipleMode(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth);

    void updateRangeRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth);

    void updateNoRangeRecyleView(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth);
}
