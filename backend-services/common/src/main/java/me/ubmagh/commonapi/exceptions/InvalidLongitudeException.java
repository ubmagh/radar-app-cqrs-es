package me.ubmagh.commonapi.exceptions;

public class InvalidLongitudeException extends RadarValidationException {
    private Float longitude;

    public InvalidLongitudeException(String s, Float longitude) {
        super(s);
        this.longitude = longitude;
    }

    public Float getLongitude() {
        return longitude;
    }
}
