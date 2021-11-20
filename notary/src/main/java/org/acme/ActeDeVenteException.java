package org.acme;

public class ActeDeVenteException extends Throwable {
    private String exception;
    private  long number;

    public ActeDeVenteException(String exception, long number) {
        this.exception = exception;
        this.number = number;
    }

    public ActeDeVenteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exeption, long number) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exception = exeption;
        this.number = number;
    }

    public ActeDeVenteException() {

    }
}
