package com.timezone.converter.output.generator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import com.timezone.converter.client.ConverterRegistry;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.datatype.OutputRecord;
import com.timezone.converter.utils.ConverterConstants;
import com.timezone.converter.utils.ConverterLogger;

/**
 * Record converter which will convert input record based on Geo city name
 * (http://download.geonames.org/export/dump/) input provided.
 */
public class GeoConverionStrategy implements ConverterStrategy {

    @Override
    public List<OutputRecord> convert(final List<InputRecord> inputRecords) {
        final List<OutputRecord> outputRecords = new ArrayList<OutputRecord>(inputRecords.size());
        InputRecord record;
        for (InputRecord inputRecord : inputRecords) {
            record = ConverterRegistry.getRegistry().getRecord(inputRecord.getLongitude(), inputRecord.getLatitude());
            try {
                outputRecords.add(new OutputRecord(inputRecord, record.getTimeZone(), getConvertedtime(inputRecord.getInputTime(), record.getTimeZone())));
            } catch (ParseException parseException) {
                ConverterLogger.info("Did not succeed in converting input record due to date in record " + inputRecord);
            }
        }
        return outputRecords;
    }

    private String getConvertedtime(final String inputDateTime, final String timeZoneString) throws ParseException {
        final Date date = ConverterConstants.dateFormat.parse(inputDateTime);
        final TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        final Date newDate = new Date(date.getTime() + timeZone.getRawOffset());
        return ConverterConstants.dateFormat.format(newDate);
    }
}
