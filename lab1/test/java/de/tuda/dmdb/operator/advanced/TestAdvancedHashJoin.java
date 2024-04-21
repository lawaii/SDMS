package de.tuda.dmdb.operator.advanced;

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
import org.junit.jupiter.api.Disabled;

@Disabled("Disabled until you implement advanced tests")
public class TestAdvancedHashJoin extends TestCase {

  /**
   * Tests that only qualifying records are returned from next() when left and right children only
   * match on join-key on every second record.
   */
  @Test
  public void testHashJoinComplex() {

  }

  /** Tests that records are only returned after open() has been called on HashJoin */
  @Test
  public void testHashJoinInit() {

  }
  
}
