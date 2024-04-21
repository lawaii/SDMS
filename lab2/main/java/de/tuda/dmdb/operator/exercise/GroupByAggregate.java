package de.tuda.dmdb.operator.exercise;

import de.tuda.dmdb.operator.AbstractAggregationOperator;
import de.tuda.dmdb.operator.Operator;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.Record;
import de.tuda.dmdb.storage.types.AbstractSQLValue;
import de.tuda.dmdb.storage.types.EnumSQLType;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Multi-purpose group-by aggregate operator
 *
 * @author melhindi
 */
public class GroupByAggregate extends AbstractAggregationOperator {
  // TODO: Define any required member variables for your operator

  /**
   * Multi-purpose group-by aggregate operator. Records are grouped and the passed aggregates are
   * computed per group. The returned record should include an attribute for each column used for
   * grouping as well as a column for each computed aggregate
   *
   * @param child The input relation on which to perform the group-by-aggregate computation
   * @param groupByAttributes Index of the attributes/columns in the input relation that should be
   *     used for grouping records. Two records are in the same group if their values in all
   *     group-by columns are equal. If null is passed no grouping should be performed
   * @param aggregateAttributes Index of the attributes/columns in the input relation that should be
   *     used to compute an aggregate, there is a 1:1 mapping of aggregateAttribute and
   *     aggregateFunction
   * @param aggregateFunctions List of aggregate functions to apply, thereby aggregateFunction at
   *     index 0 is applied on aggregateAttribute at index 0 in the aggregateAttributes
   */
  public GroupByAggregate(
      Operator child,
      List<Integer> groupByAttributes,
      List<Integer> aggregateAttributes,
      List<BiFunction<Integer, Integer, Integer>> aggregateFunctions) {
    super(child, groupByAttributes, aggregateAttributes, aggregateFunctions);
  }

  @Override
  public void open() {
    // TODO implement this method
    // initialize member variables and child
  }

  @Override
  public AbstractRecord next() {
    // TODO implement this method
    // Consider the following: Is this a blocking or non-blocking operator?
    // groupByAttributes==null means no grouping required!
    return null;
  }

  @Override
  public void close() {
    // TODO implement this method
  }
}
