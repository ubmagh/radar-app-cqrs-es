package me.ubmagh.commonapi.exceptions;

public class NegativeSpeedException extends InputValidationException {
    private Float vitesse;

    public NegativeSpeedException(String message, Float speed) {
        super(message);
        vitesse = speed;
    }

    public Float getVitesse() {
        return vitesse;
    }
}
