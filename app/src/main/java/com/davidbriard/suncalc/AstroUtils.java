package com.davidbriard.suncalc;

import android.support.annotation.FloatRange;
import android.support.annotation.Size;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class AstroUtils {

    final static DateTime Epoch = new DateTime(1858, 11, 16, 12, 0, 0);

    public static double julianDay(DateTime date) {
        return DateTimeUtils.toJulianDay(date.getMillis());//Epoch.getMillis());
       // return (date.ToUniversalTime() - Epoch).TotalDays + 2400000.0;
    }


    public static double julianDay(double jc)
    {
        return jc * 36525.0 + 2451545.0;
    }

    public static boolean isLeapYear(int year)
    {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    /// Returns the number of Julian centuries since Jan 1, 2000, 12 UT
    public static double julianCenturies(double jd) {
        return (jd - 2451545.0) / 36525.0;
    }

    public static double julianCenturies(DateTime date) {
        return julianCenturies(julianDay(date));
    }

    public static double sunEclipticLatitude(double julianCycle)
    {
        // the ecliptic latitude of the Sun is nearly
        // as it never exceeds 0.00033°
        return 0;
    }
    public static double sunEclipticLongitude(double jc)
    {
        double s = 280.4659 + 36000.7695 * jc;
        s += 1.9147 * Math.cos((35999.05 * jc + 267.52) * 0.017453292519943);
        s -= 0.0048 * jc * Math.cos((35999.05 * jc + 267.52) * 0.017453292519943);
        s += 0.02 * Math.cos((71998.1 * jc + 265.1) * 0.017453292519943);
        s += 0.002 * Math.cos((32964 * jc + 158) * 0.017453292519943);
        s += 0.0018 * Math.cos((19 * jc + 159) * 0.017453292519943);
        s += 0.0018 * Math.cos((445267 * jc + 208) * 0.017453292519943);
        s += 0.0015 * Math.cos((45038 * jc + 254) * 0.017453292519943);
        s += 0.0013 * Math.cos((22519 * jc + 352) * 0.017453292519943);
        s += 0.0007 * Math.cos((65929 * jc + 45) * 0.017453292519943);
        s += 0.0007 * Math.cos((3035 * jc + 110) * 0.017453292519943);
        s += 0.0007 * Math.cos((9038 * jc + 64) * 0.017453292519943);
        s += 0.0006 * Math.cos((33718 * jc + 316) * 0.017453292519943);
        s += 0.0005 * Math.cos((155 * jc + 118) * 0.017453292519943);
        s += 0.0005 * Math.cos((2281 * jc + 221) * 0.017453292519943);
        s += 0.0004 * Math.cos((29930 * jc + 48) * 0.017453292519943);
        s += 0.0004 * Math.cos((31557 * jc + 161) * 0.017453292519943);
        return s % 360.0;
    }

    public static double moonEclipticLatitude(double jc)
    {
        double tb = 5.1281 * Math.cos((483202.019 * jc + 3.273) * 0.017453292519943);
        tb += 0.2806 * Math.cos((960400.89 * jc + 138.24) * 0.017453292519943);
        tb += 0.2777 * Math.cos((6003.15 * jc + 48.31) * 0.017453292519943);
        tb += 0.1733 * Math.cos((407332.2 * jc + 52.43) * 0.017453292519943);
        tb += 0.0554 * Math.cos((896537.4 * jc + 104) * 0.017453292519943);
        tb += 0.0463 * Math.cos((69866.7 * jc + 82.5) * 0.017453292519943);
        tb += 0.0326 * Math.cos((1373736.2 * jc + 239) * 0.017453292519943);
        tb += 0.0172 * Math.cos((1437599.8 * jc + 273.2) * 0.017453292519943);
        tb += 0.0093 * Math.cos((884531 * jc + 187) * 0.017453292519943);
        tb += 0.0088 * Math.cos((471196 * jc + 87) * 0.017453292519943);
        tb += 0.0082 * Math.cos((371333 * jc + 55) * 0.017453292519943);
        tb += 0.0043 * Math.cos((547066 * jc + 217) * 0.017453292519943);
        tb += 0.0042 * Math.cos((1850935 * jc + 14) * 0.017453292519943);
        tb += 0.0034 * Math.cos((443331 * jc + 230) * 0.017453292519943);
        tb += 0.0025 * Math.cos((860538 * jc + 106) * 0.017453292519943);
        tb += 0.0022 * Math.cos((481268 * jc + 308) * 0.017453292519943);
        tb += 0.0022 * Math.cos((1337737 * jc + 241) * 0.017453292519943);
        tb += 0.0021 * Math.cos((105866 * jc + 80) * 0.017453292519943);
        tb += 0.0019 * Math.cos((924402 * jc + 141) * 0.017453292519943);
        tb += 0.0018 * Math.cos((820668 * jc + 153) * 0.017453292519943);
        tb += 0.0018 * Math.cos((519201 * jc + 181) * 0.017453292519943);
        tb += 0.0018 * Math.cos((1449606 * jc + 10) * 0.017453292519943);
        tb += 0.0015 * Math.cos((42002 * jc + 46) * 0.017453292519943);
        tb += 0.0015 * Math.cos((928469 * jc + 121) * 0.017453292519943);
        tb += 0.0015 * Math.cos((996400 * jc + 316) * 0.017453292519943);
        tb += 0.0014 * Math.cos((29996 * jc + 129) * 0.017453292519943);
        tb += 0.0013 * Math.cos((447203 * jc + 6) * 0.017453292519943);
        tb += 0.0013 * Math.cos((37935 * jc + 65) * 0.017453292519943);
        tb += 0.0011 * Math.cos((1914799 * jc + 48) * 0.017453292519943);
        tb += 0.0010 * Math.cos((1297866 * jc + 288) * 0.017453292519943);
        tb += 0.0009 * Math.cos((1787072 * jc + 340) * 0.017453292519943);
        tb += 0.0008 * Math.cos((972407 * jc + 235) * 0.017453292519943);
        tb += 0.0007 * Math.cos((1309873 * jc + 205) * 0.017453292519943);
        tb += 0.0006 * Math.cos((559072 * jc + 134) * 0.017453292519943);
        tb += 0.0006 * Math.cos((1361730 * jc + 322) * 0.017453292519943);
        tb += 0.0005 * Math.cos((848532 * jc + 190) * 0.017453292519943);
        tb += 0.0005 * Math.cos((419339 * jc + 149) * 0.017453292519943);
        tb += 0.0005 * Math.cos((948395 * jc + 222) * 0.017453292519943);
        tb += 0.0004 * Math.cos((2328134 * jc + 149) * 0.017453292519943);
        tb += 0.0004 * Math.cos((1024264 * jc + 352) * 0.017453292519943);
        tb += 0.0003 * Math.cos((932536 * jc + 282) * 0.017453292519943);
        tb += 0.0003 * Math.cos((1409735 * jc + 57) * 0.017453292519943);
        tb += 0.0003 * Math.cos((2264270 * jc + 115) * 0.017453292519943);
        tb += 0.0003 * Math.cos((1814936 * jc + 16) * 0.017453292519943);
        tb += 0.0003 * Math.cos((335334 * jc + 57) * 0.017453292519943);
        return tb;
    }

    public static double moonEclipticLongitude(double jc)
    {
        double tl = 218.316 + 481267.8809 * jc;
        tl += 6.2888 * Math.cos((477198.868 * jc + 44.963) * 0.017453292519943);
        tl += 1.274 * Math.cos((413335.35 * jc + 10.74) * 0.017453292519943);
        tl += 0.6583 * Math.cos((890534.22 * jc + 145.7) * 0.017453292519943);
        tl += 0.2136 * Math.cos((954397.74 * jc + 179.93) * 0.017453292519943);
        tl += 0.1851 * Math.cos((35999.05 * jc + 87.53) * 0.017453292519943);
        tl += 0.1144 * Math.cos((966404 * jc + 276.5) * 0.017453292519943);
        tl += 0.0588 * Math.cos((63863.5 * jc + 124.2) * 0.017453292519943);
        tl += 0.0571 * Math.cos((377336.3 * jc + 13.2) * 0.017453292519943);
        tl += 0.0533 * Math.cos((1367733.1 * jc + 280.7) * 0.017453292519943);
        tl += 0.0458 * Math.cos((854535.2 * jc + 148.2) * 0.017453292519943);
        tl += 0.0409 * Math.cos((441199.8 * jc + 47.4) * 0.017453292519943);
        tl += 0.0347 * Math.cos((445267.1 * jc + 27.9) * 0.017453292519943);
        tl += 0.0304 * Math.cos((513197.9 * jc + 222.5) * 0.017453292519943);
        tl += 0.0154 * Math.cos((75870 * jc + 41) * 0.017453292519943);
        tl += 0.0125 * Math.cos((1443603 * jc + 52) * 0.017453292519943);
        tl += 0.011 * Math.cos((489205 * jc + 142) * 0.017453292519943);
        tl += 0.0107 * Math.cos((1303870 * jc + 246) * 0.017453292519943);
        tl += 0.01 * Math.cos((1431597 * jc + 315) * 0.017453292519943);
        tl += 0.0085 * Math.cos((826671 * jc + 111) * 0.017453292519943);
        tl += 0.0079 * Math.cos((449334 * jc + 188) * 0.017453292519943);
        tl += 0.0068 * Math.cos((926533 * jc + 323) * 0.017453292519943);
        tl += 0.0052 * Math.cos((31932 * jc + 107) * 0.017453292519943);
        tl += 0.005 * Math.cos((481266 * jc + 205) * 0.017453292519943);
        tl += 0.004 * Math.cos((1331734 * jc + 283) * 0.017453292519943);
        tl += 0.004 * Math.cos((1844932 * jc + 56) * 0.017453292519943);
        tl += 0.004 * Math.cos((133 * jc + 29) * 0.017453292519943);
        tl += 0.0038 * Math.cos((1781068 * jc + 21) * 0.017453292519943);
        tl += 0.0037 * Math.cos((541062 * jc + 259) * 0.017453292519943);
        tl += 0.0028 * Math.cos((1934 * jc + 145) * 0.017453292519943);
        tl += 0.0027 * Math.cos((918399 * jc + 182) * 0.017453292519943);
        tl += 0.0026 * Math.cos((1379739 * jc + 17) * 0.017453292519943);
        tl += 0.0024 * Math.cos((99863 * jc + 122) * 0.017453292519943);
        tl += 0.0023 * Math.cos((922466 * jc + 163) * 0.017453292519943);
        tl += 0.0022 * Math.cos((818536 * jc + 151) * 0.017453292519943);
        tl += 0.0021 * Math.cos((990397 * jc + 357) * 0.017453292519943);
        tl += 0.0021 * Math.cos((71998 * jc + 85) * 0.017453292519943);
        tl += 0.0021 * Math.cos((341337 * jc + 16) * 0.017453292519943);
        tl += 0.0018 * Math.cos((401329 * jc + 274) * 0.017453292519943);
        tl += 0.0016 * Math.cos((1856938 * jc + 152) * 0.017453292519943);
        tl += 0.0012 * Math.cos((1267871 * jc + 249) * 0.017453292519943);
        tl += 0.0011 * Math.cos((1920802 * jc + 186) * 0.017453292519943);
        tl += 0.0009 * Math.cos((858602 * jc + 129) * 0.017453292519943);
        tl += 0.0008 * Math.cos((1403732 * jc + 98) * 0.017453292519943);
        tl += 0.0007 * Math.cos((790672 * jc + 114) * 0.017453292519943);
        tl += 0.0007 * Math.cos((405201 * jc + 50) * 0.017453292519943);
        tl += 0.0007 * Math.cos((485333 * jc + 186) * 0.017453292519943);
        tl += 0.0007 * Math.cos((27864 * jc + 127) * 0.017453292519943);
        tl += 0.0006 * Math.cos((111869 * jc + 38) * 0.017453292519943);
        tl += 0.0006 * Math.cos((2258267 * jc + 156) * 0.017453292519943);
        tl += 0.0005 * Math.cos((1908795 * jc + 90) * 0.017453292519943);
        tl += 0.0005 * Math.cos((1745069 * jc + 24) * 0.017453292519943);
        tl += 0.0005 * Math.cos((509131 * jc + 242) * 0.017453292519943);
        tl += 0.0004 * Math.cos((39871 * jc + 223) * 0.017453292519943);
        tl += 0.0004 * Math.cos((12006 * jc + 187) * 0.017453292519943);
        tl += 0.0003 * Math.cos((958465 * jc + 340) * 0.017453292519943);
        tl += 0.0003 * Math.cos((381404 * jc + 354) * 0.017453292519943);
        tl += 0.0003 * Math.cos((349472 * jc + 337) * 0.017453292519943);
        tl += 0.0003 * Math.cos((1808933 * jc + 58) * 0.017453292519943);
        tl += 0.0003 * Math.cos((549197 * jc + 220) * 0.017453292519943);
        tl += 0.0003 * Math.cos((4067 * jc + 70) * 0.017453292519943);
        tl += 0.0003 * Math.cos((2322131 * jc + 191) * 0.017453292519943);
        return tl % 360.0;
    }


    public static double moonElongation(double moonEclipticalLongitude, double sunEclipticalLongitude)
    {
        return moonEclipticalLongitude - sunEclipticalLongitude;
    }

    public static double moonPhaseAngle(double moonEclipticLongitude, double sunEclipticLongitude)
    {
        double tl = moonEclipticLongitude;
        double s = sunEclipticLongitude;

        if (tl - s > 0)
        {
            return tl - s;
        }
        else //if (tl - s <= 0)
        {
            return (tl + 360) - s;
        }
    }

    public static double moonPhaseAngle(double jc)
    {
        return moonPhaseAngle(moonEclipticLongitude(jc), sunEclipticLongitude(jc));
    }

    public static double moonRightAscension(double moonEclipticLatitude, double moonEclipticLongitude, double obliqCorr, boolean anglesInDegrees)
    {
        if (anglesInDegrees)
        {
            moonEclipticLongitude = Math.toRadians(moonEclipticLongitude);
            moonEclipticLatitude = Math.toRadians(moonEclipticLatitude);
            obliqCorr = Math.toRadians(obliqCorr);
        }

        double a = Math.sin(moonEclipticLongitude) * Math.cos(obliqCorr) - Math.tan(moonEclipticLatitude) * Math.sin(obliqCorr);
        double b = Math.cos(moonEclipticLongitude);
        double tan = a / b;

        double result = b < 0 ? Math.PI + Math.atan(tan) :
                a < 0 ? 2 * Math.PI + Math.atan(tan) : Math.atan(tan);

        //double ra;
        //double dec;
        //EclipticToRaDec(MoonEclipticLatitude(t), MoonEclipticLongitude(t), ObliqCorr(t), out ra, out dec);

        if (anglesInDegrees)
            result = Math.toDegrees(result);

        return result;
    }

    public static double moonDeclination(double moonEclipticLatitude, double moonEclipticLongitude, double obliqCorr, boolean anglesInDegrees)
    {
        if (anglesInDegrees)
        {
            moonEclipticLongitude = Math.toRadians(moonEclipticLongitude);
            moonEclipticLatitude = Math.toRadians(moonEclipticLatitude);
            obliqCorr = Math.toRadians(obliqCorr);
        }

        double a = Math.sin(moonEclipticLatitude) * Math.cos(obliqCorr) + Math.cos(moonEclipticLatitude) * Math.sin(obliqCorr) * Math.sin(moonEclipticLongitude);
        double result = Math.asin(a);

        if (anglesInDegrees)
            result = Math.toDegrees(result);

        return result;
    }

    public static double moonDeclination(double jc)
    {
        return moonDeclination(moonEclipticLatitude(jc), moonEclipticLongitude(jc),obliquityOfTheEcliptic(jc), true);
    }

    public static double moonRightAscension(double jc)
    {
        return moonRightAscension(moonEclipticLatitude(jc), moonEclipticLongitude(jc),obliquityOfTheEcliptic(jc), true);
    }

    public static double sunRightAscension(double sunAppLong, double obliqCorr, boolean anglesInDegrees)
    {
        if (anglesInDegrees)
        {
            sunAppLong = Math.toRadians(sunAppLong);
            obliqCorr = Math.toRadians(obliqCorr);
        }
        double result = Math.atan2(Math.cos(obliqCorr) * Math.sin(sunAppLong), Math.cos(sunAppLong));

        if (anglesInDegrees)
            result = Math.toDegrees(result);

        return result;
    }


    public static double sunDeclination(double sunAppLong, double obliqCorr, boolean anglesInDegrees)
    {
        if (anglesInDegrees)
        {
            sunAppLong = Math.toRadians(sunAppLong);
            obliqCorr = Math.toRadians(obliqCorr);
        }

        double result = Math.asin(Math.sin(obliqCorr) * Math.sin(sunAppLong));

        if (anglesInDegrees)
            result = Math.toDegrees(result);

        return result;
    }

    public static double sunDeclination(double julianCycle)
    {
        //    return sunDeclination(sunEclipticLongitude(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
        return sunDeclination(sunApparentLong(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
    }

    public static double sunRightAscension(double julianCycle)
    {
    //    return sunRightAscension(sunEclipticLongitude(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
        return sunRightAscension(sunApparentLong(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
    }

    /*
     *
     */
    public static double sunGeocentricDistance(double t)
    {
        double c = 1.00014;
        c += 0.016706 * Math.cos((35999.05 * t + 177.53) * 0.017453292519943);
        c -= 0.000042 * t * Math.cos((35999.05 * t + 177.53) * 0.017453292519943);
        c += 0.000139 * Math.cos((71998 * t + 175) * 0.017453292519943);
        c += 0.000031 * Math.cos((445267 * t + 298) * 0.017453292519943);
        c += 0.000016 * Math.cos((32964 * t + 68) * 0.017453292519943);
        c += 0.000016 * Math.cos((45038 * t + 164) * 0.017453292519943);
        c += 0.000005 * Math.cos((22519 * t + 233) * 0.017453292519943);
        c += 0.000005 * Math.cos((33718 * t + 226) * 0.017453292519943);
        return c * 149597870;// (c / 0.01) * 149597870;
    }

    public static double moonGeocentricDistance(double t)
    {
        double tp = 0.950725;
        tp += 0.05182 * Math.cos((477198.868 * t + 134.963) * 0.017453292519943);
        tp += 0.00953 * Math.cos((413335.35 * t + 100.74) * 0.017453292519943);
        tp += 0.007842 * Math.cos((890534.22 * t + 235.7) * 0.017453292519943);
        tp += 0.002824 * Math.cos((954397.74 * t + 269.93) * 0.017453292519943);
        tp += 0.000858 * Math.cos((1367733.1 * t + 10.7) * 0.017453292519943);
        tp += 0.000531 * Math.cos((854535.2 * t + 238.2) * 0.017453292519943);
        tp += 0.0004 * Math.cos((377336.3 * t + 103.2) * 0.017453292519943);
        tp += 0.000319 * Math.cos((441199.8 * t + 137.4) * 0.017453292519943);
        tp += 0.000271 * Math.cos((445267 * t + 118) * 0.017453292519943);
        tp += 0.000263 * Math.cos((513198 * t + 312) * 0.017453292519943);
        tp += 0.000197 * Math.cos((489205 * t + 232) * 0.017453292519943);
        tp += 0.000173 * Math.cos((1431597 * t + 45) * 0.017453292519943);
        tp += 0.000167 * Math.cos((1303870 * t + 336) * 0.017453292519943);
        tp += 0.000111 * Math.cos((35999 * t + 178) * 0.017453292519943);
        tp += 0.000103 * Math.cos((826671 * t + 201) * 0.017453292519943);
        tp += 0.000084 * Math.cos((63864 * t + 214) * 0.017453292519943);
        tp += 0.000083 * Math.cos((926533 * t + 53) * 0.017453292519943);
        tp += 0.000078 * Math.cos((1844932 * t + 146) * 0.017453292519943);
        tp += 0.000073 * Math.cos((1781068 * t + 111) * 0.017453292519943);
        tp += 0.000064 * Math.cos((1331734 * t + 13) * 0.017453292519943);
        tp += 0.000063 * Math.cos((449334 * t + 278) * 0.017453292519943);
        tp += 0.000041 * Math.cos((481266 * t + 295) * 0.017453292519943);
        tp += 0.000034 * Math.cos((918399 * t + 272) * 0.017453292519943);
        tp += 0.000033 * Math.cos((541062 * t + 349) * 0.017453292519943);
        tp += 0.000031 * Math.cos((922466 * t + 253) * 0.017453292519943);
        tp += 0.00003 * Math.cos((75870 * t + 131) * 0.017453292519943);
        tp += 0.000029 * Math.cos((990397 * t + 87) * 0.017453292519943);
        tp += 0.000026 * Math.cos((818536 * t + 241) * 0.017453292519943);
        tp += 0.000023 * Math.cos((553069 * t + 266) * 0.017453292519943);
        tp += 0.000019 * Math.cos((1267871 * t + 339) * 0.017453292519943);
        tp += 0.000013 * Math.cos((1403732 * t + 188) * 0.017453292519943);
        tp += 0.000013 * Math.cos((341337 * t + 106) * 0.017453292519943);
        tp += 0.000013 * Math.cos((401329 * t + 4) * 0.017453292519943);
        tp += 0.000012 * Math.cos((2258267 * t + 246) * 0.017453292519943);
        tp += 0.000011 * Math.cos((1908795 * t + 180) * 0.017453292519943);
        tp += 0.000011 * Math.cos((858602 * t + 219) * 0.017453292519943);
        tp += 0.00001 * Math.cos((1745069 * t + 114) * 0.017453292519943);
        tp += 0.000009 * Math.cos((790672 * t + 204) * 0.017453292519943);
        tp += 0.000007 * Math.cos((2322131 * t + 281) * 0.017453292519943);
        tp += 0.000007 * Math.cos((1808933 * t + 148) * 0.017453292519943);
        tp += 0.000006 * Math.cos((485333 * t + 276) * 0.017453292519943);
        tp += 0.000006 * Math.cos((99863 * t + 212) * 0.017453292519943);
        tp += 0.000005 * Math.cos((405201 * t + 140) * 0.017453292519943);
        tp = 1.0 / Math.sin(tp * 0.017453292519943);
        tp *= 6378.14;
        return tp;
    }

    public static final double sunMeanGeocentricDistance = 149597870.0;
    public static final double moonMeanGeocentricDistance = 384401.0;

    public static double sunGeocentricDistanceRatioOfTheAverage(double t)
    {
        return sunGeocentricDistance(t) / 149597870.0;
    }

    public static double moonGeocentricDistanceRatioOfTheAverage(double t)
    {
        return moonGeocentricDistance(t) / 384401.0;
    }
    public static double moonMeanAngularDiameter = 206265.0 * 3474.8 / 384401.0;

    public static double moonAngularDiameterRatioOfTheAverage(double angularDiameter)
    {
        return moonAngularDiameter(angularDiameter) / moonMeanAngularDiameter;
    }

    public static double sunAngularDiameter(double angleKm)
    {
        return 2 * Math.toDegrees(Math.atan(sunRadius / angleKm));
    }

    public static double moonAngularDiameter(double angleKm)
    {
        return 206265.0 * 3474.8 / angleKm;
    }

    public static final double sunDiameter = 1392000; // km
    public static final double sunRadius = 696000; // km
    public static final double moonRadius = 1737; // km
    public static final double moonDiameter = 3474.8; // km


    private static double gmst(double jc)
    {
        double d = jc * 36525; // in days since J2000.0
        double jc2 = jc * jc;
        double jc3 = jc * jc2;
        return 280.46061837 + 360.98564736629 * d + 0.000387933 * jc2 - jc3 / 38710000.0;
    }



    public static double greenwichMeanSiderealTime(double jc)
    {
        double t = gmst(jc);
        t = t % 360.0;
        if (t < 0)
            t += 360;
        return t;
    }

    /// <summary>
    /// Returns the Local Mean Sidereal Time (LMST) in degrees.
    /// LMST = GMTS + Longitude
    /// </summary>
    /// <param name="jc">Julian Centuries</param>
    /// <param name="longitude">Longitude in degrees (East positive)</param>
    /// <returns></returns>
    public static double localMeanSiderealTime(double jc, double longitude)
    {
        //Local sidereal time:
        //
        //theta = theta0 + longitude(eastern longitudes positive, western negative)
        //
        //Hour angle: tau = theta - RA;
        double t = gmst(jc) + longitude;
        t = t % 360.0;
        if (t < 0)
            t += 360;
        return t;
    }
/*

    public static double SunEqOfCtr(double geomSunMeanAnom, double julianCycle)
    {
        double num = Radians(geomSunMeanAnom);
        return Sin(num) * (1.914602 - julianCycle * (0.004817 + 0.000014 * julianCycle)) + Sin(2 * num) * (0.019993 - 0.000101 * julianCycle) + Sin(3.0 * num) * 0.000289;
    }

    public static double SunEqOfCtr(double julianCycle)
    {
        double geomSunMeanAnom = GeomMeanAnomSun(julianCycle);
        double num = Radians(geomSunMeanAnom);
        return Sin(num) * (1.914602 - julianCycle * (0.004817 + 0.000014 * julianCycle)) + Sin(2 * num) * (0.019993 - 0.000101 * julianCycle) + Sin(3.0 * num) * 0.000289;
    }

    public static double SunTrueLong(double sunMeanLong, double sunEqOfCtr)
    {
        return sunMeanLong + sunEqOfCtr;
    }

    public static double SunTrueAnom(double sunMeanAnom, double sunEqOfCtr)
    {
        return sunMeanAnom + sunEqOfCtr;
    }

    public static double SunTrueLong(double julianCycle)
    {
        return GeomMeanLongSun(julianCycle) + SunEqOfCtr(julianCycle);
    }

    public static double SunTrueAnom(double julianCycle)
    {
        double num = GeomMeanAnomSun(julianCycle);
        return num + SunEqOfCtr(num, julianCycle);
    }

    public static double SunRadVector(double eccentEarthOrbit, double sunTrueAnom)
    {
        return (1.000001018 * (1 - eccentEarthOrbit * eccentEarthOrbit)) / (1 + eccentEarthOrbit * Cos(Radians(sunTrueAnom)));
    }

    public static double SunRadVector(double julianCycle)
    {
        double eccentEarthOrbit = EccentEarthOrbit(julianCycle);
        return (1.000001018 * (1 - eccentEarthOrbit * eccentEarthOrbit)) / (1 + eccentEarthOrbit * Cos(Radians(SunTrueAnom(julianCycle))));
    }
*/

    public static double sunMeanLongitude(double julianCycle) // in degrees
    {
        double num = (280.46646 + julianCycle * (36000.76983 + julianCycle * 0.0003032)) % 360.0;
        if (num < 0)
            num += 360.0;
        return num;
    }

    public static double sunMeanAnomaly(double julianCycle) // in degrees
    {
        return 357.52911 + julianCycle * (35999.05029 - 0.0001537 * julianCycle);
    }

    public static double eccentricityOfEarthOrbit(double julianCycle) // unitless
    {
        return 0.016708634 - julianCycle * (0.000042037 + 0.0000001267 * julianCycle);
    }

    public static double sunEquationOfCenter(double t)// in degrees
    {
        double m = sunMeanAnomaly(t);
        m = Math.toRadians(m);
        double sinm = Math.sin(m);
        double sin2m = Math.sin(m+m);
        double sin3m = Math.sin(m+m+m);
        double C = sinm * (1.914602 - t * (0.004817 + 0.000014 * t)) + sin2m * (0.019993 - 0.000101 * t) + sin3m * 0.000289;
        return C;
    }


    public static double sunTrueLong(double t)
    {
        double num = sunMeanLongitude(t) + sunEquationOfCenter(t);
        return num; // in degrees
    }

    public static double sunTrueAnomaly(double t)
    {
        double m = sunMeanAnomaly(t);
        double c = sunEquationOfCenter(t);
        double v = m + c;
        return v;		// in degrees
    }

    public static double sunRadVector(double t)
    {
        double v = sunTrueAnomaly(t);
        double e = eccentricityOfEarthOrbit(t);
        double R = (1.000001018 * (1 - e * e)) / (1 + e * Math.cos(Math.toRadians(v)));
        return R;		// in AUs
    }

    public static double sunApparentLong(double t)
    {
        double o = sunTrueLong(t);
        double omega = 125.04 - 1934.136 * t;
        double lambda = o - 0.00569 - 0.00478 * Math.sin(Math.toRadians(omega));
        return lambda;		// in degrees
    }


    public static double meanObliquityOfTheEcliptic(double julianCycle) // in degrees
    {
        //double seconds = 21.448 - t*(46.8150 + t*(0.00059 - t*(0.001813)));
        //double e0 = 23.0 + (26.0 + (seconds/60.0))/60.0;
        //return e0;		// in degrees
        return 23.0 + (26.0 + ((21.448 - julianCycle * (46.815 + julianCycle * (0.00059 - julianCycle * 0.001813)))) / 60.0) / 60.0;
    }

    // correction
    public static double obliquityOfTheEcliptic(double t, double meanObliquityOfTheEcliptic)
    {
        double e0 = meanObliquityOfTheEcliptic(t);
        double omega = 125.04 - 1934.136 * t;
        double e = e0 + 0.00256 * Math.cos(Math.toRadians(omega));
        return e;		// in degrees
        //return meanObliquityOfTheEcliptic + 0.00256 * Math.cos(Math.toRadians(125.04 - 1934.136 * t));
    }

    public static double obliquityOfTheEcliptic(double julianCycle)
    {
        return obliquityOfTheEcliptic(julianCycle, meanObliquityOfTheEcliptic(julianCycle));
    }

    /* wiki pas testé
    public static double sunEclipticLongitudeEst(double meanAnomaly, double meanLongitude)
    {
        return meanLongitude + 1.915 * Math.sin(meanAnomaly) + 0.020 * Math.sin(2 * meanAnomaly);
    }*/

    /*
    EOT = GHA − GMHA
        EOT, the time difference between apparent solar time and mean solar time;
    GHA, the Greenwich Hour Angle of the apparent (actual) Sun;
    GMHA = Universal Time − Offset, the Greenwich Mean Hour Angle of the mean (fictitious) Sun.
     */
    public static double equationOfTime(double eccentricityOfEarthOrbit, double obliquityOfTheEcliptic, double sunMeanLongitude, double sunMeanAnomaly)
    {
        double epsilon = Math.toRadians(obliquityOfTheEcliptic);
        double l0 = Math.toRadians(sunMeanLongitude);
        double e = eccentricityOfEarthOrbit;
        double m = Math.toRadians(sunMeanAnomaly);

        double y = Math.tan(epsilon/2.0);
        y *= y;

        double sin2l0 = Math.sin(2.0 * l0);
        double sinm   = Math.sin(m);
        double cos2l0 = Math.cos(2.0 * l0);
        double sin4l0 = Math.sin(4.0 * l0);
        double sin2m  = Math.sin(2.0 * m);

        double eot = y * sin2l0 - 2.0 * e * sinm + 4.0 * e * y * sinm * cos2l0 - 0.5 * y * y * sin4l0 - 1.25 * e * e * sin2m;
        return Math.toDegrees(eot)*4.0;	// in minutes of time
    }

    public static double equationOfTime(double t)
    {
        return equationOfTime(eccentricityOfEarthOrbit(t), obliquityOfTheEcliptic(t), sunMeanLongitude(t), sunMeanAnomaly(t));
    }

    public static double timeOfDay(DateTime date)
    {
        return date.getSecondOfDay() / 86400.0;
    }

    public static double zenith(double H, double latitude, double dec) {

        double csz = Math.sin(latitude) * Math.sin(dec) + Math.cos(latitude) * Math.cos(dec) * Math.cos(H);
        if (csz > 1.0)
        {
            csz = 1.0;
        } else if (csz < -1.0)
        {
            csz = -1.0;
        }
        return Math.acos(csz);
    }

    public static double azimuth(double H, double latitude, double dec) {
        //return Math.atan2(Math.sin(H), Math.cos(H) * Math.sin(latitude) - Math.tan(dec) * Math.cos(latitude));
        return Math.atan2(-Math.sin(H), Math.cos(latitude) * Math.tan(dec) - Math.sin(latitude) * Math.cos(H));
    }

    public static double elevation(double H, double latitude, double dec) {
        // also = 90 - zenith
        return Math.asin(Math.sin(latitude) * Math.sin(dec) + Math.cos(latitude) * Math.cos(dec) * Math.cos(H));
    }

    //function hourAngle(h, phi, d) { return acos((sin(h) - sin(phi) * sin(d)) / (cos(phi) * cos(d))); }
    public static double hourAngle(double trueSolarTimeMin)
    {
        return (trueSolarTimeMin / 4) < 0 ? (trueSolarTimeMin / 4 + 180) : (trueSolarTimeMin / 4 - 180);


        //var hourAngle = trueSolarTime / 4.0 - 180.0;
        //if (hourAngle < -180)
        //{
        //    hourAngle += 360.0
        //}
    }
    public static double trueSolarTime(double localTimeInMin, double eot, double longitude, double offsetInHours)
    {
        return (localTimeInMin + solarTimeFix(eot, longitude, offsetInHours)) % 1440;

        //double totalDays = today.getSecondOfDay() / 86400.0;
        //return (totalDays * 1440 + eot + 4 * longitude - 60 * offsetInHours) % 1440;
    }

    public static double solarTimeFix(double eot, double longitude, double zoneOffset)
    {
        return eot + 4.0 * longitude - 60.0 * zoneOffset;
    }

    public static double approximateAtmosphericRefractionCorrection(double elevation)
    {
        double tan_h = Math.tan(Math.toRadians(elevation));
        double tan_h3 = tan_h * tan_h * tan_h;
        double tan_h5 = tan_h3 * tan_h * tan_h;

        double num = -20.772 / tan_h;
        double num2 = 1735.0 + elevation * (-518.2 + elevation * (103.4 + elevation * (-12.79 + elevation * 0.711)));
        double num3 = 58.1 / tan_h - 0.07 / tan_h3 + 0.000086 / tan_h5;

        return elevation > 85.0 ? 0 : (elevation < -0.575 ? num : (elevation < 5.0 ? num2 : num3)) / 3600.0;

       // if (exoatmElevation > 85.0) {
       //     var refractionCorrection = 0.0;
       // } else {
       //     var te = Math.tan (degToRad(exoatmElevation));
       //     if (exoatmElevation > 5.0) {
       //         var refractionCorrection = 58.1 / te - 0.07 / (te*te*te) + 0.000086 / (te*te*te*te*te);
       //     } else if (exoatmElevation > -0.575) {
       //         var refractionCorrection = 1735.0 + exoatmElevation * (-518.2 + exoatmElevation * (103.4 + exoatmElevation * (-12.79 + exoatmElevation * 0.711) ) );
       //     } else {
       //         var refractionCorrection = -20.774 / te;
       //     }
       //     refractionCorrection = refractionCorrection / 3600.0;
       // }

    }

    public static double solarElevationCorrected(double elevation)
    {
        return elevation + approximateAtmosphericRefractionCorrection(elevation);
    }

    public static AzEl sunCoords(DateTime date, double latitude, double longitude, double zone)
    {
        double t = julianCenturies(date);
        double eqTime = equationOfTime(t);
        double dec  = sunDeclination(t);
        double solarTimeFix = solarTimeFix(eqTime, longitude, zone);
        double localTime = date.getMinuteOfDay();
        double trueSolarTime = (localTime + solarTimeFix) % 1440;
        double ha = hourAngle(trueSolarTime);
        double zenith = Math.toDegrees(zenith(Math.toRadians(ha), Math.toRadians(latitude),  Math.toRadians(dec)));
        double azimuth =  Math.toDegrees(azimuth(Math.toRadians(ha), Math.toRadians(latitude),  Math.toRadians(dec))) % 360;
        if (azimuth < 0)
            azimuth += 360;
        double refractionCorrection = approximateAtmosphericRefractionCorrection(90.0 - zenith);
        double solarZen = zenith - refractionCorrection;
        double el = 90 - solarZen; // ou 90 - zenith + atmCorr
        return new AzEl(azimuth, el);
    }

    public static AzEl convertEquatorialToHorizontal(double declination, double hourAngle, double latitude) // lat of observer
    {
        double lat = Math.toRadians(latitude);
        //http://www.geoastro.de/elevazmoon/basics/meeus.htm#horizon
        double dec = Math.toRadians(declination); // delta
        double ha = Math.toRadians(hourAngle); // tau
        double sin_lat = Math.sin(lat); // beta
        double cos_lat = Math.cos(lat);
        double cos_ha = Math.cos(ha);
        double sin_el = sin_lat * Math.sin(dec) + cos_lat * Math.cos(dec) * cos_ha;
        double el = Math.toDegrees(Math.asin(sin_el));
        double az = Math.toDegrees(Math.atan2(-Math.sin(ha), cos_lat * Math.tan(dec) - sin_lat * cos_ha));
        return new AzEl(az, el);
    }

    public static double moonHorizontalParallax(double moonangleKm) // horizontal parallax (arcseconds), Meeus S. 263
    {
        return 8.794 / (moonangleKm / 149.59787e6);
    }

    public static double parallaxInAltitude(double moonangle, double elevation)
    {
        double horParal = moonHorizontalParallax(moonangle) / 3600; // arcseconds to degrees
        double horParalRad = Math.toRadians(horParal);
        elevation = Math.toRadians(elevation);
        double sin_p = Math.cos(elevation) * Math.sin(horParalRad); // parallax in altitude (degrees)
        double p = Math.toDegrees(Math.asin(sin_p));
        return p;
    }
    public static double moonElevationCorrected(double moonangle, double elevation)
    {
        double parallax = parallaxInAltitude(moonangle, elevation);
        double refraction = moonRefraction(elevation);

        return elevation - parallax + refraction;
    }
    public static boolean isSupermoon(double moonangleKm) // horizontal parallax (arcseconds), Meeus S. 263
    {
        return moonangleKm <= 360000;
    }
    public static boolean isMicromoon(double moonangleKm) // horizontal parallax (arcseconds), Meeus S. 263
    {
        return moonangleKm >= 405000;
    }
    public static double moonRefraction(double elevation) // The refraction R is calculated by Saemundsson's formula
    {
        double num = elevation + (10.3 / (elevation + 5.11));
        num = Math.toRadians(num);
        double R = 1.02 / Math.tan(num); // arcminutes
        R = R / 60; // arcminiutes to degrees
        return R;
    }

    public static RaDec convertEclipticToEquatorial(double eclipticLatitude, double eclipticLongitude, double obliquityOfTheEcliptic, double localSiderealTime)
    {
        double lng = Math.toRadians(eclipticLongitude);
        double lat = Math.toRadians(eclipticLatitude);
        double obliq = Math.toRadians(obliquityOfTheEcliptic);

        double sin_e = Math.sin(obliq);
        double cos_e = Math.cos(obliq);

        double sin_lng = Math.sin(lng);
        double cos_lng = Math.cos(lng);

        double sin_decl = Math.sin(lat) * cos_e + Math.cos(lat) * sin_e * sin_lng;

        double dec = Math.toDegrees(Math.asin(sin_decl));

        double num = sin_lng * cos_e - Math.tan(lat) * sin_e;
        double tan_ra = num / cos_lng;

        double ra = cos_lng < 0 ? Math.PI + Math.atan(tan_ra) : num < 0 ? 2 * Math.PI + Math.atan(tan_ra) : Math.atan(tan_ra);
        ra = Math.toDegrees(ra);
        // ha = local sidereal time - right ascension
        // ou
        // ha = Greenwich sidereal time - observer's longitude - right ascension

        return new RaDec(dec, ra, localSiderealTime - ra);
    }

    public static AzEl MoonHorizontalCoordinates(double jc, double latitude, double longitude)
    {
        double eclipticLatitude = moonEclipticLatitude(jc);
        double eclipticLongitide = moonEclipticLongitude(jc);
        double obliquityOfTheEcliptic = obliquityOfTheEcliptic(jc);
        double lmst = localMeanSiderealTime(jc, longitude);

        RaDec eq = convertEclipticToEquatorial(eclipticLatitude, eclipticLongitide, obliquityOfTheEcliptic, lmst);

        AzEl ho = convertEquatorialToHorizontal(eq.declination, eq.hourAngle, latitude);
        double angle = moonGeocentricDistance(jc);
        ho.elevation = moonElevationCorrected(angle, ho.elevation);

        return ho;
    }

    public static AzEl moonCoords(DateTime date, double latitude, double longitude, double zone)
    {

        double t = julianCenturies(date);
        AzEl res = MoonHorizontalCoordinates(t, latitude, longitude);

       // double eqTime = equationOfTime(t);
        double dec  = moonDeclination(t);
        double ra = moonRightAscension(t);

        double theta0 = greenwichMeanSiderealTime(t);
        double theta = localMeanSiderealTime(t,longitude);

        double localHourAngle = (theta - ra) % 360; // tau
        if (localHourAngle < 0)
            localHourAngle += 360;

        // convert decl, ha to horizontal coordinates
        AzEl azel = convertEquatorialToHorizontal(dec, localHourAngle,latitude);

        azel.elevation = moonElevationCorrected(moonGeocentricDistance(t), azel.elevation);
        return azel;
/*
        double solarTimeFix = solarTimeFix(eqTime, longitude, zone);
        double localTime = date.getMinuteOfDay();
        double trueSolarTime = (localTime + solarTimeFix) % 1440;
        double ha = hourAngle(trueSolarTime);

        double zenith = Math.toDegrees(zenith(Math.toRadians(ha), Math.toRadians(latitude),  Math.toRadians(dec)));
        double azimuth =  Math.toDegrees(azimuth(Math.toRadians(ha), Math.toRadians(latitude),  Math.toRadians(dec))) % 360;
        if (azimuth < 0)
            azimuth += 360;
        double refractionCorrection = approximateAtmosphericRefractionCorrection(90.0 - zenith);
        double solarZen = zenith - refractionCorrection;
        double el = 90 - solarZen; // ou 90 - zenith + atmCorr
        return new AzEl(azimuth, el);*/
    }



    public static double solarNoon(DateTime date, double longitude, double timezoneOffset)
    {
        double jd = julianDay(date);
        double tnoon = julianCenturies(jd - longitude/360);
        double eqTime = equationOfTime(tnoon); // estimation
        double solNoonOffset = 720.0 - (longitude * 4) - eqTime; // in minutes
        double newt = julianCenturies(jd + solNoonOffset/1440.0);
        eqTime = equationOfTime(newt); // more precise

        double solNoonLocal = 720 - (longitude * 4) - eqTime + (timezoneOffset*60.0);// in minutes
        //if(dst) solNoonLocal += 60.0

        solNoonLocal %= 1440;
        if (solNoonLocal < 0)
            solNoonLocal += 1440;

        return solNoonLocal;
    }

    public static double hourAngleSunrise(double lat, double solarDec)
    {
        double latRad = Math.toRadians(lat);
        double sdRad  = Math.toRadians(solarDec);
        double HAarg = (Math.cos(Math.toRadians(90.833))/(Math.cos(latRad)*Math.cos(sdRad))-Math.tan(latRad) * Math.tan(sdRad));
        double HA = Math.acos(HAarg);
        return HA;		// in radians (for sunset, use -HA)
    }

    public static double hourAngleFromElevation(double lat, double dec, double elevation)
    {
        double latRad = Math.toRadians(lat);
        double sdRad  = Math.toRadians(dec);
        double altRad = Math.toRadians(elevation);
        double HAarg = (Math.sin(altRad)/(Math.cos(latRad)*Math.cos(sdRad))-Math.tan(latRad) * Math.tan(sdRad));
        double HA = Math.toDegrees(Math.acos(HAarg));
        return HA;
    }

    public static double hourAngleSunset(double lat, double solarDec)
    {
        return -hourAngleSunrise(lat, solarDec);
    }

    public static DateTime getDateFromJulianDay(double jd)
    {
        long millis = DateTimeUtils.fromJulianDay(jd);
        return new DateTime(millis);
    }

    public static DateTime getDateFromJulianDay(double jd, double minutes)
    {
        return getDateFromJulianDay(jd + minutes / 1440.0);
    }

    public static double sunriseUTC(boolean rise, double jd, double latitude, double longitude) {
        double t = julianCenturies(jd);
        double eqTime = equationOfTime(t);
        double solarDec = sunDeclination(t);
        double hourAngle = hourAngleSunrise(latitude, solarDec);
        hourAngle = Math.toDegrees(hourAngle);
        if (!rise) hourAngle = -hourAngle;
        double delta = longitude + hourAngle;
        double timeUTC = 720 - (4.0 * delta) - eqTime;    // in minutes
        return timeUTC;
    }

    public static double moonriseUTC(boolean rise, double jd, double latitude, double longitude) {
        double t = julianCenturies(jd);
        double eqTime = equationOfTime(t);
        double solarDec = moonDeclination(t);
        double hourAngle = hourAngleSunrise(latitude, solarDec);
        hourAngle = Math.toDegrees(hourAngle);
        if (!rise) hourAngle = -hourAngle;
        double delta = longitude + hourAngle;
        double timeUTC = 720 - (4.0 * delta) - eqTime;    // in minutes
        return timeUTC;
    }

    public static double moonAge(double jd) // days
    {
        double begin = ((int)((jd - 2449128.59) / 29.53058867)) * 29.53058867 + 2449128.59;
        return (jd - begin);// 29.53058867;
    }

    public static DateTime sunrise(DateTime date, double latitude, double longitude, double timeZoneOffset)
    {
        date = date.withTime(0, 0, 0, 0);

        double jd = julianDay(date);
        //double julianCycle = julianCenturies(date);
        //double solarNoon = solarNoon(date, longitude, 1);
        //double solarNoon2 = SiderealTime(julianCycle) / 15.0 - EqOfTime(julianCycle) / 60 + date.Offset.TotalHours - longitude / 15.0;
        //solarNoon2 /= 24.0;
        double sunriseUTC = sunriseUTC(true, jd, latitude, longitude);
        double newSunriseUTC = sunriseUTC(true, jd + sunriseUTC/1440.0, latitude, longitude);

        //if (true) // newSunriseUTC valid
        //{
            double timeLocal = newSunriseUTC +timeZoneOffset * 60;

            double riseT = julianCenturies(jd + newSunriseUTC/1440.0);
            //double riseAz = calcAzEl(0, riseT, timeLocal, latitude, longitude, timezone)

            //timeLocal += ((dst) ? 60.0 : 0.0);
            if ( (timeLocal >= 0.0) && (timeLocal < 1440.0) ) {
                return getDateFromJulianDay(jd, timeLocal);
            } else  {
                double jday = jd;
                double increment = ((timeLocal < 0) ? 1 : -1);
                while ((timeLocal < 0.0)||(timeLocal >= 1440.0)) {
                    timeLocal += increment * 1440.0;
                    jday -= increment;
                }
                return getDateFromJulianDay(jday, timeLocal);
            }
        //}


        //double ha = HourAngleFromElevation(latitude, sunDeclination(julianCycle), -0.833);
        //double sunrise = SunriseTimeLST(solarNoon, ha);
        //return date.Midnight().AddDays(sunrise);
    }


    public static DateTime moonrise2(DateTime date, double latitude, double longitude, double timeZoneOffset)
    {
        date = date.withTime(0, 0, 0, 0);

        double jd = julianDay(date);
        //double julianCycle = julianCenturies(date);
        //double solarNoon = solarNoon(date, longitude, 1);
        //double solarNoon2 = SiderealTime(julianCycle) / 15.0 - EqOfTime(julianCycle) / 60 + date.Offset.TotalHours - longitude / 15.0;
        //solarNoon2 /= 24.0;

        double sunriseUTC = moonriseUTC(true, jd, latitude, longitude);
        double newSunriseUTC = moonriseUTC(true, jd + sunriseUTC/1440.0, latitude, longitude);

//        When the Local Sidereal Time equals the Sun's RA, then the Sun is in the south:



        //if (true) // newSunriseUTC valid
        //{
        double timeLocal = newSunriseUTC +timeZoneOffset * 60;

        double riseT = julianCenturies(jd + newSunriseUTC/1440.0);
        //double riseAz = calcAzEl(0, riseT, timeLocal, latitude, longitude, timezone)

        //timeLocal += ((dst) ? 60.0 : 0.0);
        if ( (timeLocal >= 0.0) && (timeLocal < 1440.0) ) {
            return getDateFromJulianDay(jd, timeLocal);
        } else  {
            double jday = jd;
            double increment = ((timeLocal < 0) ? 1 : -1);
            while ((timeLocal < 0.0)||(timeLocal >= 1440.0)) {
                timeLocal += increment * 1440.0;
                jday -= increment;
            }
            return getDateFromJulianDay(jday, timeLocal);
        }
        //}


        //double ha = HourAngleFromElevation(latitude, sunDeclination(julianCycle), -0.833);
        //double sunrise = SunriseTimeLST(solarNoon, ha);
        //return date.Midnight().AddDays(sunrise);
    }


    public static DateTime sunset(DateTime date, double latitude, double longitude, double timeZoneOffset)
    {
        date = date.withTime(0, 0, 0, 0);
        double jd = julianDay(date);
        //double julianCycle = julianCenturies(date);
        //double solarNoon = solarNoon(date, longitude, 1);
        //double solarNoon2 = SiderealTime(julianCycle) / 15.0 - EqOfTime(julianCycle) / 60 + date.Offset.TotalHours - longitude / 15.0;
        //solarNoon2 /= 24.0;
        double sunriseUTC = sunriseUTC(false,jd, latitude, longitude);
        double newSunriseUTC = sunriseUTC(false,jd + sunriseUTC/1440.0, latitude, longitude);

        //if (true) // newSunriseUTC valid
        //{
        double timeLocal = newSunriseUTC +timeZoneOffset * 60;

        double riseT = julianCenturies(jd + newSunriseUTC/1440.0);
        //double riseAz = calcAzEl(0, riseT, timeLocal, latitude, longitude, timezone)

        //timeLocal += ((dst) ? 60.0 : 0.0);
        if ( (timeLocal >= 0.0) && (timeLocal < 1440.0) ) {
            return getDateFromJulianDay(jd, timeLocal);
        } else  {
            double jday = jd;
            double increment = ((timeLocal < 0) ? 1 : -1);
            while ((timeLocal < 0.0)||(timeLocal >= 1440.0)) {
                timeLocal += increment * 1440.0;
                jday -= increment;
            }
            return getDateFromJulianDay(jday, timeLocal);
        }
    }

    public static DateTime moonrise(DateTime date, double latitude, double longitude, int iter)
    {
        date = date.withTime(0,0,0,0);
        double jd = julianDay(date);
        double jc = julianCenturies(jd);
        double lst = localMeanSiderealTime(jc, longitude);
        double radec[] = new double[2];
        moonEquatorialCoordinates(jc, latitude, longitude, radec);
        double hourAngleMoonrise = hourAngleMoonrise(latitude, radec[1]); // en degrees

        //double altaz[] = new double[2];
        //moonHorizontalCoordinates(jc, latitude, longitude, altaz);

        // meridian
        double tMeridian = (radec[0] - lst) / 15.0; // degrees to hours

        for (int i = 1 ; i < iter; i++)
        {
            double jdMeridian = jd + tMeridian/24;
            double jcMeridian = julianCenturies(jdMeridian);
            //double decMeridian = moonDeclination(jcMeridian);
            double raMeridian = moonRightAscension(jcMeridian);
            double lstMeridian = localMeanSiderealTime(jcMeridian, longitude);
            //double haMeridian = hourAngleMoonrise(latitude, decMeridian);

            tMeridian = (raMeridian - lst) / 15.0;
        }

        // rise
        double tRise = ((radec[0] - lst - hourAngleMoonrise) / 15.0);
        for (int i = 1 ; i < iter; i++)
        {
            double jdRise = jd + tRise/24;
            double jcRise = julianCenturies(jdRise);
            double decRise = moonDeclination(jcRise);
            double raRise = moonRightAscension(jcRise);
            double haRise = hourAngleMoonrise(latitude, decRise);

            tRise = (raRise - haRise) / 15.0;
        }

        // set
        double tSet = ((radec[0] - lst + hourAngleMoonrise) / 15.0);
        for (int i = 1 ; i < iter; i++)
        {
            double jdSet = jd + tSet/24;
            double jcSet = julianCenturies(jdSet);
            double decSet = moonDeclination(jcSet);
            double raSet = moonRightAscension(jcSet);
            double haSet = hourAngleMoonrise(latitude, decSet);

            tSet = (raSet - haSet) / 15.0;
        }

        return getDateFromJulianDay(jd + tRise/24);
    }

    public static double hourAngleMoonrise(double latitude, double declination)
    {
        //Under normal atmospheric conditions at sea level, the upper limb of the Moon will
        // then appear to be tangent with a level, unobstructed horizon. No account is taken
        //of the Moon's phase; that is, the Moon is always regarded as a disk in the sky and
        //the upper limb might be dark. Here again, a constant of 34 arcminutes (0.5666 degree)
        //is used to account for atmospheric refraction. The Moon's apparent radius varies from
        //15 to 17 arcminutes and its horizontal parallax varies from 54 to 61 arcminutes.
        //Adding all the terms above together, the center of the Moon at rise or set is geometrically
        // 5 to 10 arcminutes above the observer's "geocentric horizon" - the horizontal plane
        //that passes through the Earth's center, orthogonal to the observer's local vertical.

        // 34 arcminutes = 0.5666

        //90.5666 degrees + Moon's apparent angular radius - Moon's horizontal parallax

        //    = 57' [horizontal parallax] - 34'[refraction] -16' [semi-diameter]  + 0° = +7'

        return hourAngleFromElevation(latitude, declination, 7/60.0);
    }

    /**
     * Convert RGB components to HSV.
     * <ul>
     *   <li><code>radec[0]</code> is Right Ascension \([0..360[\)</li>
     *   <li><code>radec[1]</code> is Declination \([0...1]\)</li>
     * </ul>
     * @param jc  julian centuries
     * @param latitude  red component value \([0..255]\)
     * @param longitude  green component value \([0..255]\)
     * @param radec  2 element array which holds the resulting HSV components.
     */
    public static void moonEquatorialCoordinates(double jc,
                                                 @FloatRange (from = -90, to = 90) double latitude,
                                                 @FloatRange (from = -180, to = 180) double longitude,
                                                 @Size(2) double radec[]) {
        if (radec.length < 2) {
            throw new RuntimeException("2 components required for decRaHa");
        }

        double eclipticLatitude = moonEclipticLatitude(jc);
        double eclipticLongitide = moonEclipticLongitude(jc);
        double obliquityOfTheEcliptic = obliquityOfTheEcliptic(jc);
        convertEclipticToEquatorial(eclipticLatitude, eclipticLongitide, obliquityOfTheEcliptic, radec);
    }

    /**
     * Convert RGB components to HSV.
     * <ul>
     *   <li><code>hsv[0]</code> is Hue \([0..360[\)</li>
     *   <li><code>hsv[1]</code> is Saturation \([0...1]\)</li>
     *   <li><code>hsv[2]</code> is Value \([0...1]\)</li>
     * </ul>
     * @param latitude  red component value \([0..255]\)
     * @param longitude  green component value \([0..255]\)
     * @param altaz  2 element array which holds the resulting HSV components.
     */
    public static void moonHorizontalCoordinates(double jc,
            @FloatRange (from = -90, to = 90) double latitude,
            @FloatRange (from = -180, to = 180) double longitude,
            @Size(2) double altaz[]) {
        if (altaz.length < 2) {
            throw new RuntimeException("2 components required for decRaHa");
        }

        double eclipticLatitude = moonEclipticLatitude(jc);
        double eclipticLongitide = moonEclipticLongitude(jc);
        double obliquityOfTheEcliptic = obliquityOfTheEcliptic(jc);
        double localSiderealTime = localMeanSiderealTime(jc, longitude);

        double radec[] = new double[2];
        convertEclipticToEquatorial(eclipticLatitude, eclipticLongitide, obliquityOfTheEcliptic, radec);

        double rightAscension = radec[0];
        double declination = radec[1];
        double hourAngle = localSiderealTime - rightAscension;

        AzEl ho = convertEquatorialToHorizontal(declination, hourAngle, latitude);

        double angle = moonGeocentricDistance(jc);
        ho.elevation = moonElevationCorrected(angle, ho.elevation);

        altaz[0] = ho.elevation;
        altaz[1] = ho.azimuth;
    }
    /**
     * Convert ecliptic coordinates to equatorial coordinates.
     * <ul>
     *   <li><code>radec[0]</code> is Hue \([0..360[\)</li>
     *   <li><code>radec[1]</code> is Saturation \([0...1]\)</li>
     *   <li><code>radec[2]</code> is Value \([0...1]\)</li>
     * </ul>
     * @param eclipticLatitude  red component value \([0..255]\)
     * @param eclipticLongitude  green component value \([0..255]\)
     * @param radec  3 element array which holds the resulting HSV components.
     */
    public static void convertEclipticToEquatorial(double eclipticLatitude, double eclipticLongitude, double obliquityOfTheEcliptic,
                                                   double[] radec)
    {
        double lng = Math.toRadians(eclipticLongitude);
        double lat = Math.toRadians(eclipticLatitude);
        double obliq = Math.toRadians(obliquityOfTheEcliptic);

        double sin_obliq = Math.sin(obliq);
        double cos_obliq = Math.cos(obliq);
        double sin_lng = Math.sin(lng);
        double cos_lng = Math.cos(lng);

        double sin_dec = Math.sin(lat) * cos_obliq + Math.cos(lat) * sin_obliq * sin_lng;
        double dec = Math.asin(sin_dec);

        double num = sin_lng * cos_obliq - Math.tan(lat) * sin_obliq;
        double tan_ra = num / cos_lng;
        double ra = cos_lng < 0 ?
                Math.PI + Math.atan(tan_ra) :
                num < 0 ?
                        2 * Math.PI + Math.atan(tan_ra) :
                        Math.atan(tan_ra);

        radec[0] = Math.toDegrees(ra);
        radec[1] = Math.toDegrees(dec);

        // ha = local sidereal time - right ascension
        // ou
        // ha = Greenwich sidereal time - observer's longitude - right ascension

       // return new RaDec(dec, ra, localSiderealTime - ra);
    }

    /*public static double dayOfYearFromJD(double jd)
    {
        int z = (int)Math.floor(jd + 0.5);
        double f = (jd + 0.5) - z;
        double A = z;
        if (z < 2299161) {
            A = z;
        } else {
            double alpha = Math.floor((z - 1867216.25)/36524.25);
            A = z + 1 + alpha - Math.floor(alpha/4);
        }
        double B = A + 1524;
        int C = (int)Math.floor((B - 122.1)/365.25);
        int D = (int)Math.floor(365.25 * C);
        int E = (int)Math.floor((B - D)/30.6001);
        int day = B - D - (int)Math.floor(30.6001 * E) + f;
        int month = (E < 14) ? E - 1 : E - 13;
        int year = (month > 2) ? C - 4716 : C - 4715;

        int k = (isLeapYear(year) ? 1 : 2);
        double doy = Math.floor((275 * month)/9) - k * Math.floor((month + 9)/12) + day -30;
        return doy;
    }*/

    /// <summary>
    /// Converts a angle from angle unit to another.
    /// </summary>
    /// <param name="angle">a double that represents the angle.</param>
    /// <param name="fromUnits">The angle unit the original angle is in.</param>
    /// <param name="toUnits">The desired angle unit to convert to.</param>
    /// <returns>A angle in the new units.</returns>
    public static double convertAngle(double angle, AngleUnits fromUnits, AngleUnits toUnits)
    {
        // Convert the angle to degrees
        switch (fromUnits)
        {
            case Radians:
                angle = Math.toDegrees(angle);
                break;
            case Minutes:
                angle /= 4.0;
                break;
            case Hours:
                angle *= 15.0;
                break;
            case Days:
                angle *= 360.0;
                break;
            case Degrees:
                break;
        }

        //Convert from degrees to output angle unit
        switch (toUnits)
        {
            case Radians:
                angle = Math.toRadians(angle);
                break;
            case Minutes:
                angle *= 4.0;
                break;
            case Hours:
                angle /= 15.0;
                break;
            case Days:
                angle /= 360.0;
                break;
            case Degrees:
                break;
        }

        return angle;
    }

}
