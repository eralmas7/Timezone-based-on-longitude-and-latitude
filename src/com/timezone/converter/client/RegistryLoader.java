package com.timezone.converter.client;

import java.util.List;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.input.parser.ParseStrategy;
import com.timezone.converter.input.reader.RecordReader;
import com.timezone.converter.structure.MultiLevelTree;
import com.timezone.converter.structure.Tree;

/**
 * Loader which will load the reference data from database/text file.
 */
public class RegistryLoader {

    private static final Tree tree = new MultiLevelTree();
    private final RecordReader recordReader;
    private final ParseStrategy parseStrategy;

    public RegistryLoader(final RecordReader recordReader, final ParseStrategy parseStrategy) {
        this.recordReader = recordReader;
        this.parseStrategy = parseStrategy;
    }

    /**
     * Warm up the registry so we could use it as reference to convert the input to appropriate time
     * zone
     */
    public void loadRegistry() {
        if (ConverterRegistry.getRegistry() == null) {
            final List<String> recordList = recordReader.readLines();
            final List<InputRecord> inputRecords = parseStrategy.parseInput(recordList);
            tree.addRecords(inputRecords);
            ConverterRegistry.setTree(tree);
        }
    }
}
