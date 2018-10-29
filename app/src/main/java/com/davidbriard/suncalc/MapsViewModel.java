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
        return AstroUtils.julianDay (mDate);
    }
}
