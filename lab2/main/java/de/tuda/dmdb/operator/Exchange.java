/** */
package de.tuda.dmdb.operator;

import de.tuda.dmdb.operator.exercise.Receive;
import de.tuda.dmdb.operator.exercise.Send;
import de.tuda.dmdb.storage.AbstractRecord;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Exchange operator for supporting distributed and parallel query execution
 *
 * @author melhindi
 */
public abstract class Exchange extends UnaryOperator {
  // These variables are used to inject stub classes for the unit tests
  protected static Class<? extends SendBase> sendClass = Send.class;
  protected static Class<? extends ReceiveBase> receiveClass = Receive.class;

  public static void setSendClass(Class<? extends SendBase> sendClass) {
    Exchange.sendClass = sendClass;
  }

  public static void setReceiveClass(Class<? extends ReceiveBase> receiveClass) {
    Exchange.receiveClass = receiveClass;
  }

  protected int nodeId; // Own nodeId used to identify local records
  protected ReceiveBase receiveOperator; // required to receive records from peers
  protected SendBase sendOperator; // required to send records to peers
  protected Map<Integer, String>
      nodeMap; // Map of the form <NodeId:"IP:port"> containing connection information of
  // all peers in the network
  protected int listenerPort; // Port on which receive operator will listen for incoming connections
  protected Function<AbstractRecord, List<Integer>>
      distributionFunction; // function that will be used by the send
  // operator to determine where to send a
  // record to

  /**
   * Constructor of Exchange operator for supporting distributed and parallel query execution
   *
   * @param child The input relation from which records are retrieved and potentially send to other
   *     nodes
   * @param nodeId Own nodeId used to identify local records in send-operator
   * @param nodeMap Map of the form NodeId:"IP:port" containing connection information of all peers
   *     in the network
   * @param listenerPort Port on which receive operator will listen for incoming connections
   * @param distributionFunction Function that will be used by the send operator to determine where
   *     to send a record to The distribution Function is a mapping function that returns for a
   *     given AbstractRecord a list of nodeId to which the record should be sent
   */
  protected Exchange(
      Operator child,
      int nodeId,
      Map<Integer, String> nodeMap,
      int listenerPort,
      Function<AbstractRecord, List<Integer>> distributionFunction) {
    super(child);
    this.nodeId = nodeId;
    this.nodeMap = nodeMap;
    this.listenerPort = listenerPort;
    this.distributionFunction = distributionFunction;
    // create instances of the Send and Receive operators through java reflection
    Constructor<?> sendConstructor;
    Constructor<?> receiveConstructor;
    try {
      sendConstructor =
          sendClass.getConstructors()[0]; // we know/assume that there is only one constructor
      this.sendOperator =
          (SendBase)
              sendConstructor.newInstance(
                  new Object[] {child, nodeId, nodeMap, this.distributionFunction});
      receiveConstructor =
          receiveClass.getConstructors()[0]; // we know/assume that there is only one constructor
      this.receiveOperator =
          (ReceiveBase)
              receiveConstructor.newInstance(
                  new Object[] {this.sendOperator, nodeMap.size(), listenerPort, nodeId});
    } catch (SecurityException
        | InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
    // old implementation for later reference
    // this.sendOperator = new Send(child, nodeId, nodeMap, this.distributionFunction);
    // this.receiveOperator = new Receive(this.sendOperator, nodeMap.size(), listenerPort, nodeId);
  }

  @Override
  public void open() {
    // inits network and listener thread and handler threads
    this.receiveOperator.open();
  }

  @Override
  public AbstractRecord next() {
    // call next on child
    return this.receiveOperator.next();
  }

  @Override
  public void close() {
    this.receiveOperator.close();
  }

  /**
   * Retrieve the distribution function that is passed to the send operator
   *
   * @return the distributionFunction
   */
  public Function<AbstractRecord, List<Integer>> getDistributionFunction() {
    return distributionFunction;
  }

  /**
   * Set the distribution function that is passed to the send operator
   *
   * @param distributionFunction the distributionFunction to set
   */
  public void setDistributionFunction(
      Function<AbstractRecord, List<Integer>> distributionFunction) {
    this.distributionFunction = distributionFunction;
    if (this.sendOperator != null) {
      this.sendOperator.setDistributionFunction(this.distributionFunction);
    }
  }
}
