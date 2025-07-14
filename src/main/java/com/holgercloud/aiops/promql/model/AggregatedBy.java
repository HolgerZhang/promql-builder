package com.holgercloud.aiops.promql.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class representing an aggregated PromQL expression with grouping labels.
 * This class implements the Aggregated interface and provides a default implementation
 * for managing grouping labels. It allows for adding, removing, and retrieving group labels
 * in a PromQL expression.
 *
 * @author holger
 * @date 2025/7/14
 */
public abstract class AggregatedBy implements Aggregated {
    protected final List<String> labels;

    protected AggregatedBy() {
        this.labels = new LinkedList<>();
    }

    @Override
    public List<String> getGroupBy() {
        return labels;
    }
}
