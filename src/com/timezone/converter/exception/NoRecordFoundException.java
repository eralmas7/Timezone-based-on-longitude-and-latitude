package com.timezone.converter.exception;

public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a NoRecordFoundException with the specified detailed message.
     * 
     * @param message the detail message.
     */
    public NoRecordFoundException(final String message) {
        super(message);
    }

    /**
     * Constructs a NoRecordFoundException with the specified detailed message and cause of the
     * same.
     * 
     * @param message - detail exception message
     * @param throwable - cause of exception
     */
    public NoRecordFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
