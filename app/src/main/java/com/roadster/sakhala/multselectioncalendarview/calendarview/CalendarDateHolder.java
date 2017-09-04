package com.roadster.sakhala.multselectioncalendarview.calendarview;

import android.content.Context;
import android.os.Build;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roadster.sakhala.multselectioncalendarview.R;

/**
 * Created by atulsakhala on 01/09/17.
 */

public class CalendarDateHolder {
    boolean selected;
    boolean isFromSelected;
    boolean isToSelected;
    TextView textView;
    RelativeLayout relativeLayout;
    int date, month, year;
    Context context;
    Event event;


    public CalendarDateHolder(boolean selected, TextView textView, RelativeLayout relativeLayout, int date, int month, int year, Event event, Context context) {
        this.selected = selected;
        this.isFromSelected = isFromSelected;
        this.isToSelected = isToSelected;
        this.textView = textView;
        this.relativeLayout = relativeLayout;
        this.date = date;
        this.month = month;
        this.year = year;
        this.context = context;
        this.event = event;
        setUp();
    }

    private void setUp() {
        if (event == null) {
            textView.setTextColor(context.getResources().getColor(R.color.v2_white));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                relativeLayout.setBackground(context.getResources().getDrawable(R.drawable.corner_round_rectangle_filled_green));
            }
        } else {
            textView.setTextColor(context.getResources().getColor(event.getColorId()));
        }
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void removeSelection() {
        relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.v2_white));
        textView.setTextColor(context.getResources().getColor(R.color.v2_grey));
    }

    public boolean isFromSelected() {
        return isFromSelected;
    }

    public void setFromSelected(boolean fromSelected) {
        isFromSelected = fromSelected;
    }

    public boolean isToSelected() {
        return isToSelected;
    }

    public void setToSelected(boolean toSelected) {
        isToSelected = toSelected;
    }

    public void changeFeedback() {
        relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.v2_green));
        textView.setTextColor(context.getResources().getColor(R.color.v2_white));
    }


}
