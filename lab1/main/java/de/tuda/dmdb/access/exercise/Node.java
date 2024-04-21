package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.access.AbstractIndexElement;
import de.tuda.dmdb.access.NodeBase;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.access.UniqueBPlusTreeBase;
import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;

/**
 * Index node
 *
 * @author cbinnig
 */
public class Node<T extends AbstractSQLValue> extends NodeBase<T> {

  /**
   * Node constructor
   *
   * @param uniqueBPlusTree TODO
   */
  public Node(UniqueBPlusTreeBase<T> uniqueBPlusTree) {
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

    return false;
  }

  @Override
  public AbstractIndexElement<T> createInstance() {
    return new Node<T>(this.uniqueBPlusTree);
  }
}
