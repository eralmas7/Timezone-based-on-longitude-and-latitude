package com.timezone.converter.client;

import java.util.List;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.datatype.OutputRecord;
import com.timezone.converter.input.parser.ParseStrategy;
import com.timezone.converter.input.reader.RecordReader;
import com.timezone.converter.output.generator.ConverterStrategy;
import com.timezone.converter.output.writer.RecordWriter;

public class ConverterClient implements Converter {

    private final RecordReader recordReader;
    private final RecordWriter recordWriter;
    private final ParseStrategy parseStrategy;
    private final ConverterStrategy converterStrategy;

    public ConverterClient(final RecordReader recordReader, final RecordWriter recordWriter, final ParseStrategy parseStrategy, final ConverterStrategy converterStrategy, final RegistryLoader registryLoader) {
        this.recordReader = recordReader;
        this.recordWriter = recordWriter;
        this.parseStrategy = parseStrategy;
        this.converterStrategy = converterStrategy;
        registryLoader.loadRegistry();
    }

    public void convert() {
        final List<String> inputLines = recordReader.readLines();
        final List<InputRecord> inputRecords = parseStrategy.parseInput(inputLines);
        final List<OutputRecord> outputRecords = converterStrategy.convert(inputRecords);
        recordWriter.writeRecord(outputRecords);
    }
}
