package com.timezone.converter.validator;

import com.timezone.converter.datatype.InputRecord;

/**
 * Validator for conversion logic to validate input.
 */
public interface InputValidator {

    /**
     * Is input record valid as per longitude and latitude range
     * @param inputRecord
     * @return whether input record is valid or not
     */
    public boolean isValidRecord(InputRecord inputRecord);

    /**
     * Is input line valid from the user.
     * @param inputLine
     * @return whether input record is valid or not
     */
    public boolean isValidInput(String inputLine);
}
