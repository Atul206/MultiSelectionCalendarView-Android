# MultiSelectionCalendarView-Android
Provided multiple dates selection mode and single selection mode according to user preferences

Welcome to the MultiSelectionCalendarView-Android wiki!

A Multiple selection or single selection monthly calendar view for Android, fully written in Java. Designed to meet the minimum demands for typical calendars.

# Requirement
***

* minSdkVersion 16
* targetSdkVersion 26


# Usage
***

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
<br/>
<pre>
    allprojects {
        repositories{
               maven{ url 'https://jitpack.io' }
        }
     }
</pre>
<br/>
Step 2. Add the dependency
<pre>dependencies {
        compile 'com.github.Atul206:MultiSelectionCalendarView-Android:1.0.4'
}</pre>

Step 3. Add MultiCalendarView in you layout


```xml
<com.roadster.sakhala.multselectioncalendarview.calendarview.MultiSelectionCalendarView
            android:id="@+id/calc"
            app:isRangeMode="true"
            app:noOfRange="2"
            android:layout_below="@+id/from_to_tab"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
```


[![](https://jitpack.io/v/Atul206/MultiSelectionCalendarView-Android.svg)](https://jitpack.io/#Atul206/MultiSelectionCalendarView-Android)
