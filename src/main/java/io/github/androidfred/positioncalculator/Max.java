package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Max {

    private BigDecimal verified;

    private Max() {
    }

    public Max(final BigDecimal verify, final BigDecimal max) {
        if (new NotNull<>(verify).provide().compareTo(new NotNull<>(max).provide()) != -1) {
            throw new IllegalArgumentException("must be max " + max);
        }
        this.verified = verify;
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
