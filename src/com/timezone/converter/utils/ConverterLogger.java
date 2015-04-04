package com.timezone.converter.utils;

public class ConverterLogger {

    /**
     * Just a double for logger api. Not that advanced version of the same.
     * Will print the information on standard output.
     * @param information
     */
    public static void info(final String information) {
        System.out.println(information);
    }

    /**
     * Just a double for logger api. Not that advanced version of the same.
     * Will print the error on standard error.
     * @param information
     */
    public static void error(final String error) {
        System.err.println(error);
    }
}
