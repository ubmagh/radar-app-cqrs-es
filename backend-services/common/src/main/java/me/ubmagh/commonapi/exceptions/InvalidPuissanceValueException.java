package me.ubmagh.commonapi.exceptions;

public class InvalidPuissanceValueException extends InputValidationException {

    private int puissance;
    public InvalidPuissanceValueException(String message, int puissance ){
        super(message);
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }
}
