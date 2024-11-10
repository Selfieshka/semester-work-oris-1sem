package ru.kpfu.itis.kirillakhmetov.exception;

public class GetConfigInfoException extends Exception {
    public GetConfigInfoException() {
        super();
    }

    public GetConfigInfoException(String message) {
        super(message);
    }

    public GetConfigInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetConfigInfoException(Throwable cause) {
        super(cause);
    }
}
