package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.operator.HashJoinBase;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import java.util.HashMap;

public class HashJoin extends HashJoinBase {

  public HashJoin(Operator leftChild, Operator rightChild, int leftAtt, int rightAtt) {
    super(leftChild, rightChild, leftAtt, rightAtt);
  }

  @Override
  public void open() {
     // TODO implement this method
  }

  @Override
  public AbstractRecord next() {
     return null;
     // TODO implement this method
  }

  @Override
  public void close() {
     // TODO implement this method  
  }
}
