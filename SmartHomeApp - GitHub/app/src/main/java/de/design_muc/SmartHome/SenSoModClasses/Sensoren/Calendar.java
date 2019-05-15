package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar extends VirtualSensor {

    private static final Map<String, List<String>> appointments = new HashMap<>();

    public static ArrayList<String> nameOfEvent = new ArrayList<>();
    public static ArrayList<String> startDates = new ArrayList<>();
    public static ArrayList<String> endDates = new ArrayList<>();
    public static ArrayList<String> descriptions = new ArrayList<>();

    // Projection array. Creating indices for this array instead of doing
// dynamic lookups improves performance.
    private static final String[] EVENT_PROJECTION = new String[] {
//            CalendarContract.Calendars._ID,                           // 0
//            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
//            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
//            CalendarContract.Calendars.OWNER_ACCOUNT,                  // 3
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND
    };

    // The indices for the projection array above.
//    private static final int PROJECTION_ID_INDEX = 0;
//    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
//    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
//    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    private static final int PROJECTION_EVENT_ID_INDEX = 0;
    private static final int PROJECTION_EVENT_TITLE_INDEX = 1;
    private static final int PROJECTION_EVENT_DTSTART_INDEX = 2;
    private static final int PROJECTION_EVENT_DTEND_INDEX = 3;

    public Calendar() {
        multiple = false;
        this.name = "Calendar";
    }

    public static Map<String, List<String>> readCalendarEvent(Context context) {

        Cursor cur = null;
        ContentResolver cr = context.getContentResolver();
        long todayMidnight = getTodayMidnight();
        Uri uri = CalendarContract.Events.CONTENT_URI;
        String selection = "((" + CalendarContract.Events.OWNER_ACCOUNT + " = ?) AND (" + CalendarContract.Events.DTSTART +  " > ? ))";
        String[] selectionArgs = new String[] {"d3jay.boy@googlemail.com", String.valueOf(todayMidnight)};
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, CalendarContract.Events.DTSTART);

        // Use the cursor to step through the returned records
        int count = 0;
        while (cur.moveToNext()) {
            // Get the field values
            String id = cur.getString(PROJECTION_EVENT_ID_INDEX);
            String title = cur.getString(PROJECTION_EVENT_TITLE_INDEX);
            // Do something with the values...
            List<String> tempAppointmentList = new ArrayList<>();
            tempAppointmentList.add(title);
            tempAppointmentList.add(getDate(cur.getLong(PROJECTION_EVENT_DTSTART_INDEX)));
            tempAppointmentList.add(getDate(cur.getLong(PROJECTION_EVENT_DTEND_INDEX)));
            appointments.put(String.valueOf(count), tempAppointmentList);
            count++;
        }
        return appointments;
    }

    private static long getTodayMidnight() {
        java.util.Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis to midnight
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    private static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public Map<String, List<String>> getAppointments() {
        return appointments;
    }

}
// Use IDE to generate toString and equals methods

