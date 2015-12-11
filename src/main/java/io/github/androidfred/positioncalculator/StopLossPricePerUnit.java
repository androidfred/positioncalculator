package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class StopLossPricePerUnit {

    private BigDecimal verified;

    public StopLossPricePerUnit() {
    }

    public StopLossPricePerUnit(final BigDecimal verify) {
        this.verified = new PositiveSignum(
                new NotNull<>(verify).provide()
        ).provide();
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
