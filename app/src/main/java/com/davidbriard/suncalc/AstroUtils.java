package com.davidbriard.suncalc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

public class AstroUtils {

    final static DateTime Epoch = new DateTime(1858, 11, 16, 12, 0, 0);

    public static double julianDay(DateTime date) {
        return DateTimeUtils.toJulianDay(date.getMillis());//Epoch.getMillis());
       // return (date.ToUniversalTime() - Epoch).TotalDays + 2400000.0;
    }

    /// Returns the number of Julian centuries since Jan 1, 2000, 12 UT
    public static double JulianCenturies(DateTime date) {
        return (julianDay(date) - 2451545.0) / 36525.0;
    }

    public static double meanObliquityOfTheEcliptic(double julianCycle)
    {
        return 23.0 + (26.0 + ((21.448 - julianCycle * (46.815 + julianCycle * (0.00059 - julianCycle * 0.001813)))) / 60.0) / 60.0;
    }

    public static double obliquityOfTheEcliptic(double julianCycle, double meanObliquityOfTheEcliptic)
    {
        return meanObliquityOfTheEcliptic + 0.00256 * Math.cos(Math.toRadians(125.04 - 1934.136 * julianCycle));
    }

    public static double obliquityOfTheEcliptic(double julianCycle)
    {
        return obliquityOfTheEcliptic(julianCycle, meanObliquityOfTheEcliptic(julianCycle));
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
        return sunDeclination(sunEclipticLongitude(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
    }
    public static double sunRightAscension(double julianCycle)
    {
        return sunRightAscension(sunEclipticLongitude(julianCycle), obliquityOfTheEcliptic(julianCycle), true);
    }

}
