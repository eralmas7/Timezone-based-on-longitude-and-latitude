package com.timezone.converter.output.writer;

import java.util.List;
import com.timezone.converter.datatype.OutputRecord;

/**
 * Writes record into the file.
 */
public interface RecordWriter {

    /**
     * Write the final list of records to output file.
     * 
     * @param outputRecord
     */
    public void writeRecord(List<OutputRecord> outputRecord);
}
