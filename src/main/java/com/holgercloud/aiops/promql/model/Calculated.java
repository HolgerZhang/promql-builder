package com.holgercloud.aiops.promql.model;

import java.util.function.BiFunction;

/**
 * Interface representing a calculated PromQL expression.
 *
 * @author holger
 * @date 2025/7/14
 */
public interface Calculated extends Expression {
    BiFunction<Expression, Expression, Calculated> getOperator();
}
