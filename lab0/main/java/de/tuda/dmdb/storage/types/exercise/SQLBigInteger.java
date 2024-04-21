package de.tuda.dmdb.storage.types.exercise;

import de.tuda.dmdb.storage.types.SQLBigIntegerBase;

/**
 * SQL long value
 *
 * @author lthostrup
 */
public class SQLBigInteger extends SQLBigIntegerBase {
  private static final long serialVersionUID = 1L;

  /** Constructor with default value */
  public SQLBigInteger() {
    super();
  }

  /**
   * Constructor with value
   *
   * @param value Integer value
   */
  public SQLBigInteger(long value) {
    super(value);
  }

  @Override
  public byte[] serialize() {
    // TODO: implement this method
    return new byte[]{
            (byte) ((value >> 56) & 0xFF),
            (byte) ((value >> 48) & 0xFF),
            (byte) ((value >> 40) & 0xFF),
            (byte) ((value >> 32) & 0xFF),
            (byte) ((value >> 24) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value) & 0xFF),
    };
  }

  @Override
  public void deserialize(byte[] data) {
    // TODO: implement this method
    value =  data[7] & 0xFF |
            (data[6] & 0xFF) << 8 |
            (data[5] & 0xFF) << 16 |
            (long) (data[4] & 0xFF) << 24 |
            (long) (data[3] & 0xFF) << 32 |
            (long) (data[2] & 0xFF) << 40 |
            (long) (data[1] & 0xFF) << 48 |
            (long) (data[0] & 0xFF) << 56;


  }

  @Override
  public SQLBigInteger clone() {
    return new SQLBigInteger(this.value);
  }
}
