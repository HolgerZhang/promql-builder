package com.holgercloud.aiops.promql.model;

import java.util.Collection;

/**
 * Interface representing a PromQL expression.
 *
 * @author holger
 * @date 2025/7/14
 */
public interface Expression {
    String toPromQL();

    Collection<Expression> getComponents();
}
