package com.timezone.converter.exception;

public class ConversionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a ConversionException with the specified detailed message.
     * 
     * @param message the detail message.
     */
    public ConversionException(final String message) {
        super(message);
    }

    /**
     * Constructs a ConversionException with the specified detailed message and cause of the same.
     * 
     * @param message - detail exception message
     * @param throwable - cause of exception
     */
    public ConversionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
