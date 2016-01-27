package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class StopLossPricePerUnit<T extends BigDecimal> {

    private T verified;

    public StopLossPricePerUnit() {
    }

    public StopLossPricePerUnit(final T verify) {
        this.verified = new PositiveSignum<T>(
                new NotNull<>(verify).provide()
        ).provide();
    }

    public final T provide() {
        return this.verified;
    }
}
