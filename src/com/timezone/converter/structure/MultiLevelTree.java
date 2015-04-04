package com.timezone.converter.structure;

import java.util.Collection;
import com.timezone.converter.datatype.InputRecord;
import com.timezone.converter.exception.NoRecordFoundException;

/**
 * Multi level tree which will store node in x and y co-ordinate based on longitude and latitude.
 * It's better than HashMap, as we now have range i.e. longitude from -180 to 180 degree while
 * latitude in range -90 - 90 degree. So we now distribute data in range provided by the user as
 * rows and columns.
 */
public class MultiLevelTree implements Tree {

    static final class Node {

        private final InputRecord record;
        private Node next;

        public Node(final InputRecord record) {
            this.record = record;
        }

        public InputRecord getRecord() {
            return record;
        }
    }

    private int row;
    private int column;
    final Node[][] nodes;
    private double pToleranceLimit = 0.25d;
    private double nToleranceLimit = -0.25d;

    public MultiLevelTree() {
        this(36, 18);
    }

    public MultiLevelTree(final int row, final int column) {
        this.row = row;
        this.column = column;
        nodes = new Node[row + 1][column + 1];// count for 0th element as well.
    }

    public MultiLevelTree(final int row, final int column, final double toleranceLimit) {
        this(row, column);
        this.pToleranceLimit = toleranceLimit;
        this.nToleranceLimit = -toleranceLimit;
    }

    @Override
    public boolean containsRecord(final InputRecord inputRecord) {
        int rowIndex = getIndex(row, inputRecord.getLongitude());
        int colIndex = getIndex(column, inputRecord.getLatitude());
        double longitude = inputRecord.getLongitude();
        double latitude = inputRecord.getLatitude();
        double thatLongitude;
        double thatLatitude;
        Node tempNode = nodes[rowIndex][colIndex];
        while (tempNode != null) {
            thatLongitude = tempNode.getRecord().getLongitude();
            thatLatitude = tempNode.getRecord().getLatitude();
            if (thatLongitude - longitude < pToleranceLimit && thatLongitude - longitude > nToleranceLimit && thatLatitude - latitude < pToleranceLimit && thatLatitude - latitude > nToleranceLimit) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    @Override
    public void addRecord(final InputRecord inputRecord) {
        int rowIndex = getIndex(row, inputRecord.getLongitude());
        int colIndex = getIndex(column, inputRecord.getLatitude());
        final Node node = new Node(inputRecord);
        if (nodes[rowIndex][colIndex] == null) {
            nodes[rowIndex][colIndex] = node;
        } else {
            Node tempNode = nodes[rowIndex][colIndex];
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = node;
        }
    }

    private int getIndex(final int maxIndex, final double value) {
        if (value > 0) {
            return maxIndex / 2 - ((int) value / 10) + 1;
        } else {
            return maxIndex / 2 - ((int) value / 10);
        }
    }

    @Override
    public void addRecords(final Collection<InputRecord> inputRecords) {
        for (InputRecord inputRecord : inputRecords) {
            addRecord(inputRecord);
        }
    }

    @Override
    public InputRecord getRecord(final double longitude, final double latitude) {
        int rowIndex = getIndex(row, longitude);
        int colIndex = getIndex(column, latitude);
        double thatLongitude;
        double thatLatitude;
        Node tempNode = nodes[rowIndex][colIndex];
        while (tempNode != null) {
            thatLongitude = tempNode.getRecord().getLongitude();
            thatLatitude = tempNode.getRecord().getLatitude();
            if (thatLongitude - longitude < pToleranceLimit && thatLongitude - longitude > nToleranceLimit && thatLatitude - latitude < pToleranceLimit && thatLatitude - latitude > nToleranceLimit) {
                return tempNode.getRecord();
            }
            tempNode = tempNode.next;
        }
        throw new NoRecordFoundException("Record for long: " + longitude + " lat: " + latitude + " not found");
    }
}
