package com.holgercloud.aiops.promql;

import com.holgercloud.aiops.promql.model.Expression;
import com.holgercloud.aiops.promql.model.Functionized;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

/**
 * Functions for PromQL.
 *
 * @author holger
 * @date 2025/7/14
 */
public final class Functions {
    public static Functionized increase(Expression expression) {
        return new Functionized() {
            @Override
            public Function<Expression, Expression> getFunction() {
                return Functions::increase;
            }

            @Override
            public String toPromQL() {
                return "increase(" + expression.toPromQL() + ")";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Functionized rate(Expression expression) {
        return new Functionized() {
            @Override
            public Function<Expression, Expression> getFunction() {
                return Functions::rate;
            }

            @Override
            public String toPromQL() {
                return "rate(" + expression.toPromQL() + ")";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Functionized irate(Expression expression) {
        return new Functionized() {
            @Override
            public Function<Expression, Expression> getFunction() {
                return Functions::irate;
            }

            @Override
            public String toPromQL() {
                return "irate(" + expression.toPromQL() + ")";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }

    public static Functionized labelReplace(Expression expression, String distLabel, String replacement,
                                            String srcLabel, String regex) {
        return new Functionized() {
            @Override
            public Function<Expression, Expression> getFunction() {
                return expr -> labelReplace(expr, distLabel, replacement, srcLabel, regex);
            }

            @Override
            public String toPromQL() {
                return "label_replace(" + expression.toPromQL() + ", \"" +
                        StringUtils.defaultIfEmpty(distLabel, "") + "\", \"" +
                        StringUtils.defaultIfEmpty(replacement, "") + "\", \"" +
                        StringUtils.defaultIfEmpty(srcLabel, "") + "\", \"" +
                        StringUtils.defaultIfEmpty(regex, "") + "\")";
            }

            @Override
            public Collection<Expression> getComponents() {
                return Collections.singleton(expression);
            }
        };
    }
}
