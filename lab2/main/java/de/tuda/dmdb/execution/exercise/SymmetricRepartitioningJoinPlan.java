package de.tuda.dmdb.execution.exercise;

import de.tuda.dmdb.execution.QueryPlan;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.operator.exercise.HashJoin;
import de.tuda.dmdb.operator.exercise.HashRepartitionExchange;
import java.util.Map;

/**
 * Query Subplan to perform a distributed asymmetric repartitioning join of two relations Both
 * relations need to be repartitioned on the join-key
 *
 * @author melhindi
 */
public class SymmetricRepartitioningJoinPlan extends QueryPlan {
  protected Operator leftRelation;
  protected Operator rightRelation;
  int leftJoinAtt;
  int rightJoinAtt;
  int nodeIdRight = 0;
  int nodeIdLeft = 0;
  int listenerPortLeft = 8000;
  int listenerPortRight = this.listenerPortLeft + 1;
  Map<Integer, String> nodeMapLeft;
  Map<Integer, String> nodeMapRight;

  public SymmetricRepartitioningJoinPlan(
      Operator leftRelation,
      Operator rightRelation,
      int leftJoinAtt,
      int rightJoinAtt,
      int nodeIdLeft,
      int listenerPortLeft,
      Map<Integer, String> nodeMapLeft,
      int nodeIdRight,
      int listenerPortRight,
      Map<Integer, String> nodeMapRight) {
    this.leftRelation = leftRelation;
    this.rightRelation = rightRelation;
    this.leftJoinAtt = leftJoinAtt;
    this.rightJoinAtt = rightJoinAtt;
    this.nodeIdLeft = nodeIdLeft;
    this.nodeIdRight = nodeIdRight;
    this.listenerPortLeft = listenerPortLeft;
    this.listenerPortRight = listenerPortRight;
    this.nodeMapLeft = nodeMapLeft;
    this.nodeMapRight = nodeMapRight;
  }

  @Override
  public Operator compilePlan() {
    // TODO: Define plan by chaining together operators and return root operator
    // Use a suitable join algorithm
    // Note: Each shuffle Operation needs their own nodeId so they can belong to different networks,
    // when using the SendReceiveMocking class
    return null;
  }
}
