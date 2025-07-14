package com.holgercloud.aiops.promql.model;

import java.util.function.Function;

/**
 * Interface representing a functionized PromQL expression.
 * This interface allows for the encapsulation of a function that operates on an Expression.
 *
 * @author holger
 * @date 2025/7/14
 */
public interface Functionized extends Expression {
    Function<Expression, Expression> getFunction();
}
