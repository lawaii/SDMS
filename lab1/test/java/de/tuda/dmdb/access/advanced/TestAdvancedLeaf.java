package de.tuda.dmdb.access.advanced;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.testhelper.BufferManagerMocking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

@Disabled("Disabled until you implement advanced tests")
@ExtendWith(BufferManagerMocking.class)
public class TestAdvancedLeaf extends TestCase {

  /** Test that false is returned when existing record is inserted */
  @Test
  public void testLeafContainsAndDoesNotContainRecord() {
    // inserting same record again should return false
    // inserting different record should work
  }
}
