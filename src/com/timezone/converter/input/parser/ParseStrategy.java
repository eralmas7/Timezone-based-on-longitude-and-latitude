package com.timezone.converter.input.parser;

import java.util.List;
import com.timezone.converter.datatype.InputRecord;

/**
 * An input parsing strategy
 */
public interface ParseStrategy {

    public List<InputRecord> parseInput(final List<String> inputLine);
}
