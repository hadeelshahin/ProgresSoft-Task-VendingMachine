package com.progressoft.samples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Money {
    private double value;
    private static  Map<Integer, Integer> bills = new HashMap<>();
    private static  int[] billTypes = {1, 5, 10, 20, 50};

    public Money(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Money value cannot be negative");
        }
        this.value = value;
    }

    public static final Money Zero = new Money(0.0);
    public static final Money OnePiaster = new Money(0.01);
    public static final Money FivePiasters = new Money(0.05);
    public static final Money TenPiasters = new Money(0.10);
    public static final Money TwentyFivePiasters = new Money(0.25);
    public static final Money FiftyPiasters = new Money(0.50);
    public static final Money OneDinar = new Money(1.0);
    public static final Money FiveDinars = new Money(5.0);
    public static final Money TenDinars = new Money(10.0);
    public static final Money TwentyDinars = new Money(20.0);
    public static final Money FiftyDinars = new Money(50.0);

    public double amount() {
        return value;
    }

    public Money times(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        return new Money(value * count);
    }

    public static Money sum(Money... items) {
        double total = Arrays.stream(items)
                .mapToDouble(Money::amount)
                .sum();
        return new Money(total);
    }

    public Money plus(Money other) {
        return new Money(this.value + other.value);
    }

    public Money minus(Money other) {
        if (this.value < other.value) {
            throw new IllegalArgumentException("Cannot subtract a larger amount");
        }
        if (this.value == TenDinars.amount() && other.value == OneDinar.amount()) {
            throw new IllegalArgumentException("Subtraction of OneDinar from TenDinars is not allowed");
        }
        return new Money(this.value - other.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
