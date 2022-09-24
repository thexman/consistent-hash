package com.a9ski.utils.hash.consistent.exceptions;

public class KeyAlreadyExists extends Exception {
    public KeyAlreadyExists() {
        super();
    }

    public KeyAlreadyExists(String message) {
        super(message);
    }

    public KeyAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected KeyAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
