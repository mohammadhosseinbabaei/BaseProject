package com.example.sinamn75.base.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {
    public String dateParser(String date_parser) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        @SuppressLint("SimpleDateFormat") DateFormat format_time = new SimpleDateFormat("hh:mm:ss");
        String resultDate = "";
        try {
            Date date_old = format_date.parse(date_parser.substring(0, 19));
            Date date_now = format_date.parse(format_date.format(Calendar.getInstance().getTime()));
            String getTime = date_parser.substring(11, 19);
            Date time_old = format_time.parse(getTime);
            Date time_new = Calendar.getInstance().getTime();
            long diffDay = Math.abs((date_now.getTime() - date_old.getTime()) / 86400000);
            if (diffDay == 0) {
                long different = time_new.getTime() - time_old.getTime();
                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;
                different = different % daysInMilli;
                long elapsedHours = different / hoursInMilli;
                different = different % hoursInMilli;
                long elapsedMinutes = different / minutesInMilli;
                if (elapsedMinutes == 0 && elapsedHours == 0) resultDate = " Right now ";
                else if (elapsedHours == 0) resultDate = elapsedMinutes + " Minutes ago ";
                else resultDate = getTime.substring(0, 5);
            } else if (diffDay == 1) resultDate = " Yesterday ";
            else resultDate = diffDay + " Days ago ";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }
}