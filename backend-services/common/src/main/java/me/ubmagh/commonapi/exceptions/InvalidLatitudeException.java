package me.ubmagh.commonapi.exceptions;

public class InvalidLatitudeException extends RadarValidationException {
    private Float latitude;

    public InvalidLatitudeException(String s, Float latitude) {
        super(s);
        this.latitude = latitude;
    }

    public Float getLatitude() {
        return latitude;
    }
}
