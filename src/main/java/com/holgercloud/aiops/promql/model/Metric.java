package com.holgercloud.aiops.promql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * PromQL Metric class.
 *
 * @author holger
 * @date 2025/7/14
 */
public class Metric implements Expression {
    private String name;
    private final List<Label> labels;
    private String range;

    private Metric() {
        this.name = null;
        this.labels = new LinkedList<>();
        this.range = null;
    }

    private Metric(String name) {
        this.name = name;
        this.labels = new LinkedList<>();
        this.range = null;
    }

    public String name() {
        return this.name;
    }

    public Metric name(String name) {
        this.name = StringUtils.isNoneBlank(name) ? name : null;
        return this;
    }

    public List<Label> labels() {
        return labels;
    }

    public Metric label(String key, Compare cmp, Object value) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Label key cannot be null");
        }
        this.labels.add(new Label(key, cmp, value.toString()));
        return this;
    }

    public Metric label(String key, Object value) {
        return this.label(key, Compare.EQ, value);
    }

    public String range() {
        return range;
    }

    public Metric range(String range) {
        this.range = StringUtils.isNoneBlank(range) ? range : null;
        return this;
    }

    public Metric unsetRange() {
        return this.range(null);
    }

    @Override
    public String toPromQL() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNoneBlank(this.name)) {
            sb.append(this.name);
        }
        if (!this.labels.isEmpty()) {
            sb.append("{");
            sb.append(StringUtils.join(labels.stream().map(Label::toString).collect(Collectors.toList()), ", "));
            sb.append("}");
        }
        if (StringUtils.isNoneBlank(this.range)) {
            sb.append("[").append(this.range).append("]");
        }
        return sb.toString();
    }

    @Override
    public Collection<Expression> getComponents() {
        return Collections.singleton(this);
    }

    public static Metric of(String name) {
        return new Metric(name);
    }

    public static Metric of() {
        return new Metric();
    }


    @Getter
    @AllArgsConstructor
    public static class Label {
        private String key;
        private Compare compare;
        private String value;

        @Override
        public String toString() {
            return key + compare.getSymbol() + "\"" + value + "\"";
        }
    }

    @Getter
    public enum Compare {
        EQ("="),
        NE("!="),
        LIKE("=~"),
        NON_LIKE("!~"),
        GT(">"),
        LT("<"),
        GE(">="),
        LE("<=");

        private final String symbol;

        Compare(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
}
