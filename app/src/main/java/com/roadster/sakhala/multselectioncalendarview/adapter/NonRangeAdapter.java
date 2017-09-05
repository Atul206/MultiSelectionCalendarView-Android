package com.roadster.sakhala.multselectioncalendarview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roadster.sakhala.multselectioncalendarview.R;
import com.roadster.sakhala.multselectioncalendarview.calendarview.CalendarDateManager;
import com.roadster.sakhala.multselectioncalendarview.callback.CalendarCallback;

import org.joda.time.DateTime;

/**
 * Created by atulsakhala on 29/08/17.
 */

public class NonRangeAdapter extends RecyclerView.Adapter<NonRangeAdapter.DateLayoutViewHolder> {

    private Context activity;
    private CalendarCallback calendarCallback;
    private int count = 1;
    private TextView lastSelectedDate;
    private RelativeLayout lastSelectedLayout;
    private int howManyDaysSkipThisMonth;
    private int month = 0;
    private int year = 0;
    private boolean is31stDayMonth = false;
    private CalendarDateManager calendarDateManager;

    public NonRangeAdapter(CalendarCallback callback, Context activity, int skipDays, int mnth, int year, boolean is31stDayMonth, CalendarDateManager calendarDateManager) {
        this.activity = activity;
        this.calendarCallback = callback;
        this.howManyDaysSkipThisMonth = skipDays;
        this.month = mnth;
        this.year = year;
        this.is31stDayMonth = is31stDayMonth;
        this.calendarDateManager = calendarDateManager;
        count = 1;
    }

    @Override
    public NonRangeAdapter.DateLayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.calendar_items, parent, false);
        return new NonRangeAdapter.DateLayoutViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NonRangeAdapter.DateLayoutViewHolder holder, final int position) {

        if (position > (howManyDaysSkipThisMonth - 1)) {
            if (howManyDaysSkipThisMonth == 1 && position == 0) {
                count = 1;
                return;
            }
            if (month == 2 && count > 28) {
                holder.dateText.setText("");
                return;
            }
            if (is31stDayMonth && count <= 31) {
                holder.dateText.setText("" + count);
                if (DateTime.now().getYear() < year || DateTime.now().getMonthOfYear() < month || DateTime.now().getDayOfMonth() <= count) {
                    holder.dateText.setEnabled(true);
                    holder.dateText.setAlpha(1.0f);
                    holder.dateLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int date = Integer.valueOf(holder.dateText.getText().toString());
                            calendarDateManager.onDateSelected(activity, true, holder.dateText, holder.dateLayout, date, month, year);
                            calendarCallback.setDateMonthYear(date, month, year);
                            count = 1;
                            notifyDataSetChanged();
                        }
                    });
                    checkAndInvalidateSelection(holder, count);
                }
            } else if (count <= 30) {
                holder.dateText.setText("" + count);
                if (DateTime.now().getYear() < year || DateTime.now().getMonthOfYear() < month || DateTime.now().getDayOfMonth() <= count) {
                    holder.dateText.setEnabled(true);
                    holder.dateText.setAlpha(1.0f);
                    holder.dateLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int date = Integer.valueOf(holder.dateText.getText().toString());
                            calendarDateManager.onDateSelected(activity, true, holder.dateText, holder.dateLayout, date, month, year);
                            calendarCallback.setDateMonthYear(date, month, year);
                            count = 1;
                            notifyDataSetChanged();
                        }
                    });
                    checkAndInvalidateSelection(holder, count);
                }
            }
            count++;
        } else {
            holder.dateText.setText("");
            count = 1;
        }
    }


    private void checkAndInvalidateSelection(NonRangeAdapter.DateLayoutViewHolder holder, int date) {
        if (calendarDateManager.isDatePresent(date, month, year)) {
            calendarDateManager.lastSingleDateSelected(activity, true, holder.dateText, holder.dateLayout, date, month, year);
            calendarCallback.setSelected(true);
        }
    }


    public void restoreLastDateProperty(RelativeLayout dateLayout, TextView dateText) {
        if (getLastSelectedDate() != null && getLastSelectedLayout() != null) {
            getLastSelectedLayout().setBackgroundColor(activity.getResources().getColor(R.color.v2_white));
            getLastSelectedDate().setTextColor(activity.getResources().getColor(R.color.v2_grey));
            setLastSelectedLayout(null);
            setLastSelectedLayout(null);
        } else {
            setLastSelectedDate(dateText);
            setLastSelectedLayout(dateLayout);
        }

    }

    public TextView getLastSelectedDate() {
        return lastSelectedDate;
    }

    public void setLastSelectedDate(TextView lastSelectedDate) {
        this.lastSelectedDate = lastSelectedDate;
    }

    public RelativeLayout getLastSelectedLayout() {
        return lastSelectedLayout;
    }

    public void setLastSelectedLayout(RelativeLayout lastSelectedLayout) {
        this.lastSelectedLayout = lastSelectedLayout;
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    public void updaDate(int skipDays, int currentMonth, int currentYear, boolean is31stDayMonth) {
        this.howManyDaysSkipThisMonth = skipDays;
        this.month = currentMonth;
        this.year = currentYear;
        this.is31stDayMonth = is31stDayMonth;
        this.count = 1;
        calendarCallback.setSelected(false);
        notifyDataSetChanged();
    }

    public void invalidateSelection() {
        calendarDateManager.clearCalendarData();
        notifyDataSetChanged();
    }

    public class DateLayoutViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout dateLayout;
        TextView dateText;

        public DateLayoutViewHolder(View itemView) {
            super(itemView);
            dateText = (TextView) itemView.findViewById(R.id.day);
            dateLayout = (RelativeLayout) itemView.findViewById(R.id.calendar_dates_layout);
        }
    }
}

