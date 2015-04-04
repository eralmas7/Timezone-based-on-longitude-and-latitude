package com.timezone.converter.output.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.timezone.converter.client.Converter;
import com.timezone.converter.client.ConverterClient;
import com.timezone.converter.client.RegistryLoader;
import com.timezone.converter.input.parser.InputDatabaseParseStrategy;
import com.timezone.converter.input.parser.InputParseStrategy;
import com.timezone.converter.input.parser.ParseStrategy;
import com.timezone.converter.input.reader.InputRecordReader;
import com.timezone.converter.input.reader.RecordReader;
import com.timezone.converter.output.writer.OutputRecordWriter;
import com.timezone.converter.output.writer.RecordWriter;
import com.timezone.converter.validator.InputRecordValidator;
import com.timezone.converter.validator.InputValidator;

public class GeoCoversionStratgyTest {

    public static final String INPUT_GEO_LOCATION = "src/resource/cities15000.txt";
    public static final String INPUT_TEST_CASE_LOCATION = "test/resource/input.txt";
    public static final String OUTPUT_RESULT_LOCATION = "dist/output/output.txt";
    private Converter converter;

    @Before
    public void setUp() {
        // inject dependencies can use Spring/Guice etc
        final RecordReader recordReader = new InputRecordReader(INPUT_TEST_CASE_LOCATION);
        final RecordWriter recordWriter = new OutputRecordWriter(OUTPUT_RESULT_LOCATION);
        final InputValidator inputValidator = new InputRecordValidator();
        final ParseStrategy parseStrategy = new InputParseStrategy(inputValidator);
        final ConverterStrategy converterStrategy = new GeoConverionStrategy();
        final RecordReader fileRecordReader = new InputRecordReader(INPUT_GEO_LOCATION);
        final ParseStrategy parseFileStrategy = new InputDatabaseParseStrategy(inputValidator);
        final RegistryLoader registryLoader = new RegistryLoader(fileRecordReader, parseFileStrategy);
        converter = new ConverterClient(recordReader, recordWriter, parseStrategy, converterStrategy, registryLoader);//we can inject converterStrategy bean from a factory may be which will say whether we want google one or GEO!
    }

    @Test
    public void testWithValidInput() {// could test with failure scenario as well
        converter.convert();
        try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_RESULT_LOCATION))) {
            String currentLine;
            int count = 0;
            while ((currentLine = br.readLine()) != null) {
                Assert.assertEquals("2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10 14:52:49", currentLine);
                count++;
            }
            Assert.assertEquals(1, count);
        } catch (IOException e) {
            Assert.fail("Did not expected any exception " + e);
        }
    }

    @After
    public void tearDown() {
        final File file = new File(OUTPUT_RESULT_LOCATION);
        file.delete();
    }
}
