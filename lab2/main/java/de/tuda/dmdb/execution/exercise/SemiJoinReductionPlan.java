package de.tuda.dmdb.execution.exercise;

import de.tuda.dmdb.execution.QueryPlan;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.operator.exercise.Distinct;
import de.tuda.dmdb.operator.exercise.Projection;
import de.tuda.dmdb.operator.exercise.ReplicationExchange;
import de.tuda.dmdb.operator.exercise.SemiHashJoin;
import java.util.Map;
import java.util.Vector;

/**
 * Query Subplan to perform a distributed semi-join reduction Assume that the reduction is applied
 * to the left relation
 *
 * @author melhindi
 */
public class SemiJoinReductionPlan extends QueryPlan {
  protected Operator leftRelation;
  protected Operator rightRelation;
  int leftJoinAtt;
  int rightJoinAtt;
  int nodeId = 0;
  int listenerPort = 8000;
  Map<Integer, String> nodeMap;

  public SemiJoinReductionPlan(
      Operator leftRelation,
      Operator rightRelation,
      int leftJoinAtt,
      int rightJoinAtt,
      int nodeId,
      int listenerPort,
      Map<Integer, String> nodeMap) {
    this.leftRelation = leftRelation;
    this.rightRelation = rightRelation;
    this.leftJoinAtt = leftJoinAtt;
    this.rightJoinAtt = rightJoinAtt;
    this.nodeId = nodeId;
    this.listenerPort = listenerPort;
    this.nodeMap = nodeMap;
  }

  @Override
  public Operator compilePlan() {
    // TODO: Define plan by chaining together operators and return root operator
    return null;
  }
}
