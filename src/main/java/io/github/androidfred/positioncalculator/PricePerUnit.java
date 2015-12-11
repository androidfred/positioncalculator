package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class PricePerUnit {

    private BigDecimal verified;

    public PricePerUnit() {
    }

    public PricePerUnit(final BigDecimal verify) {
        this.verified = new Max(
                new PositiveSignum(
                        new NotNull<>(verify).provide()
                ).provide(), new BigDecimal(1000000000)
        ).provide();
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
