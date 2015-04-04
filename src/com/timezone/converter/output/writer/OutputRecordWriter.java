package com.timezone.converter.output.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.timezone.converter.datatype.OutputRecord;
import com.timezone.converter.exception.OutputWriterException;

/**
 * Writer to write into output file.
 */
public class OutputRecordWriter implements RecordWriter {

    private final File file;

    public OutputRecordWriter(String fileName) {
        this.file = new File(fileName);// can change to drive from properties file if needed
    }

    @Override
    public void writeRecord(List<OutputRecord> outputRecords) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (OutputRecord outputRecord : outputRecords) {
                bufferedWriter.write(outputRecord.toString());
            }
        } catch (IOException e) {
            throw new OutputWriterException("Exception while writing to the file ", e);
        }
    }
}
