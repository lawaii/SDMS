package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.access.AbstractIndexElement;
import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.access.UniqueBPlusTreeBase;
import de.tuda.dmdb.operator.TableScanBase;
import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Unique B+-Tree implementation
 *
 * @author cbinnig
 * @param <T> Type of the key index by the index.
 */
public class UniqueBPlusTree<T extends AbstractSQLValue> extends UniqueBPlusTreeBase<T> {

  /**
   * Constructor of B+-Tree with user-defined fil-grade
   *
   * @param table Table to be indexed
   * @param keyColumnNumber Number of unique column which should be indexed
   * @param minFillGrade Minimum fill grade of an index element (except root)
   */
  public UniqueBPlusTree(AbstractTable table, int keyColumnNumber, int minFillGrade) {
    super(table, keyColumnNumber, minFillGrade);
  }

  /**
   * Constructor for B+-tree with default fill grade
   *
   * @param table table to be indexed
   * @param keyColumnNumber Number of unique column which should be indexed
   */
  public UniqueBPlusTree(AbstractTable table, int keyColumnNumber) {
    this(table, keyColumnNumber, DEFAULT_FILL_GRADE);
  }

  @Override
  public void bulkLoad(TableScanBase tableScan) {
    tableScan.open();
    AbstractRecord rec;
    while ((rec = tableScan.next()) != null) {
      this.insert(rec);
    }
    tableScan.close();
  }

  @SuppressWarnings({"unchecked"})
  @Override
  public boolean insert(AbstractRecord record) {

    // TODO: implement this method

    return false;
  }

  @Override
  public Iterator<RecordIdentifier> lookup(T key) {
    // TODO: implement this method

    return null;
  }

  @Override
  public Iterator<RecordIdentifier> rangeLookup(T startKey, T endKey) {
    // TODO: implement this method

    return null;
  }
  
}
