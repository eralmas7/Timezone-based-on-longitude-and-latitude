package com.timezone.converter.input.parser;

import java.util.ArrayList;
import java.util.List;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.utils.ConverterLogger;
import com.timezone.converter.validator.InputValidator;

/**
 * Input from user which will be parsed into input record format.
 */
public class InputParseStrategy implements ParseStrategy {

    public static final String INPUT_DELIMETER = "\\s?,\\s?";
    private final InputValidator inputValidator;
    private final int LONGITUDE_FIELD_INDEX = 2;
    private final int LATITUDE_FIELD_INDEX = 1;
    private final int GMT_TIME_FIELD_INDEX = 0;

    public InputParseStrategy(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    public List<InputRecord> parseInput(final List<String> inputLines) {
        final List<InputRecord> inputRecords = new ArrayList<InputRecord>(inputLines.size());
        InputRecord inputRecord;
        for (String inputLine : inputLines) {
            if (inputValidator.isValidInput(inputLine)) {
                inputRecord = getRecord(inputLine);
                if (inputValidator.isValidRecord(inputRecord)) {
                    inputRecords.add(getRecord(inputLine));
                } else {
                    ConverterLogger.info("There is an invalid record which we faield to load. Check longitude/latitude value " + inputRecord);
                }
            } else {
                ConverterLogger.info("Line " + inputLine + " seems to be invalid. Ignoring for now");
            }
        }
        return inputRecords;
    }

    private InputRecord getRecord(final String inputLine) {
        final String[] inputTokens = inputLine.split(INPUT_DELIMETER);
        final double longitude = Double.valueOf(inputTokens[LONGITUDE_FIELD_INDEX]);
        final double latitude = Double.valueOf(inputTokens[LATITUDE_FIELD_INDEX]);
        final String gmtDateTime = inputTokens[GMT_TIME_FIELD_INDEX];
        return new InputRecord(gmtDateTime, latitude, longitude);
    }
}
