package de.tuda.dmdb.access;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.access.exercise.HeapTable;
import de.tuda.dmdb.access.exercise.UniqueBPlusTree;
import de.tuda.dmdb.operator.exercise.TableScan;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.Record;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import de.tuda.dmdb.storage.types.exercise.SQLVarchar;
import de.tuda.dmdb.testhelper.BufferManagerMocking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BufferManagerMocking.class)
public class TestUniqueBPlusTree extends TestCase {

  @Test
  public void testIsUnique() {
    AbstractRecord record = new Record(2);
    record.setValue(0, new SQLInteger(10));
    record.setValue(1, new SQLVarchar("Hello113", 10));

    AbstractTable table = new HeapTable(record.clone());

    AbstractDynamicIndex<SQLInteger> index = new UniqueBPlusTree<SQLInteger>(table, 0, 1);
    Assertions.assertTrue(index.isUniqueIndex());
  }

  @Test
  public void testBulkLoad() {
    AbstractRecord record1 = new Record(2);
    record1.setValue(0, new SQLInteger(1));
    record1.setValue(1, new SQLVarchar("Hello111", 10));
    AbstractTable table = new HeapTable(record1.clone());

    final int NUM_RECORDS = 100;

    for (int i = 0; i < NUM_RECORDS; i++) {
      AbstractRecord record = new Record(2);
      record.setValue(0, new SQLInteger(i));
      record.setValue(1, new SQLVarchar("Hello" + i, 10));
      table.insert(record);
    }

    AbstractDynamicIndex<SQLInteger> index = new UniqueBPlusTree<SQLInteger>(table, 0, 1);
    TableScan tableScan = new TableScan(table);
    index.bulkLoad(tableScan);

    for (int i = 0; i < NUM_RECORDS; i++) {
      SQLInteger lookupValue = new SQLInteger(i);
      AbstractRecord recordCmp = table.lookup(index.lookup(lookupValue).next());
      Assertions.assertEquals(lookupValue, recordCmp.getValue(0));
    }
  }

  /** Insert three records and reads them again using a SQLInteger index */
  @Test
  public void testIndexSimple() {
    AbstractRecord record1 = new Record(2);
    record1.setValue(0, new SQLInteger(1));
    record1.setValue(1, new SQLVarchar("Hello111", 10));

    AbstractRecord record2 = new Record(2);
    record2.setValue(0, new SQLInteger(2));
    record2.setValue(1, new SQLVarchar("Hello112", 10));

    AbstractRecord record3 = new Record(2);
    record3.setValue(0, new SQLInteger(3));
    record3.setValue(1, new SQLVarchar("Hello113", 10));

    AbstractRecord record4 = new Record(2);
    record4.setValue(0, new SQLInteger(4));
    record4.setValue(1, new SQLVarchar("Hello114", 10));

    AbstractTable table = new HeapTable(record1.clone());

    AbstractDynamicIndex<SQLInteger> index = new UniqueBPlusTree<SQLInteger>(table, 0, 1);
    index.insert(record1);
    index.insert(record2);
    index.insert(record3);
    index.insert(record4);
    // index.print();

    AbstractRecord record1Cmp = table.lookup(index.lookup((SQLInteger) record1.getValue(0)).next());
    Assertions.assertEquals(record1, record1Cmp);

    AbstractRecord record2Cmp = table.lookup(index.lookup((SQLInteger) record2.getValue(0)).next());
    Assertions.assertEquals(record2, record2Cmp);

    AbstractRecord record3Cmp = table.lookup(index.lookup((SQLInteger) record3.getValue(0)).next());
    Assertions.assertEquals(record3, record3Cmp);
  }

  /** Insert three records and reads them again using a SQLVarchar index */
  @Test
  public void testIndexSimple2() {
    AbstractRecord record1 = new Record(2);
    record1.setValue(0, new SQLInteger(1));
    record1.setValue(1, new SQLVarchar("Hello111", 10));

    AbstractRecord record2 = new Record(2);
    record2.setValue(0, new SQLInteger(2));
    record2.setValue(1, new SQLVarchar("Hello112", 10));

    AbstractRecord record3 = new Record(2);
    record3.setValue(0, new SQLInteger(3));
    record3.setValue(1, new SQLVarchar("Hello113", 10));

    AbstractTable table = new HeapTable(record1.clone());

    AbstractDynamicIndex<SQLVarchar> index = new UniqueBPlusTree<SQLVarchar>(table, 1, 1);
    index.insert(record1);
    index.insert(record2);
    index.insert(record3);
    // index.print();

    AbstractRecord record1Cmp = table.lookup(index.lookup((SQLVarchar) record1.getValue(1)).next());
    Assertions.assertEquals(record1, record1Cmp);

    AbstractRecord record2Cmp = table.lookup(index.lookup((SQLVarchar) record2.getValue(1)).next());
    Assertions.assertEquals(record2, record2Cmp);

    AbstractRecord record3Cmp = table.lookup(index.lookup((SQLVarchar) record3.getValue(1)).next());
    Assertions.assertEquals(record3, record3Cmp);
  }
}
