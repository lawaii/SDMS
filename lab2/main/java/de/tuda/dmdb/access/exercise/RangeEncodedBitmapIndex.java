package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.access.AbstractBitmapIndex;
import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.operator.TableScanBase;
import de.tuda.dmdb.operator.exercise.TableScan;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import de.tuda.dmdb.storage.types.EnumSQLType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Bitmap index that uses the range encoded approach (still one bitmap for each distinct value)
 *
 * @author lthostrup
 * @param <T> Type of the key index by the index. While all abstractSQLValues subclasses can be
 *     used, the implementation currently only support for SQLInteger type is guaranteed.
 */
public class RangeEncodedBitmapIndex<T extends AbstractSQLValue> extends AbstractBitmapIndex<T> {

  /**
   * Constructor of NaiveBitmapIndex
   *
   * @param table Table for which the bitmap index will be build
   * @param keyColumnNumber: index of the column within the passed table that should be indexed
   */
  public RangeEncodedBitmapIndex(AbstractTable table, int keyColumnNumber) {
    super(table, keyColumnNumber);
    this.bitMaps = new TreeMap<T, BitSet>(); // Use TreeMap to get an ordered map impl.
    TableScan tableScan = new TableScan(this.getTable());
    this.bulkLoad(tableScan);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void bulkLoad(TableScanBase tableScan) {
    // TODO Implement this method
    int recordCount = tableScan.getTable().getRecordCount();
    EnumSQLType type = tableScan.getTable().getType(keyColumnNumber);
    ArrayList<T> arrayList = new ArrayList<>();
    for(int i=0; i<recordCount;i++){
      AbstractSQLValue value  = tableScan.getTable().getRecordFromRowNum(i).getValue(keyColumnNumber);
      if(!arrayList.contains(value)){
        arrayList.add((T) value);
      }
    }

    for (T abstractSQLValue: arrayList) {
      bitMaps.put((T) abstractSQLValue, new BitSet(recordCount));
    }

    for (int i = 0; i < recordCount; i++) {
      AbstractSQLValue val = tableScan.getTable().getRecordFromRowNum(i).getValue(keyColumnNumber);;
      bitMaps.
      bitMaps.get(val).set(i);
    }
  }

  @Override
  public Iterator<RecordIdentifier> rangeLookup(T startKey, T endKey) {
    // TODO Implement this method
    // init variables
    List resultList = new ArrayList<>();


    bitMaps.forEach((key, value) -> {

      if( key.compareTo(startKey)>=0 && key.compareTo(endKey)<=0){
        List<Integer> listFromBitSet = value.stream()
                .boxed()
                .collect(Collectors.toList());
        resultList.addAll(listFromBitSet);
      }
    });

    // 对List进行排序
    Collections.sort(resultList);

    List<RecordIdentifier> resList = new ArrayList<>();

    for(Object o:resultList){
      RecordIdentifier res = table.getRecordIDFromRowNum(Integer.valueOf(o.toString()));
      resList.add(res);
    }

    return resList.iterator();
  }

  @Override
  public boolean isUniqueIndex() {
    return false;
  }
}
