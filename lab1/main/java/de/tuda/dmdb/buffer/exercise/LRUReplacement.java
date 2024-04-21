package de.tuda.dmdb.buffer.exercise;

import de.tuda.dmdb.buffer.LRUReplacementBase;

import java.util.Vector;

/**
 * Implements the replacement strategy Last Recently Used
 *
 * @author melhindi, danfai
 */
public class LRUReplacement extends LRUReplacementBase {

  // Define here the member variables that you need to implement your algorithm
  // please only use data structures/classes available in the default java libraries
  // i.e., don't use external libraries such as Apache Common or Guava

  protected int clockHandPos = 0; // indicates current clock-hand position
  public Integer[] refBit = new Integer[poolSize];
  public LRUReplacement(Integer poolSize) {
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
    // TODO Implement this method
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
