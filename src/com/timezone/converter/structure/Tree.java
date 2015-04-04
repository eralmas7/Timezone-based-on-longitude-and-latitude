package com.timezone.converter.structure;

import java.util.Collection;
import com.timezone.converter.datatype.InputRecord;

/**
 * Tree data structure which will do certain operations on Tree.
 */
public interface Tree {

    /**
     * Add a record to tree.
     * @param inputRecord
     */
    public void addRecord(final InputRecord inputRecord);

    /**
     * Add collection of records to tree.
     * @param inputRecord
     */
    public void addRecords(final Collection<InputRecord> inputRecords);

    /**
     * Checks existence of an input record.
     * @param inputRecord
     * @return
     */
    public boolean containsRecord(final InputRecord inputRecord);

    /**
     * Get the input record given longitude and latitude.
     * @param longitude
     * @param latitude
     * @return
     */
    public InputRecord getRecord(final double longitude, final double latitude);
}
