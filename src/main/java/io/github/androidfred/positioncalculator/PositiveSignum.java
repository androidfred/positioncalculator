package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class PositiveSignum {

    private BigDecimal verified;

    private PositiveSignum() {
    }

    public PositiveSignum(final BigDecimal verify) {
        if (!(new NotNull<>(verify).provide().signum() > 0)) {
            throw new IllegalArgumentException("must have positive signum");
        }
        this.verified = verify;
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
