package com.timezone.converter.client;

import com.timezone.converter.structure.Tree;

/**
 * Registry which will maintain the input data from database/text file.
 */
public class ConverterRegistry {

    private static Tree tree;

    private ConverterRegistry() {}

    /**
     * Singleton to get registry tree.
     * @return
     */
    public static Tree getRegistry() {
        return tree;
    }

    /**
     * Inject Tree
     * @param tree
     */
    public static void setTree(final Tree tree) {
        ConverterRegistry.tree = tree;
    }
}
