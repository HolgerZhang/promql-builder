package com.holgercloud.aiops.promql.model;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents a numeric value in PromQL.
 *
 * @author holger
 * @date 2025/7/14
 */
public class Number extends java.lang.Number implements Expression {
    private final java.lang.Number value;

    private Number(java.lang.Number value) {
        if (value == null) {
            throw new IllegalArgumentException("Number value cannot be null");
        }
        this.value = value;
    }

    public static Number of(java.lang.Number value) {
        return new Number(value);
    }

    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue() {
        return value.intValue();
    }

    /**
     * Returns the value of the specified number as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue() {
        return value.longValue();
    }

    /**
     * Returns the value of the specified number as a {@code float}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue() {
        return value.floatValue();
    }

    /**
     * Returns the value of the specified number as a {@code double}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toPromQL() {
        return this.toString();
    }

    @Override
    public Collection<Expression> getComponents() {
        return Collections.singletonList(this);
    }
}
