package de.tuda.dmdb.operator;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.access.exercise.HeapTable;
import de.tuda.dmdb.operator.exercise.HashJoin;
import de.tuda.dmdb.operator.exercise.TableScan;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.Record;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHashJoin extends TestCase {

  /**
   * Tests that HashJoin returns all merged records of left and right children, since all qualify
   */
  @Test
  public void testHashJoinNext() {

    List<AbstractRecord> expectedResult = new ArrayList<AbstractRecord>();

    AbstractRecord templateRecord = new Record(2);
    templateRecord.setValue(0, new SQLInteger(0));
    templateRecord.setValue(1, new SQLInteger(0));

    HeapTable htLeft = new HeapTable(templateRecord);
    HeapTable htRight = new HeapTable(templateRecord);

    int numRecords = 100;
    for (int i = 0; i < numRecords; i++) {
      AbstractRecord record = new Record(2);
      record.setValue(0, new SQLInteger(i));
      record.setValue(1, new SQLInteger(i));

      htLeft.insert(record);
      htRight.insert(record);

      expectedResult.add(record.append(record));
    }

    TableScan tsLeft = new TableScan(htLeft);
    TableScan tsRight = new TableScan(htRight);

    HashJoin join = new HashJoin(tsLeft, tsRight, 1, 0);

    join.open();
    for (AbstractRecord expectedRec : expectedResult) {
      Assertions.assertEquals(
          expectedRec, join.next(), "Record returned from next() did not match expected");
    }
  }
}
