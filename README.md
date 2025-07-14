# PromQL Builder for Java

A simple PromQL (Prometheus Query Language) builder for Java applications. This library provides a Java implementation for generating queries in Prometheus Query Language, following the official Prometheus querying specification.

> ⚠️ **Early Development Stage**: This project is currently in very early development stage. _The PromQL support is incomplete and the API may change significantly_. We welcome contributions from the open source community to help improve and expand this library.

## Overview

This library allows developers to programmatically construct PromQL queries using Java, eliminating the need to manually concatenate strings and reducing syntax errors. It follows the [Prometheus querying basics specification](https://prometheus.io/docs/prometheus/latest/querying/basics/) to ensure compatibility and correctness.

## Features

### Currently Implemented

- **PromQL Generation**: Generate PromQL queries through Java code
- **Instant/Range Vector Selectors**: Support for basic metric selection with label matchers, and range queries with duration specifications
- **Binary Operations**: Partial support for binary operations between metrics
- **Aggregation Operations**: Partial support for aggregation functions (sum, avg, etc.)


### Example Usage

```java
import com.holgercloud.aiops.promql.Aggregators;
import com.holgercloud.aiops.promql.Functions;
import com.holgercloud.aiops.promql.Operators;
import com.holgercloud.aiops.promql.model.Expression;
import com.holgercloud.aiops.promql.model.Metric;
import com.holgercloud.aiops.promql.model.Number;

public class Main {
    public static void main(String[] args) {
        // http_requests_total
        Expression metric1 = Metric.of("http_requests_total");
        System.out.println("Metric 1: " + metric1.toPromQL());

        // http_requests_total{job="apiserver", handler="/api/comments"}
        Expression metric2 = Metric.of("http_requests_total")
                .label("job", "apiserver")
                .label("handler", "/api/comments");
        System.out.println("Metric 2: " + metric2.toPromQL());

        // http_requests_total{job=~".*server"}
        Expression metric3 = Metric.of("http_requests_total")
                .label("job", Metric.Compare.LIKE, ".*server");
        System.out.println("Metric 3: " + metric3.toPromQL());

        // http_requests_total{status!~"4.."}
        Expression metric4 = Metric.of("http_requests_total")
                .label("status", Metric.Compare.NON_LIKE, "4..");
        System.out.println("Metric 4: " + metric4.toPromQL());

        // sum by (job) (rate(http_requests_total[5m]))
        Expression expression1 = Aggregators.sum(
                Functions.rate(
                        Metric.of("http_requests_total").range("5m")
                )
        ).groupBy("job");
        System.out.println("Expression 1: " + expression1.toPromQL());

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
        System.out.println("Expression 2: " + expression2.toPromQL());
    }
}

```

## Installation

Coming soon! This library will be available via Maven or as a JAR file.

Now you can clone the repository and build it locally using Maven:

```bash
git clone https://github.com/HolgerZhang/promql-builder.git
cd promql-builder
mvn clean install
```

## Requirements

- Java 1.8 or higher

## Roadmap

### Planned Features

1. **Complete PromQL Support** (in progress)
    - Offset and @ modifiers
    - More binary operations, aggregation operators, and functions
    - Subqueries and joins

2. **PromQL Parser** (planned for future release)
    - Java parser for existing PromQL strings
    - Convert PromQL strings to corresponding Java objects
    - Validation and syntax checking
    - Query analysis and optimization suggestions

## Contributing

Contributions are welcome! Please feel free to submit issues, feature requests, or pull requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## References

- [Prometheus Querying Basics](https://prometheus.io/docs/prometheus/latest/querying/basics/)
- [Prometheus Documentation](https://prometheus.io/docs/)