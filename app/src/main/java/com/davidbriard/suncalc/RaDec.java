package com.davidbriard.suncalc;

import android.os.Parcel;
import android.os.Parcelable;

public final class RaDec implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public RaDec createFromParcel(Parcel in) {
            return new RaDec(in);
        }
        public RaDec[] newArray(int size) {
            return new RaDec[size];
        }
    };

    public final double declination;
    public final double rightAscension;
    public final double hourAngle;

    public double localSideralTime()
    {
        return hourAngle + rightAscension;
    }

    public RaDec(double dec, double ra, double ha) {
        //az %= 360.0;
        //if (az < 0)
        //   az += 360;
        this.declination = dec;
        this.rightAscension = ra;//Math.max(-90.0D, Math.min(90.0D, el));
        this.hourAngle = ha;

        //if (-180.0D <= az && az < 180.0D) {
        //    this.declination = az;
        //} else {
        //    this.declination = ((az - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D;
        //}
    }

    // Parcelling part
    public RaDec(Parcel in){
        this.declination = in.readDouble();
        this.rightAscension =  in.readDouble();
        this.hourAngle =  in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.declination);
        dest.writeDouble(this.rightAscension);
        dest.writeDouble(this.hourAngle);
    }
/*
    public final int hashCode() {
        long var3 = Double.doubleToLongBits(this.hourAngle);
        long var2 = Double.doubleToLongBits(this.declination);
        int var1 = 31 + (int)(var2 ^ var2 >>> 32);
        var2 = Double.doubleToLongBits(this.rightAscension);
        return var1 * 31 + (int)(var2 ^ var2 >>> 32);
    }
*/
    public final boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (!(var1 instanceof RaDec)) {
            return false;
        } else {
            RaDec var2 = (RaDec)var1;
            return Double.doubleToLongBits(this.declination) == Double.doubleToLongBits(var2.declination)
                    && Double.doubleToLongBits(this.rightAscension) == Double.doubleToLongBits(var2.rightAscension)
                    && Double.doubleToLongBits(this.hourAngle) == Double.doubleToLongBits(var2.hourAngle);
        }
    }

    public final String toString() {
        double var1 = this.declination;
        double var3 = this.rightAscension;
        double var4 = this.hourAngle;
        return (new StringBuilder(60)).append("dec/ra/ha: (").append(var1).append(",").append(var3).append(",").append(var4).append(")").toString();
    }
}