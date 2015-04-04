package com.timezone.converter.datatype;

import com.timezone.converter.utils.ConverterConstants;

/**
 * Input record to deal with which we get from user.
 */
public class InputRecord {

    private final String inputTime;
    private final double latitude;
    private final double longitude;
    private String timeZone;

    public InputRecord(final String inputTime, final double latitude, final double longitude) {
        this.inputTime = inputTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public InputRecord(final String inputTime, final double latitude, final double longitude, final String timezone) {
        this(inputTime, latitude, longitude);
        this.timeZone = timezone;
    }

    public InputRecord(final double latitude, final double longitude, final String timezone) {
        this(null, latitude, longitude, timezone);
    }

    public String getInputTime() {
        return inputTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    /*
     * Setter for timeZone since its an optional field.
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputTime);
        stringBuilder.append(ConverterConstants.COMMA);
        stringBuilder.append(latitude);
        stringBuilder.append(ConverterConstants.COMMA);
        stringBuilder.append(longitude);
        return stringBuilder.toString();
    }
}
