package de.tuda.dmdb.net;

import de.tuda.dmdb.storage.AbstractRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * This class is used to stub the actual network communication that is done via classes in dmdb.net
 * Connections are simulated by queue data structures. It also serves as synchronization point to
 * coordinate the setup and teardown of connections. To ease synchronization, each logical shuffling
 * operation is encapsulated in a network - see NetworkSimulator documentation.
 */
public class NetworkSimulatorManager {
  /**
   * The NetworkSimulator encapsulate a network for a logical shuffling operation. That is, e.g.,
   * when each table is shuffled by a different exchange operator, each shuffling process will
   * create a separate network. The NetworkSimulator of each network can be used by the nodes
   * participating in the shuffling process to synchronize the send and receive process. To provide
   * this functionality, the NetworkSimulator provides two barriers through CountDownLatches.
   */
  public class NetworkSimulator {
    private Map<Integer, Queue<AbstractRecord>> connectionMap; // simulate a connection per node
    private CountDownLatch registerLatch = null; // used to synchronize the communication setup
    private CountDownLatch sendLatch = null; // used to synchronize the communication teardown

    public NetworkSimulator() {
      this.connectionMap = new HashMap<Integer, Queue<AbstractRecord>>();
    }

    /**
     * Setup the connection for a particular node
     *
     * @param nodeId Id of node that is setting up its connection
     * @param numPeers The number of peers that we need to wait for.
     */
    public void registerNode(Integer nodeId, Integer numPeers) {
      synchronized (this) { // avoid race conditions
        this.connectionMap.putIfAbsent(nodeId, new ConcurrentLinkedQueue<AbstractRecord>());
        if (this.registerLatch == null) {
          this.registerLatch = new CountDownLatch(numPeers);
          this.sendLatch = new CountDownLatch(numPeers);
        }
      }
      this.registerLatch.countDown(); // indicate that we are done
      try {
        this.registerLatch.await(); // wait for other peers to finish their setup process
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /**
     * Retrieve a connection to send or receive records
     *
     * @param nodeId The node with which to communicate
     * @return A queue data structure that simulates the connection
     */
    public Queue<AbstractRecord> getConnection(Integer nodeId) {
      synchronized (connectionMap) {
        return this.connectionMap.get(nodeId);
      }
    }

    /** Signalize that we are done with processing and waiting for other nodes to finish */
    public void waitForPeers() {
      this.sendLatch.countDown();
      try {
        this.sendLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // used to manage networks for each shuffling process (ie. network)
  private ConcurrentHashMap<Integer, NetworkSimulator> networkSimulators;

  /* Singleton design pattern
   * We use a singleton to provide a 'global' connection pool to threads
   */
  private static volatile NetworkSimulatorManager instance;

  private NetworkSimulatorManager() {
    this.networkSimulators = new ConcurrentHashMap<>();
  }

  public static NetworkSimulatorManager getInstance() {
    if (instance == null) {
      synchronized (NetworkSimulatorManager.class) { // Synchronize for thread safety.
        if (instance == null) {
          instance = new NetworkSimulatorManager();
        }
      }
    }
    return instance;
  }

  /**
   * Generate a nodeId from a given networkId and index. The dummySend operator uses this as a
   * replacement of the listener port. Conceptually the index represents the actual nodeId and we
   * only add a networkId to be able to distinguish between to separate shuffle operations.
   *
   * @param networkId The network this node belongs to, each shuffle operation has its own network.
   *     When the dummySend operator is used, we need to make sure that different shuffle operations
   *     in a query plan has a dedicated/isolated network.
   * @param index The index of the node we are generating the id for
   * @return Artificial nodeId that can be used by an exchange operator
   */
  public static Integer generateArtificialNodeId(int networkId, int index) {
    return (index * 10 + networkId); // this supports up to max 10 different networks
  }

  /**
   * Generate a nodeId from a given networkId and index. The dummySend operator uses this as a
   * replacement of the listener port. Conceptually the index represents the actual nodeId and we
   * only add a networkId to be able to distinguish between to separate shuffle operations. This
   * methods assigns a default networkId of 0. When the dummySend operator is used, we need to make
   * sure that different shuffle operations in a query plan has a dedicated/isolated network.
   *
   * @param index The index of the node we are generating the id for
   * @return Artificial nodeId that can be used by an exchange operator
   */
  public static Integer generateArtificialNodeId(int index) {
    return generateArtificialNodeId(0, index);
  }

  /**
   * Get the NetworkSimulator for a given NodeId
   *
   * @param nodeId From which we will calculate the network id
   * @return NetworkSimulator instance of the passed node's network
   */
  public NetworkSimulator getNetworkSimulator(Integer nodeId) {
    Integer networkId = nodeId % 10; // this supports up to max 10 different networks
    return networkSimulators.computeIfAbsent(networkId, key -> new NetworkSimulator());
  }

  /** Enables creating fresh NetworkSimulators for different tests */
  public void clearInstance() {
    networkSimulators.clear();
  }
}
