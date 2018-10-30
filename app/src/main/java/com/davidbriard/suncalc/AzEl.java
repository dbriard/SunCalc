package com.davidbriard.suncalc;

import android.os.Parcel;
import android.os.Parcelable;

public final class AzEl implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AzEl createFromParcel(Parcel in) {
            return new AzEl(in);
        }

        public AzEl[] newArray(int size) {
            return new AzEl[size];
        }
    };

    public final double azimuth;
    public final double elevation;

    public AzEl(double az, double el) {
        if (-180.0D <= az && az < 180.0D) {
            this.azimuth = az;
        } else {
            this.azimuth = ((az - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D;
        }

        this.elevation = Math.max(-90.0D, Math.min(90.0D, el));
    }

    // Parcelling part
    public AzEl(Parcel in){
        this.azimuth = in.readDouble();
        this.elevation =  in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.azimuth);
        dest.writeDouble(this.elevation);
    }

    public final int hashCode() {
        long var2 = Double.doubleToLongBits(this.azimuth);
        int var1 = 31 + (int)(var2 ^ var2 >>> 32);
        var2 = Double.doubleToLongBits(this.elevation);
        return var1 * 31 + (int)(var2 ^ var2 >>> 32);
    }

    public final boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (!(var1 instanceof AzEl)) {
            return false;
        } else {
            AzEl var2 = (AzEl)var1;
            return Double.doubleToLongBits(this.azimuth) == Double.doubleToLongBits(var2.azimuth) && Double.doubleToLongBits(this.elevation) == Double.doubleToLongBits(var2.elevation);
        }
    }

    public final String toString() {
        double var1 = this.azimuth;
        double var3 = this.elevation;
        return (new StringBuilder(60)).append("az/el: (").append(var1).append(",").append(var3).append(")").toString();
    }
}