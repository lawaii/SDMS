package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.net.TCPServer;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.operator.ReceiveBase;
import de.tuda.dmdb.storage.AbstractRecord;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Implementation of receive operator
 *
 * @author melhindi
 */
public class Receive extends ReceiveBase {

  /**
   * Constructor of Receive
   *
   * @param child Child operator used to process next calls, usually SendOperator
   * @param numPeers Number of peer nodes that have to finish processing before operator finishes
   * @param listenerPort Port on which to bind receive server
   * @param nodeId Own nodeId, used for debugging
   */
  public Receive(Operator child, int numPeers, int listenerPort, int nodeId) {
    super(child, numPeers, listenerPort, nodeId);
  }

  @Override
  public void open() {
    // TODO implement this method
    // init local cache
    // create tcp server to listen for in-coming connections

    // call open on child after starting receive server, so that sendOperator can
    // connect
  }

  @Override
  public AbstractRecord next() {
    // TODO implement this method
    // get next element from send operator and remote
    return null;
  }

  @Override
  public void close() {
    // TODO implement this method
  }
}
