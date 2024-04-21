package de.tuda.dmdb.buffer.exercise;

import de.tuda.dmdb.buffer.BufferManagerBase;
import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.EnumPageType;
import de.tuda.dmdb.storage.exercise.RowPage;

import java.io.IOException;
import java.util.Vector;

/**
 * BufferManager maintain buffer frames
 *
 * @author melhindi
 */
public class BufferManager extends BufferManagerBase {

  /** static Singleton instance. */
  private static volatile BufferManager instance;

  private BufferManager() {
    super();
    switch (REPLACEMENT_POLICY) {
      case ClockReplacement:
        this.replacer = new ClockReplacement(POOL_SIZE);
        break;

      default:
        break;
    }
  }

  /**
   * Returns a singleton instance of BufferManager.
   *
   * @return singleton instance of BufferManager
   */
  public static BufferManagerBase getInstance() {
    // Double lock for thread safety.
    if (instance == null) {
      synchronized (BufferManager.class) {
        if (instance == null) {
          instance = new BufferManager();
        }
      }
    }
    return instance;
  }

  /** Enables creating a fresh BufferManager for tests */
  public static void clearInstance() {
    nextPageNumber = 0;
    POOL_SIZE = 1000;
    instance = null;
  }

  @Override
  public AbstractPage pin(Integer pageId) {
    // TODO implement this method
    if(freeFrames == 0){
      return null;
    }
    try {
        return new RowPage(this.diskManager.readPage(pageId));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void unpin(Integer pageId) {
    // TODO implement this method
    Vector<Integer> vector = new Vector<>();
    vector.addElement(pageId);
    getReplacer().setEvictedPages(vector);
    freeFrames = freeFrames + 1;
  }

  @Override
  public AbstractPage createPage(EnumPageType type, byte[] data) {
    // TODO implement this method

    // You can use this snippet to create a new page

    AbstractPage page = null;
    switch (type) {
      case RowPageType:
        page = new RowPage(data);
        break;

      default:
        throw new IllegalArgumentException("You passed an unsupported page type");
    }
    page.setPageNumber(diskManager.getNextPageId());
    freeFrames = freeFrames - 1;
    return page;
  }

  @Override
  public AbstractPage createDefaultPage(EnumPageType type, int slotSize) {
    // TODO implement this method

    // You can use this snippet to create a new page
    AbstractPage page = null;
    switch (type) {
      case RowPageType:
        page = new RowPage(slotSize);
        break;

      default:
        throw new IllegalArgumentException("You passed an unsupported page type");
    }
    page.setPageNumber(diskManager.getNextPageId());
    freeFrames = freeFrames - 1;
    return page;

    // Use diskManager.getNextPageId() for the page number
  }
}
