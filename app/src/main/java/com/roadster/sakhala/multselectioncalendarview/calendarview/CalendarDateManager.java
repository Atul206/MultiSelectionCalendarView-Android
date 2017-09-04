package com.roadster.sakhala.multselectioncalendarview.calendarview;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roadster.sakhala.multselectioncalendarview.callback.AdapterCallback;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by atulsakhala on 01/09/17.
 */

public class CalendarDateManager {
    private static final int SINGLE_SELECETION_MODE = 1;
    private static final int MULTIPLE_SELECTION_MODE = 2;
    private String falseKey = "0_0_0";
    private HashMap<String, CalendarDateHolder> calendarDateHolders = new HashMap<>();
    private HashMap<String, Event> eventsMap = new HashMap<>();
    private int selectionMode = 2;
    private AdapterCallback adapterAction;

    private int singleDate;
    private int singleMonth;
    private int singleYear;

    private int fromDate;
    private int fromMonth;
    private int fromYear;

    private int toDate;
    private int toMonth;
    private int toYear;

    private ArrayList<Event> events = new ArrayList<>();

    public CalendarDateManager(boolean isRangeEnable, AdapterCallback adapterCallback) {
        this.adapterAction = adapterCallback;
        if (isRangeEnable) {
            selectionMode = 2;
        } else {
            selectionMode = 1;
        }
    }

    public int getFromDate() {
        return fromDate;
    }

    public void setFromDate(int fromDate) {
        this.fromDate = fromDate;
    }

    public int getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToDate() {
        return toDate;
    }

    public void setToDate(int toDate) {
        this.toDate = toDate;
    }

    public int getToMonth() {
        return toMonth;
    }

    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public HashMap<String, CalendarDateHolder> getCalendarDateHolders() {
        return calendarDateHolders;
    }

    public void setCalendarDateHolders(HashMap<String, CalendarDateHolder> calendarDateHolders) {
        this.calendarDateHolders = calendarDateHolders;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(int selectionMode) {
        this.selectionMode = selectionMode;
    }

    public int getSingleDate() {
        return singleDate;
    }

    public void setSingleDate(int singleDate) {
        this.singleDate = singleDate;
    }

    public int getSingleMonth() {
        return singleMonth;
    }

    public void setSingleMonth(int singleMonth) {
        this.singleMonth = singleMonth;
    }

    public int getSingleYear() {
        return singleYear;
    }

    public void setSingleYear(int singleYear) {
        this.singleYear = singleYear;
    }

    public void onDateSelected(Context context, boolean selected, TextView textView, RelativeLayout linearLayout
            , int date, int month, int year) {
        switch (selectionMode) {
            case SINGLE_SELECETION_MODE:
                invalidateAllOnSingleSelection(date, month, year, selected, textView, linearLayout, context);
                break;
            case MULTIPLE_SELECTION_MODE:
                invalidateAllOnMultipleSelection(date, month, year, selected, textView, linearLayout, context);
                break;

        }
    }

    private void checkItemSelection(int date, int month, int year) {

    }

    private void invalidateAllOnSingleSelection(int date, int month, int year, boolean selected, TextView textView, RelativeLayout relativeLayout, Context context) {
        String key = date + "_" + month + "_" + year;
        getCalendarDateHolders().clear();
        getCalendarDateHolders().put(key, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, context));
        setSingleDate(date);
        setSingleMonth(month);
        setSingleYear(year);
    }

    private void invalidateAllOnMultipleSelection(int date, int month, int year, boolean selected, TextView textView, RelativeLayout relativeLayout, Context context) {
        String key = date + "_" + month + "_" + year;
        if (getCalendarDateHolders().size() == 0) {
            getCalendarDateHolders().put(key, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, context));
            setFromDate(date);
            setFromMonth(month);
            setFromYear(year);

        } else if (getCalendarDateHolders().size() == 1) {
            if (getCalendarDateHolders().get(key) != null && getCalendarDateHolders().get(key).isSelected()) {
                removeMultipleSelection();
            } else {
                String fromKey = getFromDate() + "_" + getFromMonth() + "_" + getFromYear();
                if (date > getFromDate() || month > getFromMonth() || year > getFromYear()) {
                    setToDate(date);
                    setToMonth(month);
                    setToYear(year);
                    getCalendarDateHolders().put(key, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, context));
                } else {
                    removeMultipleSelection();
                    invalidateAllOnMultipleSelection(date, month, year, selected, textView, relativeLayout, context);
                }
            }
        } else {
            String fromKey = getFromDate() + "_" + getFromMonth() + "_" + getFromYear();
            if (key.equals(fromKey)) {
                removeMultipleSelection();
            } else if (getCalendarDateHolders().get(key) != null && getCalendarDateHolders().get(key).isSelected()) {
                if (date > getFromDate() || month > getFromMonth() || year > getFromYear()) {
                    setToDate(date);
                    setToMonth(month);
                    setToYear(year);
                    getCalendarDateHolders().put(key, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, context));
                } else {
                    removeMultipleSelection();
                    invalidateAllOnMultipleSelection(date, month, year, selected, textView, relativeLayout, context);
                }
            } else if (getCalendarDateHolders().get(key) == null) {
                removeMultipleSelection();
                invalidateAllOnMultipleSelection(date, month, year, selected, textView, relativeLayout, context);
            }
        }
    }

    private void removePrevious(String toKey) {
        getCalendarDateHolders().remove(toKey);
    }

    private void removeMultipleSelection() {
        setFromDate(0);
        setFromMonth(0);
        setFromYear(0);
        setToDate(0);
        setToMonth(0);
        setToYear(0);

        Iterator it = getCalendarDateHolders().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            getCalendarDateHolders().get(pair.getKey()).removeSelection();
            it.remove(); // avoids a ConcurrentModificationException
        }
        getCalendarDateHolders().clear();
        adapterAction.clearAllData();
    }

    public boolean isEventPresent(int date, int month, int year) {
        String key = date + "_" + month + "_" + year;
        if (eventsMap != null)
            return eventsMap.containsKey(key);
        else
            return false;
    }

    public boolean isCalendarLieBetweenRange(int date, int month, int year) {
        String key = date + "_" + month + "_" + year;
        String fromKey = getFromDate() + "_" + getFromMonth() + "_" + getFromYear();
        String toKey = getToDate() + "_" + getToMonth() + "_" + getToYear();

        if ((date >= getFromDate() && date <= getToDate() && month >= getFromMonth() && month <= getToMonth() && year >= getFromYear() && year <= getToYear())) {
            return true;
        }

        if (getCalendarDateHolders().get(key) == null) {
            return false;
        }

        if (fromKey.equals(falseKey)) {
            return false;
        } else if (fromKey.equals(key)) {
            return true;
        }

        if (toKey.equals(falseKey)) {
            return false;
        } else if (toKey.equals(key)) {
            return true;
        }

        return false;
    }

    public void putEvent(Context activity, boolean selected, TextView textView, RelativeLayout relativeLayout, int date, int month, int year) {
        String key = date + "_" + month + "_" + year;

        Event event = getEventsMap().get(key);
        textView.setTextColor(activity.getResources().getColor(event.getColorId()));
    }

    public void rangeDataSelection(Context activity, boolean selected, TextView textView, RelativeLayout relativeLayout, int date, int month, int year) {
        String key = date + "_" + month + "_" + year;
        String fromKey = getFromDate() + "_" + getFromMonth() + "_" + getFromYear();
        String toKey = getToDate() + "_" + getToMonth() + "_" + getToYear();

        if ((date >= getFromDate() && date <= getToDate() && month >= getFromMonth() && month <= getToMonth() && year >= getFromYear() && year <= getToYear())) {
            getCalendarDateHolders().put(key, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, activity));
        }

        if (key.equals(fromKey)) {
            getCalendarDateHolders().put(fromKey, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, activity));
        }

        if (key.equals(toKey)) {
            getCalendarDateHolders().put(toKey, new CalendarDateHolder(selected, textView, relativeLayout, date, month, year, null, activity));
        }
    }

    public boolean isDatePresent(int date, int month, int year) {
        String key = date + "_" + month + "_" + year;
        String singleDate = getSingleDate() + "_" + getSingleMonth() + "_" + getSingleYear();
        if (key.equals(singleDate)) {
            return true;
        }
        return false;
    }

    public void lastSingleDateSelected(Context activity, boolean selected, TextView dateText, RelativeLayout dateLayout, int date, int month, int year) {
        String key = date + "_" + month + "_" + year;
        getCalendarDateHolders().put(key, new CalendarDateHolder(selected, dateText, dateLayout, date, month, year, null, activity));
    }

    public void clearCalendarData() {
        setFromDate(0);
        setFromMonth(0);
        setFromYear(0);
        setToDate(0);
        setToMonth(0);
        setToYear(0);
        setSingleDate(0);
        setSingleMonth(0);
        setSingleYear(0);
        getCalendarDateHolders().clear();
    }

    public void setFixedDate(Context activity, boolean b, TextView dateText, RelativeLayout dateLayout, int date, int month, int year) {

    }

    public ArrayList<Event> getEvents() {
        if (events == null) {
            return new ArrayList<Event>();
        }
        return this.events;
    }

    private HashMap<String, Event> getEventsMap(){
        return eventsMap;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
        for (Event event : events) {
            DateTime d = new DateTime(new Date(event.getDate()));
            String ky = d.getDayOfMonth() + "_" + d.getMonthOfYear() + "_" + d.getYear();
            eventsMap.put(ky, event);
        }
    }
}
