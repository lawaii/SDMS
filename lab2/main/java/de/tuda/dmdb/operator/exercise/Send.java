package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.net.TCPClient;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.operator.SendBase;
import de.tuda.dmdb.storage.AbstractRecord;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Implementation of send operator
 *
 * @author melhindi
 */
public class Send extends SendBase {

  /**
   * Constructor of Send
   *
   * @param child Child operator used to process next calls, e.g., TableScan or Selection
   * @param nodeId Own nodeId to identify which records to keep locally
   * @param nodeMap Map containing connection information (as "IP:port" or "domain-name:port") to
   *     establish connection to other peers
   * @param distributionFunction Function to determine where to send a record to
   */
  public Send(
      Operator child,
      int nodeId,
      Map<Integer, String> nodeMap,
      Function<AbstractRecord, List<Integer>> distributionFunction) {
    super(child, nodeId, nodeMap, distributionFunction);
  }

  @Override
  public void open() {
    // TODO: implement this method
    // init child

    // create a client socket for all peer nodes using information in nodeMap
  }

  @Override
  public AbstractRecord next() {
    // TODO: implement this method
    // retrieve next from child
      // invoke hash-function to re-partition record
      // keep in mind that distributionFunction can tell us to send to remote nodes
      // and ourself
      // first send to remote nodes and in the end return local element

    return null;
  }

  @Override
  public void close() {
    // TODO: implement this method
  }
}
