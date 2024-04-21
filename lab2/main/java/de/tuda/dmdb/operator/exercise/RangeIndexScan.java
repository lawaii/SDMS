package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.access.AbstractIndex;
import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.operator.RangeIndexScanBase;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import java.util.Iterator;

public class RangeIndexScan<T extends AbstractSQLValue> extends RangeIndexScanBase<T> {

  /**
   * Constructor of a RangeIndexScan with lower and upper key Lower and Upper are AbstractSQLValue
   * instances that specify the lower- and upper bound of a range. A record falls into the range if
   * {@code lower <= recordKey <= upper}.
   *
   * @param table The table defining the record schema
   * @param index The index that should be used for the scan
   * @param lower The lower bound of the range (inclusive)
   * @param upper The upper bound of the range (inclusive)
   */
  public RangeIndexScan(AbstractTable table, AbstractIndex<T> index, T lower, T upper) {
    super(table, index, lower, upper);
  }

  @Override
  public void open() {
    // TODO implement this method
  }

  @Override
  public AbstractRecord next() {
    // TODO implement this method
    return null;
  }

  @Override
  public void close() {
    // TODO implement this method
  }
}
