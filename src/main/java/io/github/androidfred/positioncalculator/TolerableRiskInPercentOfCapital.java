package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class TolerableRiskInPercentOfCapital<T extends BigDecimal> {

    private T verified;

    public TolerableRiskInPercentOfCapital() {
    }

    public TolerableRiskInPercentOfCapital(final T verify) {
        this.verified = new Max<T>(
                new PositiveSignum<T>(
                        new NotNull<>(verify).provide()
                ).provide(),
                new BigDecimal(100)
        ).provide();
    }

    public final T provide() {
        return this.verified;
    }
}
