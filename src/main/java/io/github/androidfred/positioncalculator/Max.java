package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Max<T extends BigDecimal> {

    private T verified;

    private Max() {
    }

    public Max(final T verify, final BigDecimal max) {
        if (new NotNull<>(verify).provide().compareTo(new NotNull<>(max).provide()) != -1) {
            throw new IllegalArgumentException("must be max " + max);
        }
        this.verified = verify;
    }

    public final T provide() {
        return this.verified;
    }
}
