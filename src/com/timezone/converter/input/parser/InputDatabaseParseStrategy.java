package com.timezone.converter.input.parser;

import java.util.ArrayList;
import java.util.List;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.utils.ConverterLogger;
import com.timezone.converter.validator.InputValidator;

/**
 * Input parse strategy which will parse the record from data source.
 */
public class InputDatabaseParseStrategy implements ParseStrategy {

    public static final String INPUT_DELIMETER = "\t";
    private final InputValidator inputValidator;
    private final int LONGITUDE_FIELD_INDEX = 5;
    private final int LATITUDE_FIELD_INDEX = 4;

    public InputDatabaseParseStrategy(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    public List<InputRecord> parseInput(final List<String> inputLines) {
        final List<InputRecord> inputRecords = new ArrayList<InputRecord>(inputLines.size());
        InputRecord inputRecord;
        for (String inputLine : inputLines) {
            inputRecord = getRecord(inputLine);
            if (inputValidator.isValidRecord(inputRecord)) {
                inputRecords.add(getRecord(inputLine));
            } else {
                ConverterLogger.info("There is an invalid record which we failed to load. Check longitude/latitude value " + inputRecord);
            }
        }
        return inputRecords;
    }

    private InputRecord getRecord(final String inputLine) {
        final String[] inputTokens = inputLine.split(INPUT_DELIMETER);
        final double longitude = Double.valueOf(inputTokens[LONGITUDE_FIELD_INDEX]);
        final double latitude = Double.valueOf(inputTokens[LATITUDE_FIELD_INDEX]);
        final String timezone = inputTokens[inputTokens.length - 2];
        return new InputRecord(latitude, longitude, timezone);
    }
}
