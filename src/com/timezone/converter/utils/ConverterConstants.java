package com.timezone.converter.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConverterConstants {

    public static final String COMMA = ",";
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String GOOGLE_REST_BASE_URL = "https://maps.googleapis.com/maps/api/timezone/json?location=";
}
