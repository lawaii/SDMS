package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.buffer.BufferManagerBase;
import de.tuda.dmdb.buffer.exercise.BufferManager;
import de.tuda.dmdb.operator.TableScanBase;
import de.tuda.dmdb.storage.AbstractRecord;

public class TableScan extends TableScanBase {

  // Use BufferManager to get AbstractPages
  protected BufferManagerBase bufferManager = BufferManager.getInstance();
  protected int NumberOfPages;
  protected AbstractRecord Record;

  public TableScan(AbstractTable table) {
    super(table);
  }

  @Override
  public void open() {
    // TODO implement this method
    NumberOfPages = this.table.getNumPages();
    Record = this.table.getPrototype().clone();

  }

  @Override
  public AbstractRecord next() {
    // TODO implement this method
    // Use this.table.getPrototype().clone() to get a Record with the correct schema

    return null;
  }

  @Override
  public void close() {
    // TODO implement this method
  }
}
