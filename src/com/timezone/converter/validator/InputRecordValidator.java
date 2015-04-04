package com.timezone.converter.validator;

import com.timezone.converter.datatype.InputRecord;

/**
 * Validation of input from the user.
 */
public class InputRecordValidator implements InputValidator {

    private static final String RECORD_MATCH_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\s*,\\s*[+-]?\\d+\\.?\\d*\\s*,\\s*[+-]?\\d+\\.?\\d*$";

    /**
     * Longitude can be between +/-180 only, While Latitude can be between +/-90 degrees only.
     */
    @Override
    public boolean isValidRecord(final InputRecord inputRecord) {
        return isValidLatitude(inputRecord.getLatitude()) && isValidLongitude(inputRecord.getLongitude());
    }

    private boolean isValidLatitude(final double latitude) {
        return latitude >= -90. && latitude <= 90.;
    }

    private boolean isValidLongitude(final double longitude) {
        return longitude >= -180. && longitude <= 180.;
    }

    /**
     * Validate that input record should be of fixed form as per problem statement.
     */
    @Override
    public boolean isValidInput(final String inputLine) {
        return inputLine.matches(RECORD_MATCH_REGEX);
    }
}
