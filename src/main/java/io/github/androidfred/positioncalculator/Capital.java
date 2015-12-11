package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Capital {

    private BigDecimal verified;

    private Capital() {
    }

    public Capital(final BigDecimal verify) {
        this.verified = new PositiveSignum(
                new NotNull<>(verify).provide()
        ).provide();
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
