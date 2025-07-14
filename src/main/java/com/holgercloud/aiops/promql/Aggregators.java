package com.holgercloud.aiops.promql;

import com.holgercloud.aiops.promql.model.Aggregated;
import com.holgercloud.aiops.promql.model.AggregatedBy;
import com.holgercloud.aiops.promql.model.AggregatedWithParam;
import com.holgercloud.aiops.promql.model.Expression;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Aggregators for PromQL.
 *
 * @author holger
 * @date 2025/7/14
 */
public final class Aggregators {

    private static String format(String aggregationType, Expression expression, List<String> labels) {
        if (labels == null || labels.isEmpty()) {
            return aggregationType + "(" + expression.toPromQL() + ")";
        } else {
            String labelsStr = String.join(", ", labels);
            return aggregationType + " by (" + labelsStr + ") (" + expression.toPromQL() + ")";
        }
    }

    public static AggregatedBy sum(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::sum;
            }

            @Override
            public String toPromQL() {
                return format("sum", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy avg(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::avg;
            }

            @Override
            public String toPromQL() {
                return format("avg", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy min(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::min;
            }

            @Override
            public String toPromQL() {
                return format("min", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy max(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::max;
            }

            @Override
            public String toPromQL() {
                return format("max", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy count(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::count;
            }

            @Override
            public String toPromQL() {
                return format("count", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy stddev(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::stddev;
            }

            @Override
            public String toPromQL() {
                return format("stddev", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedBy stdvar(Expression expression) {
        return new AggregatedBy() {
            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::stdvar;
            }

            @Override
            public String toPromQL() {
                return format("stdvar", expression, labels);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedWithParam<Double> quantile(double quantile, Expression expression) {
        return new AggregatedWithParam<Double>() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public BiFunction<Double, Expression, Aggregated> getAggregatorWithParam() {
                return Aggregators::quantile;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return exp -> Aggregators.quantile(quantile, exp);
            }

            @Override
            public String toPromQL() {
                return "quantile(" + quantile + ", (" + expression.toPromQL() + "))";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedWithParam<String> countValues(String value, Expression expression) {
        return new AggregatedWithParam<String>() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public BiFunction<String, Expression, Aggregated> getAggregatorWithParam() {
                return Aggregators::countValues;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return exp -> Aggregators.countValues(value, exp);
            }

            @Override
            public String toPromQL() {
                return "count_values(\"" + StringUtils.defaultIfEmpty(value, "") +
                        "\", (" + expression.toPromQL() + "))";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedWithParam<Integer> bottomK(int k, Expression expression) {
        return new AggregatedWithParam<Integer>() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public BiFunction<Integer, Expression, Aggregated> getAggregatorWithParam() {
                return Aggregators::bottomK;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return exp -> Aggregators.bottomK(k, exp);
            }

            @Override
            public String toPromQL() {
                return "bottomk(" + k + ", (" + expression.toPromQL() + "))";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedWithParam<Integer> topK(int k, Expression expression) {
        return new AggregatedWithParam<Integer>() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public BiFunction<Integer, Expression, Aggregated> getAggregatorWithParam() {
                return Aggregators::topK;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return exp -> Aggregators.topK(k, exp);
            }

            @Override
            public String toPromQL() {
                return "topk(" + k + ", (" + expression.toPromQL() + "))";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static AggregatedWithParam<Integer> limitK(int k, Expression expression) {
        return new AggregatedWithParam<Integer>() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public BiFunction<Integer, Expression, Aggregated> getAggregatorWithParam() {
                return Aggregators::topK;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return exp -> Aggregators.topK(k, exp);
            }

            @Override
            public String toPromQL() {
                return "limitk(" + k + ", (" + expression.toPromQL() + "))";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Aggregated sumOverTime(Expression expression) {
        return new Aggregated() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::sumOverTime;
            }

            @Override
            public String toPromQL() {
                return format("sum_over_time", expression, null);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Aggregated avgOverTime(Expression expression) {
        return new Aggregated() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::avgOverTime;
            }

            @Override
            public String toPromQL() {
                return format("avg_over_time", expression, null);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Aggregated maxOverTime(Expression expression) {
        return new Aggregated() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::maxOverTime;
            }

            @Override
            public String toPromQL() {
                return format("max_over_time", expression, null);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Aggregated minOverTime(Expression expression) {
        return new Aggregated() {
            @Override
            public List<String> getGroupBy() {
                return null;
            }

            @Override
            public Function<Expression, Aggregated> getAggregator() {
                return Aggregators::minOverTime;
            }

            @Override
            public String toPromQL() {
                return format("min_over_time", expression, null);
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

}
