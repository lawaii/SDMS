package de.tuda.dmdb.buffer.exercise;

import de.tuda.dmdb.buffer.ClockReplacementBase;

import java.util.Vector;

/**
 * Implements the clock replacement strategy
 *
 * @author melhindi
 */
public class ClockReplacement extends ClockReplacementBase {

  // Define here the member variables that you need to implement your algorithm
  // please only use data structures/classes available in the default java libraries
  // i.e., don't use external libraries such as Apache Common or Guava
  public Integer[] refBit = new Integer[poolSize];

  public ClockReplacement(Integer poolSize) {
    super(poolSize);
  }

  @Override
  public void fix(Integer pageId) {
    // TODO Implement this method
    if(pageId>=refBit.length){
      throw new RuntimeException();
    }

    refBit[pageId] = 1;

  }

  @Override
  public void unfix(Integer pageId) {
    // TODO Implement this method
    refBit[pageId] = 0;
  }

  @Override
  public Integer evict() {
    // TODO Auto-generated method stub
    while(refBit[(clockHandPos) % poolSize]==1){
      unfix(clockHandPos);
      clockHandPos = clockHandPos + 1;
    }

    Integer page = (clockHandPos) % poolSize;
    Vector<Integer> vector = new Vector<>();
    vector.addElement(page);
    setEvictedPages(vector);

    return page;

  }
}
