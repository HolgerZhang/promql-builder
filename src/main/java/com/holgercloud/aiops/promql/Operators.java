package com.holgercloud.aiops.promql;

import com.holgercloud.aiops.promql.model.Calculated;
import com.holgercloud.aiops.promql.model.Expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 * Binary operators for PromQL expressions.
 *
 * @author holger
 * @date 2025/7/14
 */
public final class Operators {
    public static Calculated add(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::add;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " + " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }

    public static Calculated subtract(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::subtract;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " - " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }

    public static Calculated multiply(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::multiply;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " * " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }

    public static Calculated divide(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::divide;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " / " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }

    public static Calculated mod(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::mod;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " % " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }

    public static Calculated pow(Expression left, Expression right) {
        return new Calculated() {
            @Override
            public BiFunction<Expression, Expression, Calculated> getOperator() {
                return Operators::pow;
            }

            @Override
            public String toPromQL() {
                return left.toPromQL() + " ^ " + right.toPromQL();
            }

            @Override
            public Collection<Expression> getComponents() {
                return Arrays.asList(left, right);
            }
        };
    }
}
