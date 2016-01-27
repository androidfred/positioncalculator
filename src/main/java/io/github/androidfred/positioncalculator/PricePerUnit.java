package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class PricePerUnit<T extends BigDecimal> {

    private T verified;

    public PricePerUnit() {
    }

    public PricePerUnit(final T verify) {
        this.verified = new Max<T>(
                new PositiveSignum<T>(
                        new NotNull<>(verify).provide()
                ).provide(),
                new BigDecimal(1000000000)
        ).provide();
    }

    public final T provide() {
        return this.verified;
    }
}
