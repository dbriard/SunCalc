package com.davidbriard.suncalc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class AstroUtils {

    final static DateTime Epoch = new DateTime(1858, 11, 16, 12, 0, 0);

    public static double julianDay(DateTime date) {
        return DateTimeUtils.toJulianDay(date.getMillis());//Epoch.getMillis());
       // return (date.ToUniversalTime() - Epoch).TotalDays + 2400000.0;
    }
}
