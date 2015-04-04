package com.timezone.converter.exception;

public class OutputWriterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a InputReaderException with the specified detailed message.
     * 
     * @param message the detail message.
     */
    public OutputWriterException(final String message) {
        super(message);
    }

    /**
     * Constructs a InputReaderException with the specified detailed message and cause of the same.
     * 
     * @param message - detail exception message
     * @param throwable - cause of exception
     */
    public OutputWriterException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
