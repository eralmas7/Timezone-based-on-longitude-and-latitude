package com.timezone.converter.output.generator;

import java.util.List;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.datatype.OutputRecord;

/**
 * Strategy to convert the input record to output record.
 */
public interface ConverterStrategy {

    /**
     * Given an input record, convert it into output with other additional information.
     * @param inputRecords
     * @return
     */
    public List<OutputRecord> convert(final List<InputRecord> inputRecords);
}
