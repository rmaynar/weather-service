package com.globant.weatherservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public final static String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static Date convertStrToDate(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }
}
