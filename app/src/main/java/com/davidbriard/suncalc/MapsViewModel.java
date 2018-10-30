package com.davidbriard.suncalc;

//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class MapsViewModel extends ObservableViewModel {

    private DateTime mDate;
    private LatLng mLocation;
    private DateTimeZone mTimeZone;

    public MapsViewModel() {
        mDate = new DateTime(2018, 10, 30, 10, 0, 0, 0);
        mLocation = new LatLng(48.86, 2.34);
        mTimeZone = DateTimeZone.getDefault();
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
    public double getSunAzimuth() { return AstroUtils.sunCoords(getDate(), getLatitude(), getLongitude(), 0 ).azimuth; }

    @Bindable
    @DependsOnField(fieldIds = {BR.date, BR.latitude, BR.longitude})
    public double getSunElevation() { return AstroUtils.sunCoords(getDate(), getLatitude(), getLongitude(), 0 ).elevation; }

}
