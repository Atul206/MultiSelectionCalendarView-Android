package com.roadster.sakhala.multselectioncalendarview.calendarview;

/**
 * Created by atulsakhala on 04/09/17.
 */

public class Event {
    public long date;
    public String colorId;

    public Event(long date, String colorId) {
        this.date = date;
        this.colorId = colorId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }
}
