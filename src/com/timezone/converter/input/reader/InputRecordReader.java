package com.timezone.converter.input.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.timezone.converter.exception.InputReaderException;

/**
 * Actual file reader which will read the data as a set of lines from user.
 */
public class InputRecordReader implements RecordReader {

    private final File file;

    public InputRecordReader(final String fileName) {
        file = new File(fileName);// could try reading from system property or property file instead of injecting!
    }

    public List<String> readLines() {
        final List<String> inputLineList = new LinkedList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                inputLineList.add(currentLine);
            }
        } catch (IOException ioException) {
            throw new InputReaderException("Exception while reading the file ", ioException);
        }
        return inputLineList;
    }
}
