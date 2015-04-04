package com.timezone.converter.output.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.datatype.OutputRecord;
import com.timezone.converter.exception.ConversionException;
import com.timezone.converter.utils.ConverterConstants;
import com.timezone.converter.utils.ConverterLogger;

/**
 * Record Converter which will convert the record based on Google rest call.
 */
public class GoogleRestConverionStrategy implements ConverterStrategy {

    private final static String JSON_TOKEN = "\"timeZoneId\" : \"";

    @Override
    public List<OutputRecord> convert(final List<InputRecord> inputRecords) {
        final List<OutputRecord> outputRecords = new ArrayList<OutputRecord>(inputRecords.size());
        for (InputRecord inputRecord : inputRecords) {
            try {
                outputRecords.add(getOutputRecord(inputRecord));
            } catch (ConversionException conversionException) {
                ConverterLogger.info("Did not succeed in converting input record due to " + inputRecord + " exception is " + conversionException.getMessage());
            }
        }
        return outputRecords;
    }

    private OutputRecord getOutputRecord(final InputRecord inputRecord, final String jsonData) throws ParseException {
        String timeZoneString = jsonData.substring(jsonData.indexOf(JSON_TOKEN) + JSON_TOKEN.length());
        timeZoneString = timeZoneString.substring(0, timeZoneString.indexOf("\","));
        final TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        final Date date = ConverterConstants.dateFormat.parse(inputRecord.getInputTime());
        final Date newDate = new Date(date.getTime() + timeZone.getRawOffset());
        final OutputRecord outputRecord = new OutputRecord(inputRecord, timeZoneString, ConverterConstants.dateFormat.format(newDate));
        return outputRecord;
    }

    private HttpURLConnection getHttpURLConnection(final InputRecord inputRecord) throws Exception {
        final Date date = ConverterConstants.dateFormat.parse(inputRecord.getInputTime());
        final String basicUrl = ConverterConstants.GOOGLE_REST_BASE_URL + inputRecord.getLatitude() + "," + inputRecord.getLongitude() + "&timestamp=" + date.getTime() / 1000;
        final URL url = new URL(basicUrl);// limitation on usage of this call from Google!
        final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();// we could use connection pooling and a better client like RestClient or HttpClient
        urlConnection.setConnectTimeout(1000);
        urlConnection.setReadTimeout(1000);
        return urlConnection;
    }

    private OutputRecord getOutputRecord(final InputRecord inputRecord) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = getHttpURLConnection(inputRecord);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                final StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                final String jsonData = stringBuilder.toString();// Gson or Jackson can be used to
                                                                 // parse input
                return getOutputRecord(inputRecord, jsonData);
            } catch (IOException | ParseException exception) {
                throw new ConversionException(exception.getMessage(), exception);
            }
        } catch (Exception exception) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            throw new ConversionException(exception.getMessage(), exception);
        }
    }
}
