package com.roadster.sakhala.multselectioncalendarview.callback;

import android.view.View;

/**
 * Created by atulsakhala on 30/08/17.
 */

public interface OnDateChangeListener {
    void onDateChange(View view, int date, int month, int year, boolean isActive);

    void resetCalendar();
}
