package com.davidbriard.suncalc;

import com.google.android.gms.maps.model.LatLng;

public class SpatialUtils {

    /// <summary>
    /// Retrieves the radius of the earth in a specific distance unit for WGS84. Defaults unit is in Meters.
    /// </summary>
    /// <param name="units">Unit of distance measurement</param>
    /// <returns>A double that represents the radius of the earth in a specific distance unit. Defaults unit is in KM's.</returns>
    public static double earthRadius(DistanceUnits units)
    {
        switch (units)
        {
            case Feet:
                return EarthRadius.Feet;
            case Meters:
                return EarthRadius.Meters;
            case Miles:
                return EarthRadius.Miles;
            case Yards:
                return convertDistance(EarthRadius.KM, DistanceUnits.KM, DistanceUnits.Yards);
            case KM:
            default:
                return EarthRadius.KM;
        }
    }

    /// <summary>
    /// Converts a distance from distance unit to another.
    /// </summary>
    /// <param name="distance">a double that represents the distance.</param>
    /// <param name="fromUnits">The distance unit the original distance is in.</param>
    /// <param name="toUnits">The desired distance unit to convert to.</param>
    /// <returns>A distance in the new units.</returns>
    public static double convertDistance(double distance, DistanceUnits fromUnits, DistanceUnits toUnits)
    {
        //Convert the distance to kilometers
        switch (fromUnits)
        {
            case Meters:
                distance /= 1000;
                break;
            case Feet:
                distance /= 3288.839895;
                break;
            case Miles:
                distance *= 1.609344;
                break;
            case Yards:
                distance *= 0.0009144;
                break;
            case KM:
                break;
        }

        //Convert from kilometers to output distance unit
        switch (toUnits)
        {
            case Meters:
                distance *= 1000;
                break;
            case Feet:
                distance *= 5280;
                break;
            case Miles:
                distance /= 1.609344;
                break;
            case Yards:
                distance *= 1093.6133;
                break;
            case KM:
                break;
        }

        return distance;
    }

    /// <summary>
    /// Converts a decimal degree into a string in the format of days minutes seconds
    /// </summary>
    /// <param name="degree">Decimal degree</param>
    /// <param name="isLatitude">Boolean specifying if the degree is a latitude coordinate</param>
    /// <returns>A string version of an angle in days, minutes, seconds format.</returns>
    public static String toDMS(double degree, boolean isLatitude)
    {
        String orientation = "";

        if (isLatitude)
        {
            if (degree < 0)
            {
                orientation += "S";
                degree *= -1;
            }
            else
                orientation += "N";
        }
        else
        {
            if (degree < 0)
            {
                orientation += "W";
                degree *= -1;
            }
            else
            {
                orientation += "E";
            }
        }

        int day = (int)degree;
        int min = (int)((degree - day) * 60);
        double sec = (degree - (double)day - (double)min / 60) * 3600;

        return String.format("%s %d° %d' %.3f\"", orientation, day, min, sec);
    }
    /// <summary>
    /// Converts a days minutes seconds coordinate into a decimal degree's coordinate
    /// </summary>
    /// <param name="degree">Degree coordinate</param>
    /// <param name="minute">Minute coordinate</param>
    /// <param name="second">Second coordinate</param>
    /// <returns>A decimal degree coordinate</returns>
    public static double toDegrees(double degree, double minute, double second)
    {
        return degree + (minute / 60) + (second / 3600);
    }

    /// <summary>
    /// Calculate the distance between two coordinates on the surface of a sphere (Earth).
    /// </summary>
    /// <param name="origin">First coordinate to calculate distance between</param>
    /// <param name="destination">Second coordinate to calculate distance between</param>
    /// <param name="units">Unit of distance measurement</param>
    /// <returns>The shortest distance in the specifed units</returns>
    public static double haversineDistance(LatLng origin, LatLng destination, DistanceUnits units)
    {
        double radius = earthRadius(units);

        double dLat = Math.toRadians(destination.latitude - origin.latitude);
        double dLon = Math.toRadians(destination.longitude - origin.longitude);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.cos(Math.toRadians(origin.latitude)), 2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radius * c;
    }
    /// <summary>
    /// Calculates the heading from one LatLng to another.
    /// </summary>
    /// <param name="origin">Point of origin.</param>
    /// <param name="destination">Destination point to calculate relative heading to.</param>
    /// <returns>A heading degrees between 0 and 360. 0 degrees points due North.</returns>
    public static double heading(LatLng origin, LatLng destination)
    {
        double radianLat1 = Math.toRadians(origin.latitude);
        double radianLat2 = Math.toRadians(destination.latitude);

        double dLon = Math.toRadians(destination.longitude - origin.longitude);

        double dy = Math.sin(dLon) * Math.cos(radianLat2);
        double dx = Math.cos(radianLat1) * Math.sin(radianLat2) - Math.sin(radianLat1) * Math.cos(radianLat2) * Math.cos(dLon);

        return (Math.toDegrees(Math.atan2(dy, dx)) + 360) % 360;
    }

/*
    public static double CalculateRelativeElevation(LatLng origin, LatLng destination)
    {
        double h = destination.Altitude - origin.Altitude;
        double dist = HaversineDistance(origin, destination, DistanceUnits.Meters);
        double angle = Math.atan2(h, dist);
        return ToDegrees(angle);// (ToDegrees(angle) + 360) % 360;
    }
*/

    /// <summary>
    /// Calculates a destination coordinate based on a starting coordinate, a bearing, a distance, and a distance unit type.
    /// </summary>
    /// <param name="origin">LatLng that the destination is relative to</param>
    /// <param name="bearing">A bearing (heading) angle between 0 - 360 degrees. 0 - North, 90 - East, 180 - South, 270 - West</param>
    /// <param name="distance">Distance that destination is away</param>
    /// <param name="units">Unit of distance measurement</param>
    /// <returns>A coordinate that is the specified distance away from the origin</returns>
    public static LatLng destinationCoordinate(LatLng origin, double bearing, double distance, DistanceUnits units)
    {
        double radius = earthRadius(units);

        //convert latitude, longitude and heading into radians
        double latitudeRad = Math.toRadians(origin.latitude);
        double longitudeRad = Math.toRadians(origin.longitude);
        double bearingRad = Math.toRadians(bearing);

        double centralAngle = distance / radius;
        double destinationLatitudeRad = Math.asin(Math.sin(latitudeRad) * Math.cos(centralAngle) + Math.cos(latitudeRad) * Math.sin(centralAngle) * Math.cos(bearingRad));
        double destinationLongitudeRad = longitudeRad + Math.atan2(Math.sin(bearingRad) * Math.sin(centralAngle) * Math.cos(latitudeRad), Math.cos(centralAngle) - Math.sin(latitudeRad) * Math.sin(destinationLatitudeRad));

        return new LatLng(Math.toDegrees(destinationLatitudeRad), Math.toDegrees(destinationLongitudeRad));
    }

/*
    public static LatLng destinationCoordinate(LatLng origin, double bearing, double elevation, double distance, DistanceUnits units)
    {
        var radius = GetEarthRadius(units);

        //convert latitude, longitude and heading into radians
        double latitudeRad = ToRadians(origin.Latitude);
        double longitudeRad = ToRadians(origin.Longitude);
        double bearingRad = ToRadians(bearing);
        double pitchRad = ToRadians(elevation);

        double centralAngle = (distance * Math.cos(pitchRad)) / radius;
        double destinationLatitudeRad = Math.Asin(Math.sin(latitudeRad) * Math.cos(centralAngle) + Math.cos(latitudeRad) * Math.sin(centralAngle) * Math.cos(bearingRad));
        double destinationLongitudeRad = longitudeRad + Math.atan2(Math.sin(bearingRad) * Math.sin(centralAngle) * Math.cos(latitudeRad), Math.cos(centralAngle) - Math.sin(latitudeRad) * Math.sin(destinationLatitudeRad));

        return new LatLng { Latitude = ToDegrees(destinationLatitudeRad), Longitude = ToDegrees(destinationLongitudeRad),
            Altitude = distance * Math.sin(pitchRad)
    };
*/

    /// <summary>
    /// The approximate spherical radius of the Earth
    /// NOTE: In reality the Earth is an ellipsoid.
    /// </summary>
    static class EarthRadius
    {
        /// <summary>
        /// Earth Radius in Kilometers
        /// </summary>
        public static final double KM = 6378.135;

        /// <summary>
        /// Earth Radius in Meters
        /// </summary>
        public static final double Meters = 6378135;

        /// <summary>
        /// Earth Radius in Miles
        /// </summary>
        public static final double Miles = 3963.189;

        /// <summary>
        /// Earth Radius in Feet
        /// </summary>
        public static final double Feet = 20925640;
    }
}
