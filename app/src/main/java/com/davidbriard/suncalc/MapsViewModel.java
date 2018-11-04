package com.davidbriard.suncalc;

//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class MapsViewModel extends ObservableViewModel {

    private DateTime mDate;
    private LatLng mLocation;
    private DateTimeZone mTimeZone;

    /*public static List<String> GetZones(String country) {
        List<String> zones = new ArrayList<>();

        for (String i : DateTimeZone.getAvailableIDs()) {
            if (i.startsWith(country)) {
                zones.add(i);
            }
        }
        return zones;
    }*/
    public MapsViewModel() {
        //mDate = new DateTime(1991, 5, 19, 14    ,0,0,0);
        mDate = new DateTime(2018, 10, 30, 10, 0, 0, 0);
        mLocation = new LatLng(48.86, 2.34);
        mTimeZone = DateTimeZone.getDefault();

       // List<String> zones = GetZones("France");

    //    AzEl moon = AstroUtils.moonCoords(mDate, 48.86, 2.34, 1);
    }

    @Bindable
    public LatLng getLocation() {
        return mLocation;
    }

    public void setLocation(LatLng location) {
        this.mLocation = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public DateTime getDate() {
        return mDate;
    }

    public void setDate(DateTime date) {
        this.mDate = date;
        notifyPropertyChanged(BR.date);
    }
    @Bindable
    public DateTimeZone getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(DateTimeZone tz) {
        this.mTimeZone = tz;
        notifyPropertyChanged(BR.timeZone);
    }

    @Bindable
    @DependsOnField(fieldIds = {BR.location})
    public double getLongitude()
    {
        return mLocation.longitude;
    }

    @Bindable
    @DependsOnField(fieldIds = {BR.location})
    public double getLatitude()
    {
        return mLocation.latitude;
    }

    @Bindable
    @DependsOnField(fieldIds = {BR.date})
    public double getJulianDay() {
        return AstroUtils.julianDay(mDate);
    }

    @Bindable
    @DependsOnField(fieldIds = {BR.date})
    public double getJulianCenturies() {
        return AstroUtils.julianCenturies(mDate);
    }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getObliquityOfTheEcliptic() { return AstroUtils.obliquityOfTheEcliptic(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getSunEclipticLongitude() { return AstroUtils.sunEclipticLongitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getSunEclipticLatitude() { return AstroUtils.sunEclipticLatitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getMoonEclipticLongitude() { return AstroUtils.moonEclipticLongitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getMoonEclipticLatitude() { return AstroUtils.moonEclipticLatitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.moonEclipticLongitude, BR.sunEclipticLongitude})
    public double getMoonPhaseAngle() { return AstroUtils.moonPhaseAngle(getMoonEclipticLongitude(), getSunEclipticLongitude()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.moonEclipticLongitude, BR.moonEclipticLatitude, BR.obliquityOfTheEcliptic})
    public double getMoonDeclination() { return AstroUtils.moonDeclination(getMoonEclipticLatitude(), getMoonEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(fieldIds = {BR.moonEclipticLongitude, BR.moonEclipticLatitude, BR.obliquityOfTheEcliptic})
    public double getMoonRightAscension() { return AstroUtils.moonRightAscension(getMoonEclipticLatitude(), getMoonEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(fieldIds = {BR.sunEclipticLongitude, BR.obliquityOfTheEcliptic})
    public double getSunDeclination() { return AstroUtils.sunDeclination(getSunEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(fieldIds = {BR.sunEclipticLongitude, BR.obliquityOfTheEcliptic})
    public double getSunRightAscension() { return AstroUtils.sunRightAscension(getSunEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getSunGeocentricDistance() { return AstroUtils.sunGeocentricDistance(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getMoonGeocentricDistance() { return AstroUtils.moonGeocentricDistance(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getSunGeocentricDistanceRatioOfTheAverage() { return AstroUtils.sunGeocentricDistanceRatioOfTheAverage(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getMoonGeocentricDistanceRatioOfTheAverage() { return AstroUtils.moonGeocentricDistanceRatioOfTheAverage(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.sunGeocentricDistance})
    public double getSunAngularDiameter() { return AstroUtils.sunAngularDiameter(getSunGeocentricDistance()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.moonGeocentricDistance})
    public double getMoonAngularDiameter() { return AstroUtils.moonAngularDiameter(getMoonGeocentricDistance()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public double getSunAzimuth() { return AstroUtils.sunCoords(getDate(), getLatitude(), getLongitude(), 1 ).azimuth; }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public double getSunElevation() { return AstroUtils.sunCoords(getDate(), getLatitude(), getLongitude(), 1 ).elevation; }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public double getMoonAzimuth() { return AstroUtils.moonCoords(getDate(), getLatitude(), getLongitude(), 1 ).azimuth; }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public double getMoonElevation() { return AstroUtils.moonCoords(getDate(), getLatitude(), getLongitude(), 1 ).elevation; }

    @Bindable
    @DependsOnField(fieldIds = {BR.julianCenturies})
    public double getEqOfTime() { return AstroUtils.equationOfTime(getJulianCenturies()); }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public DateTime getSunrise() { return AstroUtils.sunrise(getDate(), getLatitude(), getLongitude(), 1 ); }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public DateTime getSunset() { return AstroUtils.sunset(getDate(), getLatitude(), getLongitude(), 1 ); }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public DateTime getMoonrise() { return AstroUtils.moonrise(getDate(), getLatitude(), getLongitude(), 4 ); }

    @Bindable
    @DependsOnField(fieldIds = {BR.sunrise})
    public String getSunriseTime() { return getSunrise().toLocalTime().toString() ; }

    @Bindable
    @DependsOnField(fieldIds = {BR.moonrise})
    public String getMoonriseTime() { return getMoonrise().toLocalTime().toString() ; }

    @Bindable
    @DependsOnField(fieldIds = {BR.sunrise})
    public String getSunsetTime() { return getSunset().toLocalTime().toString() ; }
}
