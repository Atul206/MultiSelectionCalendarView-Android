package com.roadster.sakhala.multselectioncalendarview.calendarview;

/**
 * Created by atulsakhala on 04/09/17.
 */

public class Event {
    public long date;
    public int colorId;

    public Event(long date, int colorId) {
        this.date = date;
        this.colorId = colorId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
