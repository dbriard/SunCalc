package com.davidbriard.suncalc;

//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;

public class MapsViewModel extends ObservableViewModel {

    private DateTime mDate;
    private LatLng mLocation;

    public MapsViewModel() {
        mDate = DateTime.now();
        mLocation = new LatLng(-34, 151);
        firstName = "David";
    }

    private String firstName;

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    @DependsOnField(name = BR.longitude, fieldIds = {BR.location})
    public double getLongitude()
    {
        return mLocation.longitude;
    }

    @Bindable
    @DependsOnField(name = BR.latitude, fieldIds = {BR.location})
    public double getLatitude()
    {
        return mLocation.latitude;
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
    @DependsOnField(name = BR.julianDay, fieldIds = {BR.date})
    public double getJulianDay() {
        return AstroUtils.julianDay(mDate);
    }

    @Bindable
    @DependsOnField(name = BR.julianCenturies, fieldIds = {BR.date})
    public double getJulianCenturies() {
        return AstroUtils.julianDay(mDate);
    }

    @Bindable
    @DependsOnField(name = BR.obliquityOfTheEcliptic, fieldIds = {BR.julianCenturies})
    public double getObliquityOfTheEcliptic() { return AstroUtils.obliquityOfTheEcliptic(getJulianCenturies()); }

    @Bindable
    @DependsOnField(name = BR.sunEclipticLongitude, fieldIds = {BR.julianCenturies})
    public double getSunEclipticLongitude() { return AstroUtils.sunEclipticLongitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(name = BR.sunEclipticLatitude, fieldIds = {BR.julianCenturies})
    public double getSunEclipticLatitude() { return AstroUtils.sunEclipticLatitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(name = BR.moonEclipticLongitude, fieldIds = {BR.julianCenturies})
    public double getMoonEclipticLongitude() { return AstroUtils.moonEclipticLongitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(name = BR.moonEclipticLatitude, fieldIds = {BR.julianCenturies})
    public double getMoonEclipticLatitude() { return AstroUtils.moonEclipticLatitude(getJulianCenturies()); }

    @Bindable
    @DependsOnField(name = BR.moonPhaseAngle, fieldIds = {BR.moonEclipticLongitude, BR.sunEclipticLongitude})
    public double getMoonPhaseAngle() { return AstroUtils.moonPhaseAngle(getMoonEclipticLongitude(), getSunEclipticLongitude()); }

    @Bindable
    @DependsOnField(name = BR.moonDeclination, fieldIds = {BR.moonEclipticLongitude, BR.moonEclipticLatitude, BR.obliquityOfTheEcliptic})
    public double getMoonDeclination() { return AstroUtils.moonDeclination(getMoonEclipticLatitude(), getMoonEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(name = BR.moonRightAscension, fieldIds = {BR.moonEclipticLongitude, BR.moonEclipticLatitude, BR.obliquityOfTheEcliptic})
    public double getMoonRightAscension() { return AstroUtils.moonRightAscension(getMoonEclipticLatitude(), getMoonEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(name = BR.sunDeclination, fieldIds = {BR.sunEclipticLongitude, BR.obliquityOfTheEcliptic})
    public double getSunDeclination() { return AstroUtils.sunDeclination(getSunEclipticLongitude(), getObliquityOfTheEcliptic(), true); }

    @Bindable
    @DependsOnField(name = BR.sunRightAscension, fieldIds = {BR.sunEclipticLongitude, BR.obliquityOfTheEcliptic})
    public double getSunRightAscension() { return AstroUtils.sunRightAscension(getSunEclipticLongitude(), getObliquityOfTheEcliptic(), true); }
}
