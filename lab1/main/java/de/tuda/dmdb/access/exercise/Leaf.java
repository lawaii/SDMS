package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.access.AbstractIndexElement;
import de.tuda.dmdb.access.LeafBase;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.access.UniqueBPlusTreeBase;
import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;

/**
 * Index leaf
 *
 * @author cbinnig Note: Leaf-level pointers omitted since AbstractUniqueIndex only supports
 *     single-key lookup
 */
public class Leaf<T extends AbstractSQLValue> extends LeafBase<T> {

  /**
   * Leaf constructor
   *
   * @param uniqueBPlusTree TODO
   */
  public Leaf(UniqueBPlusTreeBase<T> uniqueBPlusTree) {
    super(uniqueBPlusTree);
  }

  @Override
  public RecordIdentifier lookup(T key) {
    // TODO: implement this method

    return null;
  }

  @Override
  public boolean insert(T key, AbstractRecord record) {
    // TODO: implement this method
    if(isOverfull()){

    }
    else{
      this.uniqueBPlusTree.setLeafRecPrototype(getUniqueBPlusTree().getLeafRecPrototype().append(record));

    }
    return true;
  }

  @Override
  public AbstractIndexElement<T> createInstance() {
    return new Leaf<T>(this.uniqueBPlusTree);
  }
}
