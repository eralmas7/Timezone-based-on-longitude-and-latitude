package com.timezone.converter.input.reader;

import java.util.List;

/**
 * Actor which will read input from a file.
 */
public interface RecordReader {

    /**
     * Read lines from data source and get the input string line in a list.
     * @return list of input strings
     */
    public List<String> readLines();
}
