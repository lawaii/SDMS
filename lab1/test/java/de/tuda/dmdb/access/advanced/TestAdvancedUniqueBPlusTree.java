package de.tuda.dmdb.access.advanced;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.testhelper.BufferManagerMocking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

@Disabled("Disabled until you implement advanced tests")
@ExtendWith(BufferManagerMocking.class)
public class TestAdvancedUniqueBPlusTree extends TestCase {

  /** Insert three records and lookup not existing keys */
  @Test
  public void testIndexKeyNotExisting() {}

  /** Insert many records with increasing keys and do lookup for each key afterwards */
  @Test
  public void testIndexForwardInsert() {}

  /** Insert many records with decreasing keys and do lookup for each key afterwards */
  @Test
  public void testIndexReverseInsert() {}
}
