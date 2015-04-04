package com.timezone.converter.datatype;

import com.timezone.converter.utils.ConverterConstants;

/**
 * Output that we want to transform input into.
 */
public class OutputRecord {

    private final InputRecord inputRecord;
    private final String outputTime;

    public OutputRecord(final InputRecord inputRecord, final String timeZone, final String outputTime) {
        this.inputRecord = inputRecord;
        this.outputTime = outputTime;
        this.inputRecord.setTimeZone(timeZone);
    }

    public InputRecord getInputData() {
        return inputRecord;
    }

    public String getOutputTime() {
        return outputTime;
    }

    /**
     * This is what is going to be written onto output file.
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputRecord);
        stringBuilder.append(ConverterConstants.COMMA);
        stringBuilder.append(inputRecord.getTimeZone());
        stringBuilder.append(ConverterConstants.COMMA);
        stringBuilder.append(outputTime);
        return stringBuilder.toString();
    }
}
