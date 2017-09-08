# MultiSelectionCalendarView-Android
Provided multiple dates selection mode and single selection mode according to user preferences

# Welcome to the MultiSelectionCalendarView-Android wiki!
***

A Multiple selection or single selection monthly calendar view for Android, fully written in Java. Designed to meet the minimum demands for typical calendars.

![](https://user-images.githubusercontent.com/3988942/29988362-7f21dc70-8f8a-11e7-91bf-85245885c525.png)

![](https://user-images.githubusercontent.com/3988942/29988381-a9e79c6a-8f8a-11e7-88fb-d2684f35273a.png)

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
        compile 'com.github.Atul206:MultiSelectionCalendarView-Android:1.1.2'
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
