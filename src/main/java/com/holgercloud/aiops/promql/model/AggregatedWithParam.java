package com.holgercloud.aiops.promql.model;

import java.util.function.BiFunction;

/**
 * Interface representing an aggregated PromQL expression with a parameter.
 *
 * @param <T> the type of the parameter used in aggregation
 * @author holger
 * @date 2025/7/14
 */
public interface AggregatedWithParam<T> extends Aggregated {
    BiFunction<T, Expression, Aggregated> getAggregatorWithParam();
}
