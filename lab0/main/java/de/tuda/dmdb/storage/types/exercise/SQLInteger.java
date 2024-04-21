package de.tuda.dmdb.storage.types.exercise;

import de.tuda.dmdb.storage.types.SQLIntegerBase;

/**
 * SQL integer value
 *
 * @author cbinnig
 */
public class SQLInteger extends SQLIntegerBase {

  private static final long serialVersionUID = 1L;

  /** Constructor with default value */
  public SQLInteger() {
    super();
  }

  /**
   * Constructor with value
   *
   * @param value Integer value
   */
  public SQLInteger(int value) {
    super(value);
  }

  @Override
  public byte[] serialize() {
    // TODO: implement this method
    return new byte[]{
            (byte) ((value >> 24) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value) & 0xFF),
      };
  }

  @Override
  public void deserialize(byte[] data) {
    // TODO: implement this method
    value = data[3] & 0xFF |
            (data[2] & 0xFF) << 8 |
            (data[1] & 0xFF) << 16 |
            (data[0] & 0xFF) << 24;


  }

  @Override
  public SQLInteger clone() {
    return new SQLInteger(this.value);
  }
}
