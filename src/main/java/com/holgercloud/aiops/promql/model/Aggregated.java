package com.holgercloud.aiops.promql.model;

import java.util.List;
import java.util.function.Function;

/**
 * Interface representing an aggregated PromQL expression.
 * This interface extends Expression and provides methods for grouping and aggregating data.
 *
 * @author holger
 * @date 2025/7/14
 */
public interface Aggregated extends Expression {

    List<String> getGroupBy();

    default Aggregated groupBy(String... labels) {
        if (getGroupBy() == null) {
            return this;
        }
        if (labels == null) {
            return this;
        }
        for (String label : labels) {
            if (label != null && !label.trim().isEmpty()) {
                getGroupBy().add(label.trim());
            }
        }
        return this;
    }

    default Aggregated reset() {
        if (getGroupBy() == null) {
            return this;
        }
        getGroupBy().clear();
        return this;
    }

    default Aggregated removeGroupBy(String label) {
        if (getGroupBy() == null) {
            return this;
        }
        if (label != null && !label.trim().isEmpty()) {
            getGroupBy().removeIf(l -> l.equals(label.trim()));
        }
        return this;
    }

    Function<Expression, Aggregated> getAggregator();
}
