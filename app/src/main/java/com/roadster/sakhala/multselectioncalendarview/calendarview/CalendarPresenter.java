package com.roadster.sakhala.multselectioncalendarview.calendarview;

import com.roadster.sakhala.multselectioncalendarview.util.Constant;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;

/**
 * Created by atulsakhala on 02/09/17.
 * <p>
 * Created a logic of calendar.
 */

public class CalendarPresenter implements Constant {
    CalendarView view;
    int currentDate, currentMonth, currentYear, currentWeek;
    int firstDayWeek, lastDayWeek;
    boolean is31stDayMonth = false;
    boolean isFeb = false;
    private int skipDays;

    public CalendarPresenter(CalendarView cal) {
        this.view = cal;
    }

    public void setUpRecycleView() {
        view.setAction();
        setDateMonthYear();
        setUpView();
    }

    public void setUpView() {
        setSkipDays(getFirstDayWeek());
        if (view.isRangeModeEnable()) {
            view.setUpRecycleViewMultipleMode(getSkipDays(), getCurrentMonth(), getCurrentYear(), isIs31stDayMonth());
        } else {
            view.setUpRecycleViewSingleMode(getSkipDays(), getCurrentMonth(), getCurrentYear(), isIs31stDayMonth());
        }
    }

    public void setDateMonthYear() {
        this.currentMonth = DateTime.now().getMonthOfYear();
        this.currentYear = DateTime.now().getYear();
        this.currentDate = DateTime.now().getDayOfMonth();
        this.currentWeek = DateTime.now().getDayOfWeek() + 1;
        setFirstDayWeek();
        setLastDayWeek();
    }

    public int getFirstDayWeek() {
        return firstDayWeek;
    }

    private void setFirstDayWeek(int firstDayWeek) {
        setCurrentDate(1);
        setCurrentWeek(firstDayWeek);
        this.firstDayWeek = firstDayWeek;
        setLastDayWeek();
    }

    public int getLastDayWeek() {
        return lastDayWeek;
    }

    private void setLastDayWeek(int lastDayWeek) {
        if (isIs31stDayMonth()) {
            setCurrentDate(31);
        } else if (isFeb) {
            setCurrentDate(28);
        } else {
            setCurrentDate(30);
        }
        setCurrentWeek(lastDayWeek);
        this.lastDayWeek = lastDayWeek;
        setFirstDayWeek();
    }

    public void setFirstDayWeek() {
        if (getCurrentDate() % NUMBER_OF_IN_DAYS_WEEK == 0) {
            if ((getCurrentWeek() + 1) > NUMBER_OF_IN_DAYS_WEEK) {
                this.firstDayWeek = 1;
            } else {
                this.firstDayWeek = (this.lastDayWeek == 0 ? getCurrentWeek() : this.lastDayWeek) + 1;
            }
        } else if (getCurrentDate() % NUMBER_OF_IN_DAYS_WEEK != 0) {
            this.firstDayWeek = (this.lastDayWeek == 0 ? getCurrentWeek() : this.lastDayWeek);
            if (isIs31stDayMonth()) {
                this.firstDayWeek = this.firstDayWeek + (8 - getCurrentDate() % 7);
                if (this.firstDayWeek > NUMBER_OF_IN_DAYS_WEEK) {
                    this.firstDayWeek = this.firstDayWeek - NUMBER_OF_IN_DAYS_WEEK;
                }
            } else {
                this.firstDayWeek = this.firstDayWeek + (8 - getCurrentDate() % 7);
                if (this.firstDayWeek > NUMBER_OF_IN_DAYS_WEEK) {
                    this.firstDayWeek = this.firstDayWeek - NUMBER_OF_IN_DAYS_WEEK;
                }
            }
        }
        if (this.firstDayWeek > NUMBER_OF_IN_DAYS_WEEK) {
            this.firstDayWeek = 7;
        }
    }

    public void setLastDayWeek() {
        if (isIs31stDayMonth()) {
            this.lastDayWeek = firstDayWeek + 1 + 1;
            if (this.lastDayWeek > NUMBER_OF_IN_DAYS_WEEK) {
                this.lastDayWeek = 1;
            }
        } else if (isFeb) {
            this.lastDayWeek = firstDayWeek - 1;
            if (this.lastDayWeek < 1) {
                this.lastDayWeek = NUMBER_OF_IN_DAYS_WEEK;
            }
        } else {
            this.lastDayWeek = firstDayWeek + 1;
            if (this.lastDayWeek > NUMBER_OF_IN_DAYS_WEEK) {
                this.lastDayWeek = 1;
            }
        }
    }

    public void updateFirstLastDay(boolean isMovementRight) {
        if (isMovementRight) {
            if (this.lastDayWeek + 1 > NUMBER_OF_IN_DAYS_WEEK) {
                this.firstDayWeek = 1;
            } else {
                this.firstDayWeek = this.lastDayWeek + 1;
            }
            setFirstDayWeek(firstDayWeek);
        } else {
            if (this.firstDayWeek - 1 < 1) {
                this.lastDayWeek = 7;
            } else {
                this.lastDayWeek = this.firstDayWeek - 1;
            }
            setLastDayWeek(lastDayWeek);
        }

    }

    public int getSkipDays() {
        return skipDays;
    }

    public void setSkipDays(int skipDays) {
        this.skipDays = skipDays - 1;
        if (skipDays < 1) {
            this.skipDays = 0;
        }
    }

    public boolean isIs31stDayMonth() {
        isFeb = false;
        switch (getCurrentMonth()) {
            case 1:
                return true;
            case 2:
                isFeb = true;
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return false;
            case 7:
                return true;
            case 8:
                return true;
            case 9:
                return false;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
        }
        return false;
    }

    public void updateCalenderView(boolean isMovementRight) {
        int updateMonth = 0;
        if (isMovementRight) {
            updateMonth = getCurrentMonth() + 1;
            if (updateMonth <= 12)
                setCurrentMonth(updateMonth);
            else {
                setCurrentMonth(1);
                setCurrentYear(getCurrentYear() + 1);
            }
        } else {
            updateMonth = getCurrentMonth() - 1;
            if (updateMonth >= 1)
                setCurrentMonth(updateMonth);
            else {
                setCurrentMonth(12);
                setCurrentYear(getCurrentYear() - 1);
            }
        }
        updateFirstLastDay(isMovementRight);
        setSkipDays(getFirstDayWeek());
        if (view.isRangeModeEnable()) {
            view.updateRangeRecyleView(getSkipDays(), getCurrentMonth(), getCurrentYear(), isIs31stDayMonth());
        } else {
            view.updateNoRangeRecyleView(getSkipDays(), getCurrentMonth(), getCurrentYear(), isIs31stDayMonth());
        }
    }

    public String getMonthInWord() {
        return new DateFormatSymbols().getMonths()[getCurrentMonth() - 1];
    }

    public int getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(int currentDate) {
        this.currentDate = currentDate;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }
}

