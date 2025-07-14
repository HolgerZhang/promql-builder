package com.holgercloud.aiops.promql.test;

import com.holgercloud.aiops.promql.Aggregators;
import com.holgercloud.aiops.promql.Functions;
import com.holgercloud.aiops.promql.Operators;
import com.holgercloud.aiops.promql.model.Expression;
import com.holgercloud.aiops.promql.model.Metric;
import com.holgercloud.aiops.promql.model.Number;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author holger
 * @date 2025/7/14
 */
public class SimpleTests {
    @Test
    public void testMetric() {
        // http_requests_total
        Expression metric1 = Metric.of("http_requests_total");
        assertEquals("http_requests_total", metric1.toPromQL());
        // http_requests_total{job="apiserver", handler="/api/comments"}
        Expression metric2 = Metric.of("http_requests_total")
                .label("job", "apiserver")
                .label("handler", "/api/comments");
        assertEquals("http_requests_total{job=\"apiserver\", handler=\"/api/comments\"}", metric2.toPromQL());
        // http_requests_total{job=~".*server"}
        Expression metric3 = Metric.of("http_requests_total")
                .label("job", Metric.Compare.LIKE, ".*server");
        assertEquals("http_requests_total{job=~\".*server\"}", metric3.toPromQL());
        // http_requests_total{status!~"4.."}
        Expression metric4 = Metric.of("http_requests_total")
                .label("status", Metric.Compare.NON_LIKE, "4..");
        assertEquals("http_requests_total{status!~\"4..\"}", metric4.toPromQL());
        // sum by (job) (rate(http_requests_total[5m]))
        Expression expression1 = Aggregators.sum(
                Functions.rate(
                        Metric.of("http_requests_total").range("5m")
                )
        ).groupBy("job");
        assertEquals("sum by (job) (rate(http_requests_total[5m]))", expression1.toPromQL());
        // sum by (app, proc) (instance_memory_limit_bytes - instance_memory_usage_bytes) / 1024 / 1024
        Expression expression2 = Operators.divide(
                Operators.divide(
                        Aggregators.sum(
                                Operators.subtract(
                                        Metric.of("instance_memory_limit_bytes"),
                                        Metric.of("instance_memory_usage_bytes")
                                )
                        ).groupBy("app", "proc"),
                        Number.of(1024)
                ),
                Number.of(1024)
        );
        assertEquals("sum by (app, proc) (instance_memory_limit_bytes - instance_memory_usage_bytes) / 1024 / 1024", expression2.toPromQL());
    }
}
