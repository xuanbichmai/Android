package com.example.fcd.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by fcd on 18/01/2018.
 */

public abstract class MesOutils {

    public static Date convertStringToDate (String uneDate) {
        String expectedPattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern,Locale.ENGLISH);
        //formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String convertDateToString (Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(uneDate);
    }
}
