package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class PositiveSignum<T extends BigDecimal> {

    private T verified;

    private PositiveSignum() {
    }

    public PositiveSignum(final T verify) {
        if (!(new NotNull<>(verify).provide().signum() > 0)) {
            throw new IllegalArgumentException("must have positive signum");
        }
        this.verified = verify;
    }

    public final T provide() {
        return this.verified;
    }
}
